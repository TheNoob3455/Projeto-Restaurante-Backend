package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "Cliente")
@Getter
@Setter
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Cliente;

    @ManyToOne
    @JoinColumn(name = "id_Pedido")
    private Pedido pedido;

    private String nome;
    private String senha;
    private String email;
    private String cpf;
}
