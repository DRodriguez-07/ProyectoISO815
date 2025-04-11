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
import unapec.facturacion.domain.AsientoContable;
import unapec.facturacion.repository.AsientoContableRepository;

@Controller
@Slf4j
@RequestMapping("/asientoscontables")
public class AsientoContableController {
    private final AsientoContableRepository repository;
    private final AsientoContableRepository asientoContableRepository;

    @Autowired
    public AsientoContableController(AsientoContableRepository repository, AsientoContableRepository asientoContableRepository) {
        this.repository = repository;
        this.asientoContableRepository = asientoContableRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("asientoscontables", repository.findAll());
        return "asiento_list";
    }

    @GetMapping("/{id}")
    public String details(Model model, @PathVariable Long id) {
        AsientoContable a = asientoContableRepository.findById(id).orElse(null);
        model.addAttribute("asientocontable", a);

        return "asiento_detail";
    }

    @GetMapping("/new")
    public String create(Model model) {
        AsientoContable a = new AsientoContable();
        model.addAttribute("asientocontable", a);
        return "asiento_create";
    }

    @PostMapping("/new")
    public String create(@Valid AsientoContable asiento, Errors errors) {
        if(errors.hasErrors()) {
            return "asiento_create";
        }

        asientoContableRepository.save(asiento);

        return "redirect:/asientoscontables";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable Long id) {
        AsientoContable a = asientoContableRepository.findById(id).orElse(null);
        model.addAttribute("asientocontable", a);
        return "asiento_edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid AsientoContable asiento, Errors errors) {
        if(errors.hasErrors()) {
            return "asiento_edit";
        }

        return "redirect:/asientoscontables";
    }

    @GetMapping("/delete/{id}")
    public String delete(Model model, @PathVariable Long id) {
        AsientoContable a = asientoContableRepository.findById(id).orElse(null);
        if(a != null) {
            a.setEstado(AsientoContable.EstadoAsientoContable.Inactivo);
            asientoContableRepository.save(a);
        }

        return "redirect:/asientoscontables";
    }
}
