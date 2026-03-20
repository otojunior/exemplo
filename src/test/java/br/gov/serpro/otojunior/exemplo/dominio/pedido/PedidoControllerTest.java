/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.pedido;

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
import br.gov.serpro.otojunior.exemplo.dominio.cliente.Cliente;

/**
 * Classe de teste para a classe PedidoController.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
@WebMvcTest(controllers = PedidoController.class)
@DisplayName("Controller de Pedido")
class PedidoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PedidoService pedidoService;

    @Test
    @DisplayName("Listar todos com sucesso")
    void listarTodos_sucesso() throws Exception {
        var cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678901");
        cliente.setNome("Cliente 1");
        cliente.setEmail("cliente1@exemplo.com");

        var pedido = new Pedido();
        pedido.setId(1L);
        pedido.setNumero(1001L);
        pedido.setCliente(cliente);

        when(pedidoService.listarTodos()).thenReturn(List.of(pedido));

        mockMvc.perform(get("/api/pedidos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].numero").value(1001L));
    }

    @Test
    @DisplayName("Obter por id não encontrado")
    void obterPorId_naoEncontrado() throws Exception {
        when(pedidoService.obterPorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/pedidos/99"))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Criar com sucesso")
    void criar_sucesso() throws Exception {
                var body = """
                        {
                            "numero": 1001,
                            "clienteId": 1
                        }
                        """;

        var cliente = new Cliente();
        cliente.setId(1L);
        cliente.setCpf("12345678901");
        cliente.setNome("Cliente 1");
        cliente.setEmail("cliente1@exemplo.com");

        var pedido = new Pedido();
        pedido.setId(1L);
        pedido.setNumero(1001L);
        pedido.setCliente(cliente);
        when(pedidoService.criar(any(PedidoDto.class))).thenReturn(pedido);

        mockMvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
            .content(body))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/api/pedidos/1"))
            .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @DisplayName("Atualizar não encontrado")
    void atualizar_naoEncontrado() throws Exception {
                var body = """
                        {
                            "numero": 1001,
                            "clienteId": 1
                        }
                        """;
        when(pedidoService.atualizar(any(Long.class), any(PedidoDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/pedidos/99")
                .contentType(MediaType.APPLICATION_JSON)
                                .content(body))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Excluir com sucesso")
    void excluir_sucesso() throws Exception {
        when(pedidoService.excluir(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/pedidos/1"))
            .andExpect(status().isNoContent());
    }
}
