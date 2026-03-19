/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import br.gov.serpro.otojunior.exemplo.core.testcontainer.TestContainersIntegrationTest;

/**
 * Classe de teste para a classe ProdutoRepositorio.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 18/03/2026 - SERPRO 2026
 */
@DataJpaTest
@TestContainersIntegrationTest
@DisplayName("Repositório de Produto")
class ProdutoRepositorioIT {
    @Autowired
    private ProdutoRepositorio produtoRepositorio;
    
    /**
     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.produto.ProdutoRepositorio#obterPorCodigo(java.lang.String)}.
     */
    @Test
    @DisplayName("Obter por código com sucesso")
    @Sql(statements = "insert into produto (id, codigo, nome, descricao) values (1, 'P001', 'Produto1', 'Desc1')")
    void obterPorCodigo_sucesso() {
        var produto = produtoRepositorio
            .obterPorCodigo("P001")
            .orElseThrow();
        assertEquals("P001", produto.getCodigo());
        assertEquals("Produto1", produto.getNome());
        assertEquals("Desc1", produto.getDescricao());
    }
}
