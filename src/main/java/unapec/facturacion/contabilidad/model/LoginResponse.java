package unapec.facturacion.contabilidad.model;

import lombok.Data;

@Data
public class LoginResponse {
    public String token;
    public Usuario usuario;
}
