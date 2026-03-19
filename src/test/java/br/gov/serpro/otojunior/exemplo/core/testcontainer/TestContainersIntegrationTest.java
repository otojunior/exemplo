/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.core.testcontainer;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

/**
 * Classe base para testes de integração utilizando TestContainers.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 19/03/2026 - SERPRO 2026
 */
@ActiveProfiles("test")
@Import(TestContainersConfig.class)
public abstract class TestContainersIntegrationTest {
}
