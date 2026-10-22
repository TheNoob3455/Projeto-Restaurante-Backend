package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Pedido")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Pedido;

    @ManyToOne
    @JoinColumn(name = "id_Produto")
    private Produto produto;

    private Double valor;
    private Double desconto;
    private Double valorTotal;
}
