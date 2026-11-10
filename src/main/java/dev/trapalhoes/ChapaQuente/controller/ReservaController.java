package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Cliente;
import dev.trapalhoes.ChapaQuente.model.Mesa;
import dev.trapalhoes.ChapaQuente.model.Reserva;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.RequestBody;
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
            @NotNull Mesa mesa,
            @NotNull Cliente cliente,
            @NotNull Date dia_reserva,
            @NotNull Time hora_reserva
    ) {
    }

    @GetMapping
    public List<Reserva> getAll() {
        return reservaService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Reserva> get(@PathVariable Integer id) {
        return reservaService.get(id);
    }

    @PostMapping
    public Reserva cadastrar(@RequestBody DadosReserva dados) {
        Reserva reserva = new Reserva();

        reserva.setMesa(dados.mesa());
        reserva.setCliente(dados.cliente());
        reserva.setDia_reserva(dados.dia_reserva());
        reserva.setHora_reserva(dados.hora_reserva());
        reserva.setStatus(Reserva.StatusReserva.ATIVA);

        return reservaService.save(reserva);
    }

    public record dadosStatus(
            @NotNull Reserva.StatusReserva status
    ) {
    }

    @PatchMapping("/status/{id}")
    public Reserva atualizar(@PathVariable Integer id, @RequestBody @Valid Reserva.StatusReserva dadosStatus) {
        return reservaService.atualizar(id, dadosStatus);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {reservaService.delete(id);}
}