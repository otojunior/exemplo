/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.pedido;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repositório para a entidade Pedido
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
@Repository
public interface PedidoRepositorio extends JpaRepository<Pedido, Long> {
    Optional<Pedido> obterPorNumero(final Long numero);
}
