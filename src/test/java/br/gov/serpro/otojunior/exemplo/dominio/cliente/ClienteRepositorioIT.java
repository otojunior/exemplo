/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import br.gov.serpro.otojunior.exemplo.core.testcontainer.TestContainersIntegrationTest;

/**
 * Classe de teste para a classe ClienteRepositorio.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
@DataJpaTest
@TestContainersIntegrationTest
@DisplayName("Repositório de Cliente")
class ClienteRepositorioIT {
    @Autowired
    private ClienteRepositorio clienteRepositorio;

    /**
     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.cliente.ClienteRepositorio#obterPorCpf(java.lang.String)}.
     */
    @Test
    @DisplayName("Obter por CPF com sucesso")
    @Sql(statements = "insert into cliente (id, cpf, nome, email) values (1, '12345678901', 'Cliente1', 'cliente1@exemplo.com')")
    void obterPorCpf_sucesso() {
        var cliente = clienteRepositorio
            .obterPorCpf("12345678901")
            .orElseThrow();
        assertEquals("12345678901", cliente.getCpf());
        assertEquals("Cliente1", cliente.getNome());
        assertEquals("cliente1@exemplo.com", cliente.getEmail());
    }
}
