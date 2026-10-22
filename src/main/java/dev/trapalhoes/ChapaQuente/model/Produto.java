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

    private Double valor;
    private String nome;
    private String tipo_Produto;
    private Integer quantidade;
}
