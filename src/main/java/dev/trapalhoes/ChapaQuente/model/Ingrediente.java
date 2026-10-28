package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Ingrediente")
@Getter
@Setter
@NoArgsConstructor
public class Ingrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Ingrediente;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Integer estoque;

    @Column(nullable = false)
    private String unidade;
}
