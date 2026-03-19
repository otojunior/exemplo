/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.core.testcontainer;

import static br.gov.serpro.otojunior.exemplo.core.testcontainer.TestContainersConfig.POSTGRES;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;

/**
 * Classe base para testes de integração utilizando TestContainers.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 19/03/2026 - SERPRO 2026
 */
@Import(TestContainersConfig.class)
@TestPropertySource(properties = {
    "spring.main.banner-mode=off",
    "spring.jpa.open-in-view=false",
    "spring.jpa.show-sql=false",
    "spring.jpa.properties.hibernate.format_sql=true",
    "spring.jpa.hibernate.ddl-auto=update",
    "decorator.datasource.p6spy.enable-logging=false",
    "decorator.datasource.p6spy.multiline=true",
    "decorator.datasource.p6spy.logging=slf4j",
    "decorator.datasource.p6spy.log-file=test-spy.log" })
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