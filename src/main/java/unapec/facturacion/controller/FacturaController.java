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
import unapec.facturacion.domain.Factura;
import unapec.facturacion.repository.FacturaRepository;

@Slf4j
@Controller
@RequestMapping("/facturas")
public class FacturaController {
    private final FacturaRepository facturaRepository;

    @Autowired
    public FacturaController(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("facturas", facturaRepository.findAll());
        return "factura_list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        Factura factura = facturaRepository.findById(id).orElse(null);
        model.addAttribute("factura", factura);

        return "factura_detail";
    }

    @GetMapping("/new")
    public String create(Model model) {
        Factura f= new Factura(0L, 0L,0L,null, "",null);
        model.addAttribute("factura", f);
        return "factura_create";
    }

    @PostMapping("/new")
    public String create(@Valid Factura factura, Errors errors) {
        if(errors.hasErrors()) {
            return "factura_create";
        }

        facturaRepository.save(factura);
        return "redirect:/facturas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        return "factura_edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@Valid Factura factura, Errors errors) {
        if(errors.hasErrors()) {
            return "factura_edit";
        }

        return "redirect:/facturas";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        //Placeholder
        return "redirect:/facturas";
    }
}
