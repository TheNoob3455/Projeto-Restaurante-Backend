package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Produto")
@Getter
@Setter
@NoArgsConstructor
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Produto;

    @Column(nullable = false)
    private Double valor;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo_Produto;

    @Column(nullable = false)
    private Integer quantidade;
}
