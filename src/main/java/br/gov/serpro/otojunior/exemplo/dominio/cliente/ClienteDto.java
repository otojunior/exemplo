/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Classe de transferência de dados (DTO) para representar informações de um cliente.
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 20/03/2026 - SERPRO 2026
 */
public record ClienteDto(
    @NotBlank @Size(min = 11, max = 11) String cpf,
    @NotBlank @Size(min = 3, max = 50) String nome,
    @Size(max = 100) String email) { }
