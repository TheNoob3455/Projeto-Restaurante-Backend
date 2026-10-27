package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Mesa;
import dev.trapalhoes.ChapaQuente.service.MesaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mesa")
@CrossOrigin(origins = "*")
public class MesaController {
    private final MesaService mesaService;

    public MesaController(MesaService mesaService) {
        this.mesaService = mesaService;
    }

    @GetMapping
    public List<Mesa> getAll(){return mesaService.getAll();}

    @GetMapping("/{id}")
    public Optional<Mesa> get(@PathVariable Integer id){return mesaService.get(id);}

    @PostMapping
    public Mesa create(@RequestBody Mesa mesa){return mesaService.save(mesa);}
}
