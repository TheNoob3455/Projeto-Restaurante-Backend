package dev.trapalhoes.ChapaQuente.controller;

import dev.trapalhoes.ChapaQuente.config.PasswordConfig;
import dev.trapalhoes.ChapaQuente.model.Cliente;
import dev.trapalhoes.ChapaQuente.service.ClienteService;
import dev.trapalhoes.ChapaQuente.verification.CaptchaVerification;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
@CrossOrigin(origins = "*")
public class ClienteController {
    private final ClienteService clienteService;
    private final PasswordConfig passwordConfig;
    private final CaptchaVerification captchaVerification;

    public ClienteController(ClienteService clienteService, PasswordConfig passwordConfig, CaptchaVerification captchaVerification) {
        this.clienteService = clienteService;
        this.passwordConfig = passwordConfig;
        this.captchaVerification = captchaVerification;
    }

    public record DadosClientes(
        @NotBlank String nome,
        @NotBlank String senha,
        @NotBlank String email,
        @NotBlank String cpf,
        @NotBlank String token
    ){}

    public record SenhaHash(
            String senha
    ){}

    @GetMapping
    public List<Cliente> getAll(){return clienteService.getAll();}

    @PostMapping("/{id}")
    public Cliente get(@PathVariable Integer id, String token){
        if(!captchaVerification.verificar(token)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Verificação do CAPTCHA inválida");
        }
        return clienteService.get(id);
    }

    @PostMapping("/hash")
    public String hash(@RequestBody SenhaHash senhaHash){return passwordConfig.passwordEncoder().encode(senhaHash.senha());}

    @PostMapping
    public Cliente create(@RequestBody @Valid DadosClientes dadosClientes){
        if(!captchaVerification.verificar(dadosClientes.token())){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Verificação do CAPTCHA inválida");
        }

        Cliente cliente = new Cliente();

        cliente.setNome(dadosClientes.nome());
        cliente.setSenha(passwordConfig.passwordEncoder().encode(dadosClientes.senha()));
        cliente.setEmail(dadosClientes.email());
        cliente.setCpf(dadosClientes.cpf());
        cliente.setPontos(0);

        return clienteService.save(cliente);
    }

    public record DadosPontos(
            @NotBlank @PositiveOrZero Integer pontos
    ){}

    @PostMapping("/pontos/{id}")
    public Cliente atualizarPontos(@PathVariable Integer id,@RequestBody @Valid DadosPontos dadosPontos){
        return clienteService.atualizarPontos(id, dadosPontos.pontos());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {clienteService.delete(id);}
}
