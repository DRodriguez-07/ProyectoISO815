package unapec.facturacion.configuration;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import unapec.facturacion.domain.User;
import unapec.facturacion.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class UserSeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private static final Logger log = LoggerFactory.getLogger(UserSeeder.class);

    @Autowired
    public UserSeeder(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(userRepository.findByUsername("admin") == null) {
            seedAdmin();
        }
    }

    public void seedAdmin() {
        log.info("Seeding admin users");
        User user = new User("admin", passwordEncoder.encode("admin"), "Admin", "admin@fakeemail.com");
        userRepository.save(user);
        log.info("Admin user created. user: admin|pass:admin");
    }
}
