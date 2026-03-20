/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.produto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.test.web.servlet.MockMvc;
import br.gov.serpro.otojunior.exemplo.core.testcontainer.TestContainersIntegrationTest;

/**
 * Classe de teste para a classe ProdutoController.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
@AutoConfigureMockMvc
@SpringBootTest
@TestContainersIntegrationTest
@DisplayName("IT: Controller de Produto")
class ProdutoControllerIT {
    @MockitoSpyBean
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Listar todos com sucesso")
    void listarTodos_sucesso() throws Exception {
        var produto = salvarEntidade();
        mockMvc.perform(get("/api/produtos"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id").value(produto.getId()))
            .andExpect(jsonPath("$[0].codigo").value("P001"));
    }

    @Test
    @DisplayName("Obter por id não encontrado")
    void obterPorId_naoEncontrado() throws Exception {
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

        mockMvc.perform(post("/api/produtos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.codigo").value("P001"))
            .andExpect(jsonPath("$.nome").value("Produto 1"))
            .andExpect(jsonPath("$.descricao").value("Descrição 1"));
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
        mockMvc.perform(put("/api/produtos/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(body))
            .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Excluir com sucesso")
    void excluir_sucesso() throws Exception {
        var produto = salvarEntidade();
        mockMvc.perform(delete("/api/produtos/" + produto.getId()))
            .andExpect(status().isNoContent());
    }
    
    /**
     * @return
     */
    private Produto salvarEntidade() {
        var produto = new Produto();
        produto.setCodigo("P001");
        produto.setNome("Produto 1");
        produto.setDescricao("Descrição 1");
        produto = produtoRepositorio.saveAndFlush(produto);
        return produto;
    }
}
