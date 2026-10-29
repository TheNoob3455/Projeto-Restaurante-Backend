package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Pedido;
import dev.trapalhoes.ChapaQuente.model.ItemPedido;
import dev.trapalhoes.ChapaQuente.model.Produto;
import dev.trapalhoes.ChapaQuente.service.ItemPedidoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item-pedido")
@CrossOrigin(origins = "*")
public class ItemPedidoController {
    private final ItemPedidoService itemPedidoService;

    public ItemPedidoController(ItemPedidoService itemPedidoService) {
        this.itemPedidoService = itemPedidoService;
    }

    public record DadosItemPedido(
            @NotNull Produto produto,
            @NotNull Pedido pedido,
            @NotNull @Positive Integer quantidade,
            @NotNull @PositiveOrZero Double precoUnitario
    ) {}

    @GetMapping
    public List<ItemPedido> getAll(){return itemPedidoService.getAll();}

    @GetMapping("/{id}")
    public Optional<ItemPedido> get(@PathVariable Integer id){return itemPedidoService.get(id);}

    @PostMapping
    public ItemPedido create(@RequestBody @Valid DadosItemPedido dadosItemPedido){
        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setProduto(dadosItemPedido.produto());
        itemPedido.setPedido(dadosItemPedido.pedido());
        itemPedido.setQuantidade(dadosItemPedido.quantidade());
        itemPedido.setPrecoUnitario(dadosItemPedido.precoUnitario());

        return itemPedidoService.save(itemPedido);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {itemPedidoService.delete(id);}
}
