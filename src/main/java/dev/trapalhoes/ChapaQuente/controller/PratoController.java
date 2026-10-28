package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Pedido;
import dev.trapalhoes.ChapaQuente.model.Prato;
import dev.trapalhoes.ChapaQuente.model.Produto;
import dev.trapalhoes.ChapaQuente.service.PratoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prato")
@CrossOrigin(origins = "*")
public class PratoController {
    private final PratoService pratoService;

    public PratoController(PratoService pratoService) {
        this.pratoService = pratoService;
    }

    public record DadosPrato(
            Produto produto,
            Pedido pedido
    ) {}

    @GetMapping
    public List<Prato> getAll(){return pratoService.getAll();}

    @GetMapping("/{id}")
    public Optional<Prato> get(@PathVariable Integer id){return pratoService.get(id);}

    @PostMapping
    public Prato create(@RequestBody DadosPrato dadosPrato){
        Prato prato = new Prato();

        prato.setProduto(dadosPrato.produto());
        prato.setPedido(dadosPrato.pedido());

        return pratoService.save(prato);
    }
}
