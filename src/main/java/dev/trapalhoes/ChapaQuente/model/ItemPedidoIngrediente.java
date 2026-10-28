package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ItemPedidoIngrediente")
@Getter
@Setter
@NoArgsConstructor
public class ItemPedidoIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_ItemPedidoIngrediente;

    @ManyToOne
    @JoinColumn(name = "id_ItemPedido", nullable = false)
    private ItemPedido itemPedido;

    @ManyToOne
    @JoinColumn(name = "id_Ingrediente", nullable = false)
    private Ingrediente ingrediente;

    @Column(nullable = false)
    private Integer quantidade;
}
