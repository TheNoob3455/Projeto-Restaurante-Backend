package dev.trapalhoes.ChapaQuente.verification;

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
            String email,
            String senha
    ){}

    @PostMapping("/admin")
    public Boolean verificarAdmin(@RequestBody DadosVerificacao dados){
        if(dados.email.equals(email) && dados.senha.equals(senha)){
            return true;
        }
        else {
            return false;
        }
    }
}
