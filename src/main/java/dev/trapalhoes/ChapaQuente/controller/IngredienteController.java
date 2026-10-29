package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Ingrediente;
import dev.trapalhoes.ChapaQuente.service.IngredienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
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
           @NotNull String nome,
           @NotNull @PositiveOrZero Integer estoque,
           @NotNull String unidade
    ){}

    @GetMapping
    public List<Ingrediente> getAll(){return ingredienteService.getAll();}

    @GetMapping("/{id}")
    public Optional<Ingrediente> get(@PathVariable Integer id){return ingredienteService.get(id);}

    @PostMapping
    public Ingrediente create(@RequestBody @Valid DadosIngredientes dadosIngredientes){
        Ingrediente ingrediente = new Ingrediente();

        ingrediente.setNome(dadosIngredientes.nome());
        ingrediente.setEstoque(dadosIngredientes.estoque());
        ingrediente.setUnidade(dadosIngredientes.unidade());

        return ingredienteService.save(ingrediente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {ingredienteService.delete(id);}
}
