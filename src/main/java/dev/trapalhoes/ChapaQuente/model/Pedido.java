package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Pedido")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {

    public enum StatusPedido {
        AGUARDANDO_PAGAMENTO,
        EM_PREPARACAO,
        SAINDO_PARA_ENTREGA,
        CONCLUIDO
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Pedido;

    @ManyToOne
    @JoinColumn(name = "id_Cliente", nullable = false)
    private Cliente cliente;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false) // se não tiver desconto, colocar "0"
    private Double desconto;

    @Column(nullable = false)
    private Double valorTotal;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusPedido status;
}
