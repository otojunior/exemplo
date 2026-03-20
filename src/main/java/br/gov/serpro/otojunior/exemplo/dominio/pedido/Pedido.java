/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.pedido;

import br.gov.serpro.otojunior.exemplo.dominio.cliente.Cliente;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * 
 */
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "pedido")
@NamedQueries({
    @NamedQuery(
        name = "Pedido.obterPorNumero",
    query = "select p from Pedido p where p.numero = :numero")})
public class Pedido implements java.io.Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @EqualsAndHashCode.Include
    @NotNull
    @Column(name = "numero", nullable = false, unique = true)
    private Long numero;
    
    @NotNull
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(
        name = "cliente_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
    private Cliente cliente;
}
