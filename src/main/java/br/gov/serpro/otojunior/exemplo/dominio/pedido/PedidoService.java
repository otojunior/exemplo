/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.pedido;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import br.gov.serpro.otojunior.exemplo.dominio.cliente.Cliente;
import br.gov.serpro.otojunior.exemplo.dominio.cliente.ClienteRepositorio;
import lombok.RequiredArgsConstructor;

/**
 * Classe de serviço para operações relacionadas a pedidos.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
@RequiredArgsConstructor
@Service
public class PedidoService {
    private final PedidoRepositorio pedidoRepositorio;
    private final ClienteRepositorio clienteRepositorio;

    /**
     * Lista todos os pedidos cadastrados.
     * @return Lista de pedidos.
     */
    public List<Pedido> listarTodos() {
        return pedidoRepositorio.findAll();
    }

    /**
     * Obtém um pedido pelo seu identificador.
     * @param id Identificador do pedido.
     * @return Optional contendo o pedido, se encontrado.
     */
    public Optional<Pedido> obterPorId(final Long id) {
        return pedidoRepositorio.findById(id);
    }

    /**
     * Obtém um pedido pelo seu número.
     * @param numero O número do pedido a ser buscado.
     * @return Um Optional contendo o pedido encontrado, ou vazio se não encontrado.
     */
    public Optional<Pedido> obterPorNumero(final Long numero) {
        return pedidoRepositorio.obterPorNumero(numero);
    }

    /**
     * Cria um novo pedido.
     * @param pedidoDto Dados para criação do pedido.
     * @return Pedido criado.
     */
    public Pedido criar(final PedidoDto pedidoDto) {
        Pedido pedido = new Pedido();
        preencherPedido(pedido, pedidoDto);
        return pedidoRepositorio.save(pedido);
    }

    /**
     * Atualiza um pedido existente.
     * @param id Identificador do pedido.
     * @param pedidoDto Dados para atualização.
     * @return Optional contendo o pedido atualizado, se encontrado.
     */
    public Optional<Pedido> atualizar(final Long id, final PedidoDto pedidoDto) {
        return pedidoRepositorio
            .findById(id)
            .map(pedido -> {
                preencherPedido(pedido, pedidoDto);
                return pedidoRepositorio.save(pedido);
            });
    }

    /**
     * Exclui um pedido por id.
     * @param id Identificador do pedido.
     * @return true se o pedido existia e foi excluído; false caso contrário.
     */
    public boolean excluir(final Long id) {
        return pedidoRepositorio
            .findById(id)
            .map(pedido -> {
                pedidoRepositorio.delete(pedido);
                return true;
            })
            .orElse(false);
    }

    /**
     * Preenche os dados de um pedido com base em um PedidoDto.
     * @param pedido O pedido a ser preenchido.
     * @param pedidoDto Os dados do pedido a serem usados para preenchimento.
     */
    private void preencherPedido(final Pedido pedido, final PedidoDto pedidoDto) {
        pedido.setNumero(pedidoDto.numero());
        Cliente cliente = clienteRepositorio
            .findById(pedidoDto.clienteId())
            .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
        pedido.setCliente(cliente);
    }
}
