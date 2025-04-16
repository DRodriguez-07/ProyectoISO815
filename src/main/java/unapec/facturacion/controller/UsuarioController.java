package unapec.facturacion.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unapec.facturacion.domain.User;
import unapec.facturacion.repository.UserRepository;
import unapec.facturacion.service.ContabilidadClient;
import unapec.facturacion.viewmodel.RegistrationViewModel;

@Slf4j
@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UsuarioController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("usuarios", userRepository.findAll());
        return "usuario_list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("usuario", user);

        return "usuario_detail";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("usuario", new RegistrationViewModel());
        return "usuario_create";
    }

    @PostMapping("/new")
    public String createForm(@Valid RegistrationViewModel form, Errors errors) {
        if(errors.hasErrors()) {
            return "usuario_create";
        }
        userRepository.save(form.toUser(passwordEncoder));

        return "redirect:/usuarios";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("usuario", user);
        return "usuario_edit";
    }

    @PostMapping("/edit/{id}")
    public String editForm(@Valid RegistrationViewModel form, Errors errors) {
        if(errors.hasErrors()) {
            return "usuario_edit";
        }

        userRepository.save(form.toUser(passwordEncoder));

        return "redirect:/usuarios";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        User user = userRepository.findById(id).orElse(null);
        if(user != null) {
            user.setEstado(User.EstadoUsuario.INACTIVO);
            userRepository.save(user);
        }

        return "redirect:/usuarios";
    }
}
