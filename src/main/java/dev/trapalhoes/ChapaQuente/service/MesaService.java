package dev.trapalhoes.ChapaQuente.service;

import dev.trapalhoes.ChapaQuente.model.Mesa;
import dev.trapalhoes.ChapaQuente.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {
    private final MesaRepository mesaRepository;

    public MesaService(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    public List<Mesa> getAll(){return mesaRepository.findAll();}

    public Optional<Mesa> get(Integer id) {return mesaRepository.findById(id);}

    public Mesa save (Mesa mesa){return mesaRepository.save(mesa);}

    public void delete(Integer id) {mesaRepository.deleteById(id);}
}
