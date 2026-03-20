/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.cliente;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import br.gov.serpro.otojunior.exemplo.core.testcontainer.TestContainersIntegrationTest;

/**
 * 
 */
@SpringBootTest
@TestContainersIntegrationTest
class ClienteServiceIT {
    @MockitoSpyBean
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ClienteService clienteService;

    /**
     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.cliente.ClienteService#listarTodos()}.
     */
    @Test
    void testListarTodos() {
        for (int i = 0; i < 3; i++) {
            var c = new Cliente();
            c.setCpf("1234567890" + i);
            c.setNome("Cliente" + i);
            c.setEmail("cliente" + i + "@exemplo.com");
            clienteRepositorio.save(c);
        }

        var clientes = clienteService.listarTodos();

        assertEquals(3, clientes.size());
        for (int i = 0; i < 3; i++) {
            var cliente = clientes.get(i);
            assertEquals("1234567890" + i, cliente.getCpf());
            assertEquals("Cliente" + i, cliente.getNome());
            assertEquals("cliente" + i + "@exemplo.com", cliente.getEmail());
        }
    }
}
