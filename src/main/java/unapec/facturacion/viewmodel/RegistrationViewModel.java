package unapec.facturacion.viewmodel;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import unapec.facturacion.domain.User;

@Data
public class RegistrationViewModel {
    private String username;
    private String password;
    private String fullname;
    private String email;

    public User toUser(PasswordEncoder passwordEncoder) {
        return new User(username, passwordEncoder.encode(password),
                fullname, email);
    }
}
