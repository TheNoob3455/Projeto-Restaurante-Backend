package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Ingrediente;
import dev.trapalhoes.ChapaQuente.service.IngredienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ingrediente")
@CrossOrigin(origins = "*")
public class IngredienteController {
    private final IngredienteService ingredienteService;

    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }

    public record DadosIngredientes(
            String nome,
            Integer estoque
    ){}

    @GetMapping
    public List<Ingrediente> getAll(){return ingredienteService.getAll();}

    @GetMapping("/{id}")
    public Optional<Ingrediente> get(@PathVariable Integer id){return ingredienteService.get(id);}

    @PostMapping
    public Ingrediente create(@RequestBody DadosIngredientes dadosIngredientes){
        Ingrediente ingrediente = new Ingrediente();

        ingrediente.setNome(dadosIngredientes.nome());
        ingrediente.setEstoque(dadosIngredientes.estoque());

        return ingredienteService.save(ingrediente);
    }
}
