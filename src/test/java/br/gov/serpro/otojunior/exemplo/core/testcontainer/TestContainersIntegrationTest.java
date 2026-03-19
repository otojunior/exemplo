/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.core.testcontainer;

import static br.gov.serpro.otojunior.exemplo.core.testcontainer.TestContainersConfig.POSTGRES;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

/**
 * Classe base para testes de integração utilizando TestContainers.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 19/03/2026 - SERPRO 2026
 */
@ActiveProfiles("test")
@Import(TestContainersConfig.class)
public abstract class TestContainersIntegrationTest {
    /**
     * Sobrescreve as propriedades de configuração para usar o container PostgreSQL
     * e ajustar as configurações do JPA e P6Spy.
     * @param registry o registro de propriedades dinâmicas
     */
    @DynamicPropertySource
    static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", POSTGRES::getJdbcUrl);
        registry.add("spring.datasource.username", POSTGRES::getUsername);
        registry.add("spring.datasource.password", POSTGRES::getPassword);
    }
}