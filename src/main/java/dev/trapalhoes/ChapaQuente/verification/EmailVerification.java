package dev.trapalhoes.ChapaQuente.verification;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;



@RestController
@RequestMapping("/verificacao")
@CrossOrigin(origins = "*")
public class EmailVerification{
    private final SecureRandom random = new SecureRandom();
    private final Map<String, CodigoPendente> pendentes = new ConcurrentHashMap<>();
    private record CodigoPendente(String codigo, Instant expiraEm, int tentativas) {}
    @Value("${email.remetente}")
    private String remetente;
    @Value("${email.senha}")
    private String senha;


    public record DadosVerificacao(
            String email,
            String codigoInformado
    ) {}

    public record DadosUsuario(
            String usuario,
            String email
    ) {}
    @GetMapping("/usuario")
    public void enviarCodigo(@RequestBody DadosUsuario dados) throws MessagingException {
        String usuario = dados.usuario();
        String email = dados.email();
        String codigo = String.format("%06d", random.nextInt(1_000_000));
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.connectiontimeout", "10000");
        props.put("mail.smtp.timeout", "10000");
        props.put("mail.smtp.writetimeout", "10000");
        Session sessao = Session.getInstance(props, new Authenticator() {
            @Override protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });
        MimeMessage mensagem = new MimeMessage(sessao);
        mensagem.setFrom(new InternetAddress(remetente));
        mensagem.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
        mensagem.setSubject("Código de verificação - Hamburgueria", "UTF-8");
        mensagem.setText("Olá, " + usuario + "!\n\nSeu código de verificação é: " + codigo
                + "\nEle expira em 5 minutos.\nSe não solicitou, ignore esta mensagem.", "UTF-8");
        Transport.send(mensagem);
        pendentes.put(email.toLowerCase(), new CodigoPendente(codigo, Instant.now().plusSeconds(300), 0));
    }

    @PostMapping("/verificado")
    public boolean verificarCodigoPost(@RequestBody DadosVerificacao dados) throws  MessagingException{
        return verificarCodigo(dados.email(), dados.codigoInformado());
    }
    public boolean verificarCodigo(String email, String codigoInformado) {
        if (email == null || codigoInformado == null) return false;
        String chave = email.toLowerCase();
        CodigoPendente atual = pendentes.get(chave);
        if (atual == null) return false;
        if (Instant.now().isAfter(atual.expiraEm()) || atual.tentativas() >= 5) {
            pendentes.remove(chave);
            return false;
        }
        if (atual.codigo().equals(codigoInformado.trim())) {
            pendentes.remove(chave);
            return true;
        }
        pendentes.replace(chave, atual, new CodigoPendente(atual.codigo(), atual.expiraEm(), atual.tentativas() + 1));
        return false;
    }

}
