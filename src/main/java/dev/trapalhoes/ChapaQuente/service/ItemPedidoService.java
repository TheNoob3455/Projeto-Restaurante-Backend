package dev.trapalhoes.ChapaQuente.service;

import dev.trapalhoes.ChapaQuente.model.ItemPedido;
import dev.trapalhoes.ChapaQuente.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemPedidoService {
    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public List<ItemPedido> getAll(){return itemPedidoRepository.findAll();}

    public Optional<ItemPedido> get(Integer id){return itemPedidoRepository.findById(id);}

    public ItemPedido save(ItemPedido itemPedido){return itemPedidoRepository.save(itemPedido);}

    public void delete(Integer id){
        itemPedidoRepository.deleteById(id);}
}
