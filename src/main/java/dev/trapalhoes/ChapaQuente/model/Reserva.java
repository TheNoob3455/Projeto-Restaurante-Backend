package dev.trapalhoes.ChapaQuente.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name = "reserva")
@Setter
@Getter
@NoArgsConstructor
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_rerserva;

    @ManyToOne
    @JoinColumn(name = "id_mesa")
    Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    Cliente cliente;

    Date dia_reserva;
    Time hora_reserva;
}
