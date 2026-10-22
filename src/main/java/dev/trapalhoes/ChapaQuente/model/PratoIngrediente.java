package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "PratoIngrediente")
@Getter
@Setter
@NoArgsConstructor
public class PratoIngrediente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_PratoIngrediente;

    @ManyToOne
    @JoinColumn(name = "id_Prato")
    private Prato Prato;

    @ManyToOne
    @JoinColumn(name = "id_Ingrediente")
    private Ingrediente ingrediente;

    private Integer quantidade;
    private String unidade;
}
