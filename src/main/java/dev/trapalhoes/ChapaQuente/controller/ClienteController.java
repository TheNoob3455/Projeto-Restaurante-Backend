package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.config.PasswordConfig;
import dev.trapalhoes.ChapaQuente.model.Cliente;
import dev.trapalhoes.ChapaQuente.service.ClienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
    private final ClienteService clienteService;
    private final PasswordConfig passwordConfig;

    public ClienteController(ClienteService clienteService, PasswordConfig passwordConfig) {
        this.clienteService = clienteService;
        this.passwordConfig = passwordConfig;
    }

    public record DadosClientes(
        String nome,
        String senha,
        String email,
        String cpf
    ){}

    public record SenhaHash(
            String senha
    ){}

    @GetMapping
    public List<Cliente> getAll(){return clienteService.getAll();}

    @GetMapping("/{id}")
    public Optional<Cliente> get(@PathVariable Integer id){return clienteService.get(id);}

    @PostMapping("/hash")
    public String hash(SenhaHash senhaHash){return passwordConfig.passwordEncoder().encode(senhaHash.senha());}

    @PostMapping
    public Cliente create(@RequestBody DadosClientes dadosClientes){
        Cliente cliente = new Cliente();

        cliente.setNome(dadosClientes.nome());
        cliente.setSenha(passwordConfig.passwordEncoder().encode(dadosClientes.senha()));
        cliente.setEmail(dadosClientes.email());
        cliente.setCpf(dadosClientes.cpf());

        return clienteService.save(cliente);
    }

}
