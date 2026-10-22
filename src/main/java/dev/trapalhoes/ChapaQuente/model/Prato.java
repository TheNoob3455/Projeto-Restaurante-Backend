package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Prato")
@Getter
@Setter
@NoArgsConstructor
public class Prato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Prato;

    @ManyToOne
    @JoinColumn(name = "id_Produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_Pedido")
    private Pedido pedido;
}
