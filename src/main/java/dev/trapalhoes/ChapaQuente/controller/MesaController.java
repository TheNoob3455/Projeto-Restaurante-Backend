package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Mesa;
import dev.trapalhoes.ChapaQuente.service.MesaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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

    public record DadosMesa(
            @NotNull @Positive Integer lugares
    ){}

    @GetMapping
    public List<Mesa> getAll(){return mesaService.getAll();}

    @GetMapping("/{id}")
    public Optional<Mesa> get(@PathVariable Integer id){return mesaService.get(id);}

    @PostMapping()
    public Mesa create(@RequestBody @Valid DadosMesa dadosMesa){
        Mesa mesa = new Mesa();
        mesa.setLugares(dadosMesa.lugares());
        return mesaService.save(mesa);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {mesaService.delete(id);}
}
