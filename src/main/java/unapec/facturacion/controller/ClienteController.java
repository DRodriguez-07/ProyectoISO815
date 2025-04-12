package unapec.facturacion.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unapec.facturacion.domain.Cliente;
import unapec.facturacion.repository.ClienteRepository;

@Slf4j
@Controller
@RequestMapping("/clientes")
public class ClienteController {
    private final ClienteRepository repository;

    @Autowired
    public ClienteController(ClienteRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("clientes", repository.findAll());
        return "cliente_list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        Cliente c = repository.findById(id).orElse(null);
        model.addAttribute("cliente", c);

        return "cliente_detail";
    }

    @GetMapping("/new")
    public String create(Model model) {
        Cliente c = new Cliente();
        model.addAttribute("cliente", c);
        return "cliente_create";
    }

    @PostMapping("/new")
    public String create(@Valid Cliente c, Errors errors) {
        if (errors.hasErrors()) {
            return "cliente_create";
        }

        repository.save(c);
        return "redirect:/clientes";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Cliente c = repository.findById(id).orElse(null);
        model.addAttribute("cliente", c);
        return "cliente_edit";
    }

    @PostMapping("/edit/{id}")
    public String editForm(@Valid Cliente c, Errors errors) {
        if(errors.hasErrors()) {
            return "cliente_edit";
        }

        repository.save(c);
        return "redirect:/clientes";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        Cliente c = repository.findById(id).orElse(null);
        if(c != null) {
            c.setEstado(Cliente.EstadoCliente.INACTIVO);
            repository.save(c);
        }

        return "redirect:/clientes";
    }
}
