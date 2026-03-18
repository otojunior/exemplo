/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.produto;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * Classe de serviço para operações relacionadas a produtos.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 18/03/2026 - SERPRO 2026
 */
@RequiredArgsConstructor
@Service
public class ProdutoService {
    private final ProdutoRepositorio produtoRepositorio;

    /**
     * Lista todos os produtos cadastrados.
     * @return Lista de produtos.
     */
    public List<Produto> listarTodos() {
        return produtoRepositorio.findAll();
    }

    /**
     * Obtém um produto pelo seu identificador.
     * @param id Identificador do produto.
     * @return Optional contendo o produto, se encontrado.
     */
    public Optional<Produto> obterPorId(final Long id) {
        return produtoRepositorio.findById(id);
    }

    /**
     * Obtém um produto pelo seu código.
     * @param codigo O código do produto a ser buscado.
     * @return Um Optional contendo o produto encontrado, ou vazio se não encontrado.
     */
    public Optional<Produto> obterPorCodigo(final String codigo) {
        return produtoRepositorio.obterPorCodigo(codigo);
    }

    /**
     * Cria um novo produto.
     * @param produtoDto Dados para criação do produto.
     * @return Produto criado.
     */
    public Produto criar(final ProdutoDto produtoDto) {
        Produto produto = new Produto();
        preencherProduto(produto, produtoDto);
        return produtoRepositorio.save(produto);
    }

    /**
     * Atualiza um produto existente.
     * @param id Identificador do produto.
     * @param produtoDto Dados para atualização.
     * @return Optional contendo o produto atualizado, se encontrado.
     */
    public Optional<Produto> atualizar(final Long id, final ProdutoDto produtoDto) {
        return produtoRepositorio
            .findById(id)
            .map(produto -> {
                preencherProduto(produto, produtoDto);
                return produtoRepositorio.save(produto);
            });
    }

    /**
     * Exclui um produto por id.
     * @param id Identificador do produto.
     * @return true se o produto existia e foi excluído; false caso contrário.
     */
    public boolean excluir(final Long id) {
        return produtoRepositorio
            .findById(id)
            .map(produto -> {
                produtoRepositorio.delete(produto);
                return true;
            })
            .orElse(false);
    }

    /**
     * Preenche os dados de um produto com base em um ProdutoDto.
     * @param produto O produto a ser preenchido.
     * @param produtoDto Os dados do produto a serem usados para preenchimento.
     */
    private void preencherProduto(final Produto produto, final ProdutoDto produtoDto) {
        produto.setCodigo(produtoDto.codigo());
        produto.setNome(produtoDto.nome());
        produto.setDescricao(produtoDto.descricao());
    }
}
