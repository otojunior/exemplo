/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.produto;

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
     * Obtém um produto pelo seu código.
     * @param codigo O código do produto a ser buscado.
     * @return Um Optional contendo o produto encontrado, ou vazio se não encontrado.
     */
    public Optional<Produto> obterPorCodigo(final String codigo) {
        return produtoRepositorio.obterPorCodigo(codigo);
    }
}
