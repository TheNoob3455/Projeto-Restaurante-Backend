package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Produto;
import dev.trapalhoes.ChapaQuente.service.ProdutoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {
    private ProdutoService produtoService;

    public ProdutoController (ProdutoService produtoService) {this.produtoService = produtoService;}

    @GetMapping
    public List<Produto> getAll(){return produtoService.getAll();}

    @GetMapping("/{id}")
    public Optional<Produto> get(@PathVariable Integer id){return produtoService.get(id);}

    @PostMapping
    public Produto create(@RequestBody Produto produto){return produtoService.save(produto);}
}
