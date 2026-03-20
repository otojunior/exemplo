/**
 *
 */
package br.gov.serpro.otojunior.exemplo.dominio.cliente;

import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * Controller REST para operações de CRUD de Cliente.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    private final ClienteService clienteService;

    /**
     * Lista todos os clientes.
     * @return ResponseEntity contendo a lista de clientes.
     */
    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        return ResponseEntity.ok(clienteService.listarTodos());
    }

    /**
     * Obtém um cliente por seu ID.
     * @param id O ID do cliente a ser obtido.
     * @return ResponseEntity contendo o cliente, ou 404 se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obterPorId(@PathVariable final Long id) {
        return clienteService.obterPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo cliente.
     * @param clienteDto Os dados do cliente a ser criado.
     * @return ResponseEntity contendo o cliente criado e o URI de localização.
     */
    @PostMapping
    public ResponseEntity<Cliente> criar(@Valid @RequestBody final ClienteDto clienteDto) {
        Cliente clienteCriado = clienteService.criar(clienteDto);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(clienteCriado.getId())
            .toUri();
        return ResponseEntity.created(location).body(clienteCriado);
    }

    /**
     * Atualiza um cliente existente.
     * @param id O ID do cliente a ser atualizado.
     * @param clienteDto Os dados para atualização do cliente.
     * @return ResponseEntity contendo o cliente atualizado, ou 404 se não encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(
        @PathVariable final Long id,
        @Valid @RequestBody final ClienteDto clienteDto) {
        return clienteService.atualizar(id, clienteDto)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Exclui um cliente por ID.
     * @param id O ID do cliente a ser excluído.
     * @return ResponseEntity com status 204 se excluído, ou 404 se não encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable final Long id) {
        return clienteService.excluir(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
