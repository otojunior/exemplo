/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.core.testcontainer;

import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
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
    @Bean(destroyMethod = "stop")
    @ServiceConnection
    PostgreSQLContainer postgresContainer() {
        return new PostgreSQLContainer("postgres:alpine")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");
    }
}
