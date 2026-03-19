/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.core.testcontainer;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 * 
 */
public abstract class TestContainer {
    protected static final PostgreSQLContainer postgreSQLContainer =
        new PostgreSQLContainer("postgres:alpine")
            .withDatabaseName("test")
            .withUsername("testuser")
            .withPassword("testpass");

    static {
        System.setProperty("testcontainers.ryuk.disabled", "true");
        postgreSQLContainer.start();
    }
    
    @DynamicPropertySource
    protected static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
        
        registry.add("spring.jpa.open-in-view", () -> "false");
        registry.add("spring.jpa.show-sql", () -> "false");
        registry.add("spring.jpa.properties.hibernate.format_sql", () -> "true");
        registry.add("spring.jpa.hibernate.ddl-auto", () -> "create-only");
        
        registry.add("decorator.datasource.p6spy.enable-logging", () -> "true");
        registry.add("decorator.datasource.p6spy.multiline", () -> "true");
        registry.add("decorator.datasource.p6spy.logging", () -> "slf4j");
        registry.add("decorator.datasource.p6spy.log-file", () -> "test-spy.log");
    }
}
