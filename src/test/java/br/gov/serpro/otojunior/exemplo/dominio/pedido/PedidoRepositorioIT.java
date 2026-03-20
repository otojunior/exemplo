/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import br.gov.serpro.otojunior.exemplo.core.testcontainer.TestContainersIntegrationTest;

/**
 * Classe de teste para a classe PedidoRepositorio.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
@DataJpaTest
@TestContainersIntegrationTest
@DisplayName("Repositório de Pedido")
class PedidoRepositorioIT {
    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    /**
     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.pedido.PedidoRepositorio#obterPorNumero(java.lang.Long)}.
     */
    @Test
    @DisplayName("Obter por número com sucesso")
    @Sql(statements = {
        "insert into cliente (id, cpf, nome, email) values (1, '12345678901', 'Cliente1', 'cliente1@exemplo.com')",
        "insert into pedido (id, numero, cliente_id) values (1, 1001, 1)"
    })
    void obterPorNumero_sucesso() {
        var pedido = pedidoRepositorio
            .obterPorNumero(1001L)
            .orElseThrow();
        assertEquals(1001L, pedido.getNumero());
        assertEquals("12345678901", pedido.getCliente().getCpf());
    }
}
