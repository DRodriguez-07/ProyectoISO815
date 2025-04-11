package unapec.facturacion.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web
        .builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class SecurityConfig {
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) ->
                        authz.requestMatchers("/css/**").permitAll()
                                .requestMatchers("/images/**").permitAll()
                                .requestMatchers("/fonts/**").permitAll()
                                .requestMatchers("/scripts/**").permitAll()
                                .anyRequest().authenticated())

                .logout((logout) -> logout.logoutSuccessUrl("/").permitAll()).formLogin(form ->
                        form.loginPage("/login")
                                //.loginProcessingUrl("/authenticate")
                                .defaultSuccessUrl("/admin/dashboard",true)
                                .permitAll());
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
