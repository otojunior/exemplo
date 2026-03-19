/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import org.springframework.transaction.annotation.Transactional;
import br.gov.serpro.otojunior.exemplo.core.testcontainer.TestContainersIntegrationTest;

/**
 * 
 */
@SpringBootTest
@Transactional
@Rollback
class ProdutoServiceIT extends TestContainersIntegrationTest {
    @MockitoSpyBean
    private ProdutoRepositorio produtoRepositorio;
    
    @Autowired
    private ProdutoService produtoService;
    
    
    /**
     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.produto.ProdutoService#listarTodos()}.
     */
    @Test
    void testListarTodos() {
        for (int i = 0; i < 3; i++) {
            var p = new Produto();
            p.setCodigo("P00" + i);
            p.setNome("Produto" + i);
            p.setDescricao("Desc" + i);
            produtoRepositorio.save(p);
        }
        
        var produtos = produtoService.listarTodos();
        
        assertEquals(3, produtos.size());
        for (int i = 0; i < 3; i++) {
            var produto = produtos.get(i);
            assertEquals("P00" + i, produto.getCodigo());
            assertEquals("Produto" + i, produto.getNome());
            assertEquals("Desc" + i, produto.getDescricao());
        }
    }

//    /**
//     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.produto.ProdutoService#obterPorId(java.lang.Long)}.
//     */
//    @Test
//    void testObterPorId() {
//        fail("Not yet implemented");
//    }
//
//    /**
//     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.produto.ProdutoService#obterPorCodigo(java.lang.String)}.
//     */
//    @Test
//    void testObterPorCodigo() {
//        fail("Not yet implemented");
//    }
//
//    /**
//     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.produto.ProdutoService#criar(br.gov.serpro.otojunior.exemplo.dominio.produto.ProdutoDto)}.
//     */
//    @Test
//    void testCriar() {
//        fail("Not yet implemented");
//    }
//
//    /**
//     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.produto.ProdutoService#atualizar(java.lang.Long, br.gov.serpro.otojunior.exemplo.dominio.produto.ProdutoDto)}.
//     */
//    @Test
//    void testAtualizar() {
//        fail("Not yet implemented");
//    }
//
//    /**
//     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.produto.ProdutoService#excluir(java.lang.Long)}.
//     */
//    @Test
//    void testExcluir() {
//        fail("Not yet implemented");
//    }
//
//    /**
//     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.produto.ProdutoService#ProdutoService(ProdutoRepositorio)}.
//     */
//    @Test
//    void testProdutoService() {
//        fail("Not yet implemented");
//    }

}
