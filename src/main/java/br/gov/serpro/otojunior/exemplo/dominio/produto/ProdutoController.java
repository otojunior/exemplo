/**
 *
 */
package br.gov.serpro.otojunior.exemplo.dominio.produto;

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
 * Controller REST para operações de CRUD de Produto.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 18/03/2026 - SERPRO 2026
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
    private final ProdutoService produtoService;

    /**
     * Lista todos os produtos.
     * @return ResponseEntity contendo a lista de produtos.
     */
    @GetMapping
    public ResponseEntity<List<Produto>> listarTodos() {
        return ResponseEntity.ok(produtoService.listarTodos());
    }

    /**
     * Obtém um produto por seu ID.
     * @param id O ID do produto a ser obtido.
     * @return ResponseEntity contendo o produto, ou 404 se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Produto> obterPorId(@PathVariable final Long id) {
        return produtoService.obterPorId(id)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Cria um novo produto.
     * @param produtoDto Os dados do produto a ser criado.
     * @return ResponseEntity contendo o produto criado e o URI de localização.
     */
    @PostMapping
    public ResponseEntity<Produto> criar(@Valid @RequestBody final ProdutoDto produtoDto) {
        Produto produtoCriado = produtoService.criar(produtoDto);
        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(produtoCriado.getId())
            .toUri();
        return ResponseEntity.created(location).body(produtoCriado);
    }

    /**
     * Atualiza um produto existente.
     * @param id O ID do produto a ser atualizado.
     * @param produtoDto Os dados para atualização do produto.
     * @return ResponseEntity contendo o produto atualizado, ou 404 se não encontrado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(
        @PathVariable final Long id,
        @Valid @RequestBody final ProdutoDto produtoDto) {
        return produtoService.atualizar(id, produtoDto)
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Exclui um produto por ID.
     * @param id O ID do produto a ser excluído.
     * @return ResponseEntity com status 204 se excluído, ou 404 se não encontrado.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable final Long id) {
        return produtoService.excluir(id)
            ? ResponseEntity.noContent().build()
            : ResponseEntity.notFound().build();
    }
}
