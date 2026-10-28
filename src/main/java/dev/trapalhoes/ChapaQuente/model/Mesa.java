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

    private Integer lugares;
    private Boolean reservado = false;
}
