package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Cliente;
import dev.trapalhoes.ChapaQuente.model.Mesa;
import dev.trapalhoes.ChapaQuente.model.Reserva;
import dev.trapalhoes.ChapaQuente.service.ReservaService;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reserva")
@CrossOrigin(origins = "*")
public class ReservaController {
    private final ReservaService reservaService;

    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }

    public record DadosReserva(
            Mesa mesa,
            Cliente cliente,
            Date dia_reserva,
            Time hora_reserva
    ) {}

    @GetMapping
    public List<Reserva> getAll(){return reservaService.getAll();}

    @GetMapping("/{id}")
    public Optional<Reserva> get(@PathVariable Integer id){return reservaService.get(id);}

    // incompleto
}
