package dev.trapalhoes.ChapaQuente.verification;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;


@Service
public class CaptchaVerification {
    private final RestClient restClient;
    private final String secretKey;


    public CaptchaVerification(RestClient.Builder builder, @Value("${cloudflare.turnstile.secret-key}") String secretKey) {
        this.restClient = builder.build();
        this.secretKey = secretKey;
    }

    public boolean verificar(String token) {
        if (token == null || token.isBlank()) {
            return false;
        }
        MultiValueMap<String, String> dados = new LinkedMultiValueMap<>();

        dados.add("secret", secretKey);
        dados.add("response", token);

        try {
            CaptchaResponse resposta = restClient.post().uri("https://challenges.cloudflare.com/turnstile/v0/siteverify").contentType(MediaType.APPLICATION_FORM_URLENCODED).body(dados).retrieve().body(CaptchaResponse.class);

            return resposta != null && resposta.success();

        } catch (RestClientException e) {
            return false;
        }
    }
        public record CaptchaResponse(boolean success) {}
}
