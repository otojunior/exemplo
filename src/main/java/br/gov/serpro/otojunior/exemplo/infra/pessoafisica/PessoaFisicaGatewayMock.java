/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.infra.pessoafisica;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

/**
 * 
 */
@RequiredArgsConstructor
@Service
class PessoaFisicaGatewayMock implements PessoaFisicaGateway {
    private final PessoaFisicaProperties prop;
    
    @Override
    public PessoaFisicaDto obterPorCpf(final String cpf) {
        final var mockcpf = cpf;
        final var mocknome = prop.nome() + ":" + cpf;
        final var mockemail = cpf + "@exemplo.com";
        
        return new PessoaFisicaDto(
            mockcpf,
            mocknome,
            mockemail);
    }
}
