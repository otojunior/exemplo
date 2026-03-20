/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.produto;

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
 * Classe de teste para a classe ProdutoController.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
@WebMvcTest(controllers = ProdutoController.class)
@DisplayName("Controller de Produto")
class ProdutoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProdutoService produtoService;

    @Test
    @DisplayName("Listar todos com sucesso")
    void listarTodos_sucesso() throws Exception {
        var produto = new Produto();
        produto.setId(1L);
        produto.setCodigo("P001");
        produto.setNome("Produto 1");
        produto.setDescricao("Descrição 1");
        when(produtoService.listarTodos()).thenReturn(List.of(produto));

        mockMvc.perform(get("/api/produtos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(1L))
            .andExpect(jsonPath("$[0].codigo").value("P001"));
    }

    @Test
    @DisplayName("Obter por id não encontrado")
    void obterPorId_naoEncontrado() throws Exception {
        when(produtoService.obterPorId(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/produtos/99"))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Criar com sucesso")
    void criar_sucesso() throws Exception {
        var body = """
            {
                "codigo": "P001",
                "nome": "Produto 1",
                "descricao": "Descrição 1"
            }
            """;
        var produto = new Produto();
        produto.setId(1L);
        produto.setCodigo("P001");
        produto.setNome("Produto 1");
        produto.setDescricao("Descrição 1");
        when(produtoService.criar(any(ProdutoDto.class))).thenReturn(produto);

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isCreated())
            .andExpect(header().string("Location", "http://localhost/api/produtos/1"))
            .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    @DisplayName("Atualizar não encontrado")
    void atualizar_naoEncontrado() throws Exception {
        var body = """
            {
                "codigo": "P001",
                "nome": "Produto 1",
                "descricao": "Descrição 1"
            }
            """;
        when(produtoService.atualizar(any(Long.class), any(ProdutoDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/produtos/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Excluir com sucesso")
    void excluir_sucesso() throws Exception {
        when(produtoService.excluir(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/produtos/1"))
            .andExpect(status().isNoContent());
    }
}
