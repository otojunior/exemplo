/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.infra.pessoafisica;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

/**
 * 
 */
@SpringBootTest
@TestPropertySource(properties = "spring.main.banner-mode=off")
class PessoaFisicaGatewayMockIT {
    @Autowired
    private PessoaFisicaGateway gateway;
    
    /**
     * Test method for {@link br.gov.serpro.otojunior.exemplo.infra.pessoafisica.PessoaFisicaGatewayMock#obterPorCpf(java.lang.String)}.
     */
    @Test
    void testObterPorCpf() {
        var dto = gateway.obterPorCpf("01456231650");
        assertEquals("01456231650", dto.cpf());
        assertEquals("Fulano:01456231650", dto.nome());
        assertEquals("01456231650@exemplo.com", dto.email());
    }
}
