package unapec.facturacion.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unapec.facturacion.domain.Vendedor;
import unapec.facturacion.repository.VendedorRepository;
import jakarta.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/vendedores")
public class VendedorController {
    private final VendedorRepository repository;

    public VendedorController(VendedorRepository repository) {
        this.repository = repository;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("vendedores", repository.findAll());
        return "vendedor_list";
    }

    @GetMapping("/{id}")
    public String details(Model model, @PathVariable Long id) {
        Vendedor vendedor = repository.findById(id).orElse(null);
        model.addAttribute("vendedor", vendedor);
        return "vendedor_detail";
    }

    @GetMapping("/new")
    public String create(Model model) {
        Vendedor v = new Vendedor();
        model.addAttribute("vendedor", v);
        return "vendedor_create";
    }

    @PostMapping("/new")
    public String create(@Valid Vendedor vendedor, Errors errors) {
        if(errors.hasErrors()) {
            return "vendedor_create";
        }

        repository.save(vendedor);

        return "redirect:/vendedores";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        Vendedor v = repository.findById(id).orElse(null);
        model.addAttribute("vendedor", v);
        return "vendedor_edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid Vendedor vendedor, Errors errors) {
        if(errors.hasErrors()) {
            return "vendedor_edit";
        }

        repository.save(vendedor);
        return "redirect:/vendedores";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        Vendedor vendedor = repository.findById(id).orElse(null);
        if(vendedor != null) {
            vendedor.setEstado(Vendedor.EstadoVendedor.INACTIVO);
            repository.save(vendedor);
        }

        return "redirect:/vendedores";
    }
}
