/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.infra.pessoafisica;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(PessoaFisicaProperties.class)
class PessoaFisicaPropertiesConfig { }
