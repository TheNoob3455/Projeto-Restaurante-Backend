package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.model.Produto;
import dev.trapalhoes.ChapaQuente.service.ProdutoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "*")
public class ProdutoController {
    private final ProdutoService produtoService;

    public ProdutoController (ProdutoService produtoService) {this.produtoService = produtoService;}

    public record DadosProduto(
            @NotNull @Positive Double valor,
            @NotBlank String nome,
            @NotBlank String tipo_produto,
            @NotNull @PositiveOrZero Integer quantidade
    ) {}

    @GetMapping
    public List<Produto> getAll(){return produtoService.getAll();}

    @GetMapping("/{id}")
    public Optional<Produto> get(@PathVariable Integer id){return produtoService.get(id);}

    @PostMapping
    public Produto create(@RequestBody @Valid DadosProduto dadosProduto){
        Produto produto = new Produto();

        produto.setValor(dadosProduto.valor());
        produto.setNome(dadosProduto.nome());
        produto.setTipo_Produto(dadosProduto.tipo_produto());
        produto.setQuantidade(dadosProduto.quantidade());

        return produtoService.save(produto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {produtoService.delete(id);}
}
