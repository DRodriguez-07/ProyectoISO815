package unapec.facturacion.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import unapec.facturacion.contabilidad.model.DetallesEntradaContable;
import unapec.facturacion.contabilidad.model.EntradaContable;
import unapec.facturacion.contabilidad.model.LoginRequest;
import unapec.facturacion.contabilidad.model.LoginResponse;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class ContabilidadClient {
    private final String apiUrl = "https://iso810-contabilidad.azurewebsites.net/api";
    private static final String TOKEN = "eyJhbGciOiJodHRwOi8vd3d3LnczLm9yZy8yMDAxLzA0L3htbGRzaWctbW9yZSNobWFjLXNoYTI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiRmFjdHVyYWNpb24iLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjY3ZmE0OWJlZDkzNjFmNDAzZjlkNjNhMiIsIlNpc3RlbWFBdXhpbGlhcklkIjoiNjdkMGEwNDY3ZGEzYTNmMDQzZDc5NTIzIiwibmJmIjoxNzQ0Nzc3MzE3LCJleHAiOjE3NDczNjkzMTd9.YV5MUlzkSvSTg--EIiKbaA9P-d158b4gKvm1YIwGswM";
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


    public EntradaContable guardarEntradaContable(EntradaContable entradaContable)
    {
        try
        {
            refreshToken();
            return webClient.post()
                    .uri(uriBuilder -> uriBuilder.path("/EntradaContable").build())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .bodyValue(entradaContable)
                    .retrieve()
                    .bodyToMono(EntradaContable.class)
                    .block();
        } catch (Exception e) {
            throw new RuntimeException("Error tipo [" + e.getClass().getSimpleName() + "] al guardar entrada contable: " + e.getMessage(), e);
        }

    }

    public void agregarCuentaContable(String fechaAsiento, String descripcion, String cuentaId, String TipoMovimientoDB, String TipoMovimientoCR, double Montoasiento) {
        try {
            // Crear datos del asiento
            EntradaContable entrada = new EntradaContable();
            entrada.setDescripcion(descripcion);
            entrada.setSistemaAuxiliarId("3");
            entrada.setFecha(fechaAsiento);

            DetallesEntradaContable d1 = new DetallesEntradaContable();
            d1.setCuentaId(cuentaId);
            d1.setTipoMovimiento(TipoMovimientoDB);
            d1.setMontoAsiento(Montoasiento);

            DetallesEntradaContable d2 = new DetallesEntradaContable();
            d2.setCuentaId(cuentaId);
            d2.setTipoMovimiento(TipoMovimientoCR);
            d2.setMontoAsiento(Montoasiento);

            entrada.setDetalles(List.of(d1, d2));

            guardarEntradaContable(entrada);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
