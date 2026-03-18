/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.produto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Classe de transferência de dados (DTO) para representar informações de um produto.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 18/03/2026 - SERPRO 2026 
 */
public record ProdutoDto(
    @NotBlank @Size(min = 1, max = 10) String codigo,
    @NotBlank @Size(min = 3, max = 50) String nome,
    @Size(max = 100) String descricao) { }