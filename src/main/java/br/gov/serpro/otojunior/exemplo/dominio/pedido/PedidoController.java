/**
 *
 */
package br.gov.serpro.otojunior.exemplo.dominio.pedido;

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
 * Controller REST para operações de CRUD de Pedido.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
    private final PedidoService pedidoService;

    /**
     * Lista todos os pedidos.
     * @return ResponseEntity contendo a lista de pedidos.
     */
    @GetMapping
    public ResponseEntity<List<Pedido>> listarTodos() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    /**
     * Obtém um pedido por seu ID.
     * @param id O ID do pedido a ser obtido.
     * @return ResponseEntity contendo o pedido, ou 404 se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obterPorId(@PathVariable final Long id) {
        return pedidoService.obterPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo pedido.
     * @param pedidoDto Os dados do pedido a ser criado.
     * @return ResponseEntity contendo o pedido criado e o URI de localização.
     */
    @PostMapping
    public ResponseEntity<Pedido> criar(@Valid @RequestBody final PedidoDto pedidoDto) {
        Pedido pedidoCriado = pedidoService.criar(pedidoDto);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(pedidoCriado.getId())
            .toUri();
        return ResponseEntity.created(location).body(pedidoCriado);
    }

    /**
     * Atualiza um pedido existente.
     * @param id O ID do pedido a ser atualizado.
     * @param pedidoDto Os dados para atualização do pedido.
     * @return ResponseEntity contendo o pedido atualizado, ou 404 se não encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Pedido> atualizar(
        @PathVariable final Long id,
        @Valid @RequestBody final PedidoDto pedidoDto) {
        return pedidoService.atualizar(id, pedidoDto)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Exclui um pedido por ID.
     * @param id O ID do pedido a ser excluído.
     * @return ResponseEntity com status 204 se excluído, ou 404 se não encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable final Long id) {
        return pedidoService.excluir(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
