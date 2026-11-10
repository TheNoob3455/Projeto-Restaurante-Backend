package dev.trapalhoes.ChapaQuente.service;

import dev.trapalhoes.ChapaQuente.model.Reserva;
import dev.trapalhoes.ChapaQuente.repository.ReservaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {
    private final ReservaRepository reservaRepository;

    public ReservaService(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public List<Reserva> getAll(){return reservaRepository.findAll();}

    public Optional<Reserva> get(Integer id){return reservaRepository.findById(id);}

    public Reserva save(Reserva reserva){return reservaRepository.save(reserva);}

    public Reserva atualizar(Integer id, Reserva.StatusReserva statusReserva){
        Reserva reserva = reservaRepository.findById(id).orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
        reserva.setStatus(statusReserva);
        return reservaRepository.save(reserva);
    }

    public void delete(Integer id){reservaRepository.deleteById(id);}
}
