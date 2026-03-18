/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.produto;

import java.io.Serial;
import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Entidade Produto
 * @author Oto Soares Coelho Junior (oto.coelho-junior@serpro.gov.br)
 * @since 18/03/2026 - SERPRO 2026
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "produto")
@NamedQueries({
    @NamedQuery(
        name = "Produto.obterPorCodigo",
        query = "select p from Produto p where p.codigo = :codigo")})
public class Produto implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @EqualsAndHashCode.Include
    @NotBlank
    @Size(min = 1, max = 10)
    @Column(name = "codigo", nullable = false, length = 10, unique = true)
    private String codigo;
    
    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;

    @Size(max = 100)
    @Column(name = "descricao", length = 100)
    private String descricao;
}
