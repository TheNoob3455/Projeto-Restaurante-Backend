package dev.trapalhoes.ChapaQuente.service;

import dev.trapalhoes.ChapaQuente.repository.ItemPedidoIngredienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoIngredienteService {
    private final ItemPedidoIngredienteRepository itemPedidoIngredienteRepository;

    public ItemPedidoIngredienteService(ItemPedidoIngredienteRepository itemPedidoIngredienteRepository) {
        this.itemPedidoIngredienteRepository = itemPedidoIngredienteRepository;
    }

    public List<dev.trapalhoes.ChapaQuente.model.ItemPedidoIngrediente> getAll(){return itemPedidoIngredienteRepository.findAll();}

    public Optional<dev.trapalhoes.ChapaQuente.model.ItemPedidoIngrediente> get(Integer id){return itemPedidoIngredienteRepository.findById(id);}

    public dev.trapalhoes.ChapaQuente.model.ItemPedidoIngrediente save(dev.trapalhoes.ChapaQuente.model.ItemPedidoIngrediente itemPedidoIngrediente){return this.itemPedidoIngredienteRepository.save(itemPedidoIngrediente);}

    public void delete(Integer id){
        itemPedidoIngredienteRepository.deleteById(id);}
}
