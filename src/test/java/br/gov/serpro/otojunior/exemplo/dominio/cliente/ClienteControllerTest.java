/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.cliente;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Classe de teste para a classe ClienteController.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
@WebMvcTest(controllers = ClienteController.class)
@DisplayName("Controller de Cliente")
class ClienteControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ClienteService clienteService;

    @Test
    @DisplayName("Listar todos com sucesso")
    void listarTodos_sucesso() throws Exception {
        var cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678901");
        cliente.setNome("Cliente 1");
        cliente.setEmail("cliente1@exemplo.com");
        when(clienteService.listarTodos()).thenReturn(List.of(cliente));

        mockMvc.perform(get("/api/clientes"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].cpf").value("12345678901"));
    }

    @Test
    @DisplayName("Obter por id não encontrado")
    void obterPorId_naoEncontrado() throws Exception {
        when(clienteService.obterPorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/clientes/99"))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Criar com sucesso")
    void criar_sucesso() throws Exception {
                var body = """
                        {
                            "cpf": "12345678901",
                            "nome": "Cliente 1",
                            "email": "cliente1@exemplo.com"
                        }
                        """;
        var cliente = new Cliente();
        cliente.setId(1L);
                cliente.setCpf("12345678901");
                cliente.setNome("Cliente 1");
                cliente.setEmail("cliente1@exemplo.com");
        when(clienteService.criar(any(ClienteDto.class))).thenReturn(cliente);

        mockMvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
            .content(body))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/api/clientes/1"))
            .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @DisplayName("Atualizar não encontrado")
    void atualizar_naoEncontrado() throws Exception {
                var body = """
                        {
                            "cpf": "12345678901",
                            "nome": "Cliente 1",
                            "email": "cliente1@exemplo.com"
                        }
                        """;
        when(clienteService.atualizar(any(Long.class), any(ClienteDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/clientes/99")
                .contentType(MediaType.APPLICATION_JSON)
                                .content(body))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Excluir com sucesso")
    void excluir_sucesso() throws Exception {
        when(clienteService.excluir(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/clientes/1"))
            .andExpect(status().isNoContent());
    }
}
