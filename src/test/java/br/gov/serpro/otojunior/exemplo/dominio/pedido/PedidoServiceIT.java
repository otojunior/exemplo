/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.pedido;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;
import br.gov.serpro.otojunior.exemplo.core.testcontainer.TestContainersIntegrationTest;
import br.gov.serpro.otojunior.exemplo.dominio.cliente.Cliente;
import br.gov.serpro.otojunior.exemplo.dominio.cliente.ClienteRepositorio;

/**
 * 
 */
@SpringBootTest
@TestContainersIntegrationTest
class PedidoServiceIT {
    @MockitoSpyBean
    private PedidoRepositorio pedidoRepositorio;

    @MockitoSpyBean
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PedidoService pedidoService;

    /**
     * Test method for {@link br.gov.serpro.otojunior.exemplo.dominio.pedido.PedidoService#listarTodos()}.
     */
    @Test
    void testListarTodos() {
        for (int i = 0; i < 3; i++) {
            var c = new Cliente();
            c.setCpf("1234567890" + i);
            c.setNome("Cliente" + i);
            c.setEmail("cliente" + i + "@exemplo.com");
            c = clienteRepositorio.save(c);

            var p = new Pedido();
            p.setNumero(1000L + i);
            p.setCliente(c);
            pedidoRepositorio.save(p);
        }

        var pedidos = pedidoService.listarTodos();

        assertEquals(3, pedidos.size());
        for (int i = 0; i < 3; i++) {
            var pedido = pedidos.get(i);
            assertEquals(1000L + i, pedido.getNumero());
            assertEquals("1234567890" + i, pedido.getCliente().getCpf());
        }
    }
}
