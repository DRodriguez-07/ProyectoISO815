package unapec.facturacion.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import unapec.facturacion.contabilidad.model.LoginRequest;
import unapec.facturacion.contabilidad.model.LoginResponse;

import java.util.Date;

@Service
@AllArgsConstructor
public class ContabilidadClient {
    private final String apiUrl = "https://iso810-contabilidad.azurewebsites.net/api";
    private WebClient webClient;
    private static String token;
    private static Date last_login;
    public LoginRequest loginRequest;

    @Autowired
    public ContabilidadClient(LoginRequest loginRequest) {
        this.loginRequest = loginRequest;
        this.webClient = WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
        refreshToken();
    }

    //Get example DO NOT USE.
    public String getBookById(Long id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{id}").build(id))
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public void refreshToken() {
        //last login not defined or old token
        if(last_login == null || token.isBlank() || last_login.getTime() >= (new Date().getTime() + (1 * 24 * 60 * 60 * 1000) )) {
            LoginResponse resp = webClient.post().uri(uriBuilder -> uriBuilder.path("/Autenticacion/login").build())
                    .bodyValue(loginRequest)
                    .retrieve()
                    .bodyToMono(LoginResponse.class)
                    .block();
            token = resp.getToken();
            last_login = new Date();

        }
    }
}
