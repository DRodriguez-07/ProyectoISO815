package unapec.facturacion.contabilidad.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
public class LoginRequest {
    @Value("${app.contabilidad.email}")
    public String email;
    @Value("${app.contabilidad.password}")
    public String password;
}
