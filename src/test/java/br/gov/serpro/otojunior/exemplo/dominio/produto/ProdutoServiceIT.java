/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.produto;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import br.gov.serpro.otojunior.exemplo.core.testcontainer.TestContainersIntegrationTest;

/**
 * 
 */
@SpringBootTest
@TestContainersIntegrationTest
class ProdutoServiceIT {
    @MockitoSpyBean
    private ProdutoRepositorio produtoRepositorio;
    
    @Autowired
    private ProdutoService produtoService;
    
    /**
     * Testa o método listarTodos para verificar se ele retorna a lista correta de
     * produtos.
     */
    @Test
    void listarTodos_sucesso() {
        salvarEntidades();
        var obtidos = produtoService.listarTodos();
        assertEquals(3, obtidos.size());
        for (int i = 0; i < 3; i++) {
            var obtido = obtidos.get(i);
            assertEquals("P00" + i, obtido.getCodigo());
            assertEquals("Produto" + i, obtido.getNome());
            assertEquals("Desc" + i, obtido.getDescricao());
        }
    }

    /**
     * Testa o método obterPorId para verificar se ele retorna vazio quando o id não é
     * encontrado.
     */
    @Test
    void obterPorId_naoEncontrado() {
        salvarEntidade();
        var obtido = produtoService.obterPorId(999999L);
        assertTrue(obtido.isEmpty());
    }

    /**
     * Testa o método obterPorId para verificar se ele retorna o produto correto
     * quando o id é encontrado.
     */
    @Test
    void obterPorId_Encontrado() {
        var salvo = salvarEntidade();
        var obtido = produtoService.obterPorId(salvo.getId());
        assertTrue(obtido.isPresent());
        assertEquals(salvo.getId(), obtido.get().getId());
        assertEquals(salvo.getCodigo(), obtido.get().getCodigo());
        assertEquals(salvo.getNome(), obtido.get().getNome());
        assertEquals(salvo.getDescricao(), obtido.get().getDescricao());
    }

    /**
     * Testa o método obterPorCodigo para verificar se ele retorna vazio quando o código
     * não é encontrado.
     */
    @Test
    void obterPorCodigo_naoEncontrado() {
        salvarEntidade();
        var obtido = produtoService.obterPorCodigo("P999999");
        assertTrue(obtido.isEmpty());
    }

    /**
     * Testa o método obterPorCodigo para verificar se ele retorna o produto correto
     * quando o código é encontrado.
     */
    @Test
    void obterPorCodigo_Encontrado() {
        var salvo = salvarEntidade();
        var obtido = produtoService.obterPorCodigo(salvo.getCodigo());
        assertTrue(obtido.isPresent());
        assertEquals(salvo.getId(), obtido.get().getId());
        assertEquals(salvo.getCodigo(), obtido.get().getCodigo());
        assertEquals(salvo.getNome(), obtido.get().getNome());
        assertEquals(salvo.getDescricao(), obtido.get().getDescricao());
    }

    /**
     * Testa o método criar para verificar se ele salva o produto corretamente e
     * retorna o produto salvo com o id gerado. O teste também verifica se o produto pode
     * ser obtido por código após a criação, garantindo que o produto foi realmente salvo
     * no banco.
     */
    @Test
    void criar_sucesso() {
        var p = new ProdutoDto("P0001", "Produto1", "Desc1");
        var salvo = produtoService.criar(p);
        var obtido = produtoRepositorio.obterPorCodigo(salvo.getCodigo());
        assertTrue(obtido.isPresent());
        assertEquals(salvo.getId(), obtido.get().getId());
        assertEquals(salvo.getCodigo(), obtido.get().getCodigo());
        assertEquals(salvo.getNome(), obtido.get().getNome());
        assertEquals(salvo.getDescricao(), obtido.get().getDescricao());
    }

    /**
     * Testa o método atualizar para verificar se ele retorna vazio quando o id não
     * é encontrado.
     */
    @Test
    void atualizar_naoEncontrado() {
        salvarEntidade();
        var atualizado = produtoService.atualizar(
            999999L,
            new ProdutoDto("P0002", "Produto2", "Desc2"));
        assertTrue(atualizado.isEmpty());
    }
    
    /**
     * Testa o método atualizar para verificar se ele atualiza o produto corretamente e
     * retorna o produto atualizado. O teste também verifica se o produto pode ser obtido
     * por código após a atualização, garantindo que as alterações foram realmente salvas
     * no banco.
     */
    @Test
    void atualizar_Encontrado() {
        var salvo = salvarEntidade();
        var atualizado = produtoService.atualizar(
            salvo.getId(),
            new ProdutoDto("P000X", "ProdutoX", "DescX"));
        assertTrue(atualizado.isPresent());
        var obtido = produtoRepositorio.obterPorCodigo(atualizado.get().getCodigo());
        assertTrue(obtido.isPresent());
        assertEquals(atualizado.get().getId(), obtido.get().getId());
        assertEquals(atualizado.get().getCodigo(), obtido.get().getCodigo());
        assertEquals(atualizado.get().getNome(), obtido.get().getNome());
        assertEquals(atualizado.get().getDescricao(), obtido.get().getDescricao());
    }

    /**
     * Testa o método excluir para verificar se ele retorna true quando o id é encontrado
     * e o produto é excluído. O teste também verifica se o produto não pode ser obtido
     * por código após a exclusão, garantindo que o produto foi realmente removido do banco.
     */
    @Test
    void excluir_sucesso() {
        var salvo = salvarEntidade();
        var excluido = produtoService.excluir(salvo.getId());
        assertTrue(excluido);
        var obtido = produtoRepositorio.obterPorCodigo(salvo.getCodigo());
        assertTrue(obtido.isEmpty());
    }

    /**
     * Salva uma entidade para teste de obtenção por id. O método é privado porque os testes
     * de obtenção por id não dependem de uma entidade específica, apenas de uma entidade
     * existente.
     */
    private Produto salvarEntidade() {
        var p = new Produto();
        p.setCodigo("P0001");
        p.setNome("Produto1");
        p.setDescricao("Desc1");
        return produtoRepositorio.saveAndFlush(p);
    }
    
    /**
     * Salva 3 entidades para teste de listagem. O método é privado porque os testes de
     * listagem não dependem de uma entidade específica, apenas da quantidade e dos dados
     * básicos.
     */
    private void salvarEntidades() {
        for (int i = 0; i < 3; i++) {
            var p = new Produto();
            p.setCodigo("P00" + i);
            p.setNome("Produto" + i);
            p.setDescricao("Desc" + i);
            produtoRepositorio.save(p);
        }
    }
}