package dev.trapalhoes.ChapaQuente.controller;


import dev.trapalhoes.ChapaQuente.model.Ingrediente;
import dev.trapalhoes.ChapaQuente.model.Prato;
import dev.trapalhoes.ChapaQuente.model.PratoIngrediente;
import dev.trapalhoes.ChapaQuente.service.PratoIngredienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pratoingrediente")
@CrossOrigin(origins = "*")
public class PratoIngredienteController {
    private final PratoIngredienteService pratoIngredienteService;

    public PratoIngredienteController(PratoIngredienteService pratoIngredienteService) {
        this.pratoIngredienteService = pratoIngredienteService;
    }

    public record DadosPratoIngrediente(
            Prato prato,
            Ingrediente ingrediente,
            Integer quantidade,
            String unidade
    ) {}

    @GetMapping
    public List<PratoIngrediente> getAll(){return pratoIngredienteService.getAll();}

    @GetMapping("/{id}")
    public Optional<PratoIngrediente> get(@PathVariable Integer id){return pratoIngredienteService.get(id);}

    @PostMapping
    public PratoIngrediente create(@RequestBody DadosPratoIngrediente dadosPratoIngrediente){
        PratoIngrediente pratoIngrediente = new PratoIngrediente();

        pratoIngrediente.setPrato(dadosPratoIngrediente.prato());
        pratoIngrediente.setIngrediente(dadosPratoIngrediente.ingrediente());
        pratoIngrediente.setQuantidade(dadosPratoIngrediente.quantidade());
        pratoIngrediente.setUnidade(dadosPratoIngrediente.unidade());

        return pratoIngredienteService.save(pratoIngrediente);
    }
}
