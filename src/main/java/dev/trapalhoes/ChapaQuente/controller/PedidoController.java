package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Pedido;
import dev.trapalhoes.ChapaQuente.service.PedidoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/pedido")
@CrossOrigin(origins = "*")
public class PedidoController {
    private PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @GetMapping
    public List<Pedido> getAll(){return pedidoService.getAll();}

    @GetMapping("/{id}")
    public Optional<Pedido> get(@PathVariable Integer id){return pedidoService.get(id);}

    @PostMapping
    public Pedido create(@RequestBody Pedido pedido){return pedidoService.save(pedido);}
}
