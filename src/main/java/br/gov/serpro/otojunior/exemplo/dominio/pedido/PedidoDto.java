/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.pedido;

import jakarta.validation.constraints.NotNull;

/**
 * Classe de transferência de dados (DTO) para representar informações de um pedido.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
public record PedidoDto(
    @NotNull Long numero,
    @NotNull Long clienteId) { }
