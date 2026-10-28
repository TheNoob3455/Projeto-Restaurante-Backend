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
    private final PedidoService pedidoService;

    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    public record DadosPedido(
            Double valor,
            Double desconto,
            Double valorTotal
    ) {}

    @GetMapping
    public List<Pedido> getAll(){return pedidoService.getAll();}

    @GetMapping("/{id}")
    public Optional<Pedido> get(@PathVariable Integer id){return pedidoService.get(id);}

    @PostMapping
    public Pedido create(@RequestBody DadosPedido dadosPedido){
        Pedido pedido = new Pedido();

        pedido.setValor(dadosPedido.valor());
        pedido.setDesconto(dadosPedido.desconto());
        pedido.setValorTotal(dadosPedido.valorTotal());

        return pedidoService.save(pedido);
    }
}
