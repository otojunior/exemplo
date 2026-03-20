/**
 * 
 */
package br.gov.serpro.otojunior.exemplo.dominio.cliente;

import java.io.Serial;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import br.gov.serpro.otojunior.exemplo.dominio.pedido.Pedido;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
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
@Table(name = "cliente")
@NamedQueries({
    @NamedQuery(
        name = "Cliente.obterPorCpf",
        query = "select c from Cliente c where c.cpf = :cpf")})
public class Cliente implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @EqualsAndHashCode.Include
    @NotBlank
    @Size(min = 11, max = 11)
    @Column(name = "cpf", nullable = false, length = 11, unique = true)
    private String cpf;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(name = "nome", nullable = false, length = 50)
    private String nome;
    
    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;
    
    @Setter(AccessLevel.NONE)
    @OneToMany(
        mappedBy = "cliente",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    private Set<Pedido> pedidos = new HashSet<>();
    
    /**
     * Adiciona um pedido ao cliente, estabelecendo a relação bidirecional
     * @param pedido o pedido a ser adicionado
     */
    public void adicionar(final Pedido pedido) {
        pedidos.add(pedido);
        pedido.setCliente(this);
    }
    
    /**
     * Remove um pedido do cliente, quebrando a relação bidirecional
     * @param pedido o pedido a ser removido
     */
    public void remover(final Pedido pedido) {
        pedidos.remove(pedido);
        pedido.setCliente(null);
    }
    
    /**
     * Retorna uma cópia imutável do conjunto de pedidos do cliente
     * @return um conjunto de pedidos
     */
    public Set<Pedido> getPedidos() {
        return Set.copyOf(pedidos);
    }
}
