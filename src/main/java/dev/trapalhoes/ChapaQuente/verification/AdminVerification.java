package dev.trapalhoes.ChapaQuente.verification;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/verificacao")
@CrossOrigin(origins = "*")
public class AdminVerification {
    @Value("${admin.email}")
    private String email;
    @Value("${admin.senha}")
    private String senha;

    public record DadosVerificacao(
            @NotBlank String email,
            @NotBlank String senha
    ){}

    @PostMapping("/admin")
    public Boolean verificarAdmin(@RequestBody @Valid DadosVerificacao dados){
        if(dados.email.equals(email) && dados.senha.equals(senha)){
            return true;
        }
        else {
            return false;
        }
    }
}
