package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "ItemPedido")
@Getter
@Setter
@NoArgsConstructor
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_itemPedido;

    @ManyToOne
    @JoinColumn(name = "id_Produto", nullable = false)
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_Pedido", nullable = false)
    private Pedido pedido;

    @Column(nullable = false)
    Integer quantidade;

    @Column(nullable = false)
    Double precoUnitario;
}
