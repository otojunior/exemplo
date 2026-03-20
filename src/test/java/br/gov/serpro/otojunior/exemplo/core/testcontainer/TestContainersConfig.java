/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.core.testcontainer;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.DynamicPropertyRegistrar;
import org.testcontainers.postgresql.PostgreSQLContainer;

/**
 * Configuração de TestContainers para testes de integração.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 19/03/2026 - SERPRO 2026
 */
@TestConfiguration
public class TestContainersConfig {
    /**
     * Bean do container PostgreSQL para testes de integração.
     * O método stop() será chamado automaticamente ao final dos testes.
     * @return o container PostgreSQL em execução
     */
    @Bean
    @SuppressWarnings("resource")
    PostgreSQLContainer postgresContainer() {
        return new PostgreSQLContainer("postgres:alpine")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");
    }

    /**
     * Registra as propriedades de conexão do PostgreSQL para os testes de integração.
     * As propriedades são obtidas diretamente do container em execução.
     * @param postgres o container PostgreSQL em execução
     * @return um registrador de propriedades dinâmico para configurar
     * a conexão com o banco de dados.
     */
    @Bean
    DynamicPropertyRegistrar postgresProps(final PostgreSQLContainer postgres) {
        return registry -> {
            registry.add("spring.datasource.url", postgres::getJdbcUrl);
            registry.add("spring.datasource.username", postgres::getUsername);
            registry.add("spring.datasource.password", postgres::getPassword);
        };
    }
}
