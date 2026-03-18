/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.produto;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Produto
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 18/03/2026 - SERPRO 2026
 */
@Repository
public interface ProdutoRepositorio extends JpaRepository<Produto, Long> {
    Optional<Produto> obterPorCodigo(final String codigo);
}
