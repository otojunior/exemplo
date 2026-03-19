/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.infra.pessoafisica;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import jakarta.validation.constraints.NotBlank;

/**
 * 
 */
@Validated
@ConfigurationProperties(prefix = "exemplo.integr.pf")
record PessoaFisicaProperties(
    @NotBlank String nome) {
}
