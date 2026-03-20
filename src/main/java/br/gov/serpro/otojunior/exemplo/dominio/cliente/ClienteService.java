/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.cliente;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * Classe de serviço para operações relacionadas a clientes.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
@RequiredArgsConstructor
@Service
public class ClienteService {
    private final ClienteRepositorio clienteRepositorio;

    /**
     * Lista todos os clientes cadastrados.
     * @return Lista de clientes.
     */
    public List<Cliente> listarTodos() {
        return clienteRepositorio.findAll();
    }

    /**
     * Obtém um cliente pelo seu identificador.
     * @param id Identificador do cliente.
     * @return Optional contendo o cliente, se encontrado.
     */
    public Optional<Cliente> obterPorId(final Long id) {
        return clienteRepositorio.findById(id);
    }

    /**
     * Obtém um cliente pelo seu CPF.
     * @param cpf O CPF do cliente a ser buscado.
     * @return Um Optional contendo o cliente encontrado, ou vazio se não encontrado.
     */
    public Optional<Cliente> obterPorCpf(final String cpf) {
        return clienteRepositorio.obterPorCpf(cpf);
    }

    /**
     * Cria um novo cliente.
     * @param clienteDto Dados para criação do cliente.
     * @return Cliente criado.
     */
    public Cliente criar(final ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        preencherCliente(cliente, clienteDto);
        return clienteRepositorio.save(cliente);
    }

    /**
     * Atualiza um cliente existente.
     * @param id Identificador do cliente.
     * @param clienteDto Dados para atualização.
     * @return Optional contendo o cliente atualizado, se encontrado.
     */
    public Optional<Cliente> atualizar(final Long id, final ClienteDto clienteDto) {
        return clienteRepositorio
            .findById(id)
            .map(cliente -> {
                preencherCliente(cliente, clienteDto);
                return clienteRepositorio.save(cliente);
            });
    }

    /**
     * Exclui um cliente por id.
     * @param id Identificador do cliente.
     * @return true se o cliente existia e foi excluído; false caso contrário.
     */
    public boolean excluir(final Long id) {
        return clienteRepositorio
            .findById(id)
            .map(cliente -> {
                clienteRepositorio.delete(cliente);
                return true;
            })
            .orElse(false);
    }

    /**
     * Preenche os dados de um cliente com base em um ClienteDto.
     * @param cliente O cliente a ser preenchido.
     * @param clienteDto Os dados do cliente a serem usados para preenchimento.
     */
    private void preencherCliente(final Cliente cliente, final ClienteDto clienteDto) {
        cliente.setCpf(clienteDto.cpf());
        cliente.setNome(clienteDto.nome());
        cliente.setEmail(clienteDto.email());
    }
}
