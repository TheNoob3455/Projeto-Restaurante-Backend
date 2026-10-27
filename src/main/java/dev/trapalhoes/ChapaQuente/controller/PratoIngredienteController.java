package dev.trapalhoes.ChapaQuente.controller;


import dev.trapalhoes.ChapaQuente.model.PratoIngrediente;
import dev.trapalhoes.ChapaQuente.service.PratoIngredienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pratoingrediente")
@CrossOrigin(origins = "*")
public class PratoIngredienteController {
    private PratoIngredienteService pratoIngredienteService;

    public PratoIngredienteController(PratoIngredienteService pratoIngredienteService) {
        this.pratoIngredienteService = pratoIngredienteService;
    }

    @GetMapping
    public List<PratoIngrediente> getAll(){return pratoIngredienteService.getAll();}

    @GetMapping("/{id}")
    public Optional<PratoIngrediente> get(@PathVariable Integer id){return pratoIngredienteService.get(id);}

    @PostMapping
    public PratoIngrediente create(@RequestBody PratoIngrediente pratoIngrediente){return pratoIngredienteService.save(pratoIngrediente);}
}
