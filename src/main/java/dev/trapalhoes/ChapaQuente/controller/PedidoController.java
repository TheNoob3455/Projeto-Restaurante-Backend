
package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Cliente;
import dev.trapalhoes.ChapaQuente.model.Pedido;
import dev.trapalhoes.ChapaQuente.service.PedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
            @NotNull Cliente cliente,
            @NotNull @Positive Double valor,
            @NotNull @PositiveOrZero Double desconto,
            @NotNull @PositiveOrZero Double valorTotal
    ) {}

    public record DadosStatusPedido(
            @NotNull Pedido.StatusPedido status
    ) {}

    @GetMapping
    public List<Pedido> getAll() {
        return pedidoService.getAll();
    }

    @GetMapping("/{id}")
    public Optional<Pedido> get(@PathVariable Integer id) {
        return pedidoService.get(id);
    }

    @PostMapping
    public Pedido create(@RequestBody @Valid DadosPedido dadosPedido) {
        Pedido pedido = new Pedido();

        pedido.setCliente(dadosPedido.cliente());
        pedido.setValor(dadosPedido.valor());
        pedido.setDesconto(dadosPedido.desconto());
        pedido.setValorTotal(dadosPedido.valorTotal());
        pedido.setStatus(Pedido.StatusPedido.AGUARDANDO_PAGAMENTO);

        return pedidoService.save(pedido);
    }

    @PatchMapping("/status/{id}")
    public Pedido atualizarStatus(@PathVariable Integer id, @RequestBody @Valid DadosStatusPedido dados) {
        return pedidoService.atualizarStatus(id, dados.status());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {pedidoService.delete(id);}
}
