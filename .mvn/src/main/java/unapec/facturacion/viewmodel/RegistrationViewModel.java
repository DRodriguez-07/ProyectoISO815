package unapec.facturacion.viewmodel;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import unapec.facturacion.domain.User;

@Data
public class RegistrationViewModel {
    @NotBlank
    private String username;
    @NotBlank
    @Size(min = 8, message = "La contraseña requiere de al menos 8 digitos")
    private String password;
    @NotBlank
    private String fullname;
    @Email
    private String email;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password),
                fullname, email);
    }
}
