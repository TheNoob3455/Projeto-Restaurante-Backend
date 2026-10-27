package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name = "Mesa")
@Setter
@Getter
@NoArgsConstructor
public class Mesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_Mesa;

    @ManyToOne
    @JoinColumn(table = "id_cliente")
    Cliente cliente;

    private Boolean reservado = false;
    private Date dia_Reservado;
    private Time hora_Reservada;
}
