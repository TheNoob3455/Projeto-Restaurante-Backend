package dev.trapalhoes.ChapaQuente.controller;


import dev.trapalhoes.ChapaQuente.model.Ingrediente;
import dev.trapalhoes.ChapaQuente.model.ItemPedido;
import dev.trapalhoes.ChapaQuente.service.ItemPedidoIngredienteService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/item-pedido-ingrediente")
@CrossOrigin(origins = "*")
public class ItemPedidoIngredienteController {
    private final ItemPedidoIngredienteService itemPedidoIngredienteService;

    public ItemPedidoIngredienteController(ItemPedidoIngredienteService itemPedidoIngredienteService) {
        this.itemPedidoIngredienteService = itemPedidoIngredienteService;
    }

    public record DadosItemPedidoIngrediente(
            @NotBlank ItemPedido itemPedido,
            @NotBlank Ingrediente ingrediente,
            @NotBlank @Positive Integer quantidade
    ) {}

    @GetMapping
    public List<dev.trapalhoes.ChapaQuente.model.ItemPedidoIngrediente> getAll(){return itemPedidoIngredienteService.getAll();}

    @GetMapping("/{id}")
    public Optional<dev.trapalhoes.ChapaQuente.model.ItemPedidoIngrediente> get(@PathVariable Integer id){return itemPedidoIngredienteService.get(id);}

    @PostMapping
    public dev.trapalhoes.ChapaQuente.model.ItemPedidoIngrediente create(@RequestBody @Valid DadosItemPedidoIngrediente dadosItemPedidoIngrediente){
        dev.trapalhoes.ChapaQuente.model.ItemPedidoIngrediente itemPedidoIngrediente = new dev.trapalhoes.ChapaQuente.model.ItemPedidoIngrediente();

        itemPedidoIngrediente.setItemPedido(dadosItemPedidoIngrediente.itemPedido());
        itemPedidoIngrediente.setIngrediente(dadosItemPedidoIngrediente.ingrediente());
        itemPedidoIngrediente.setQuantidade(dadosItemPedidoIngrediente.quantidade());

        return this.itemPedidoIngredienteService.save(itemPedidoIngrediente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {itemPedidoIngredienteService.delete(id);}
}
