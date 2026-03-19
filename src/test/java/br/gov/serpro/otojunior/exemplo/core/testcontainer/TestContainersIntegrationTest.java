/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.core.testcontainer;

import static br.gov.serpro.otojunior.exemplo.core.testcontainer.TestContainersConfig.POSTGRES;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import java.util.function.Supplier;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

/**
 * Classe base para testes de integração utilizando TestContainers.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 19/03/2026 - SERPRO 2026
 */
@Import(TestContainersConfig.class)
public abstract class TestContainersIntegrationTest {
    private static final Supplier<Object> SIM = TRUE::toString;
    private static final Supplier<Object> NAO = FALSE::toString;
    private static final Supplier<Object> DDL_AUTO = () -> "create-only";
    
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
        
        registry.add("spring.jpa.open-in-view", NAO);
        registry.add("spring.jpa.show-sql", NAO);
        registry.add("spring.jpa.properties.hibernate.format_sql", SIM);
        registry.add("spring.jpa.hibernate.ddl-auto", DDL_AUTO);
        
        registry.add("decorator.datasource.p6spy.enable-logging", SIM);
        registry.add("decorator.datasource.p6spy.multiline", SIM);
        registry.add("decorator.datasource.p6spy.logging", () -> "slf4j");
        registry.add("decorator.datasource.p6spy.log-file", () -> "test-spy.log");
    }
}