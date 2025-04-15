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
import unapec.facturacion.domain.DetalleFactura;
import unapec.facturacion.domain.Factura;
import unapec.facturacion.repository.ArticuloRepository;
import unapec.facturacion.repository.ClienteRepository;
import unapec.facturacion.repository.FacturaRepository;
import unapec.facturacion.repository.VendedorRepository;

@Slf4j
@Controller
@RequestMapping("/facturas")
public class FacturaController {
    private final FacturaRepository facturaRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final ArticuloRepository articuloRepository;

    @Autowired
    public FacturaController(FacturaRepository facturaRepository, ClienteRepository clienteRepository, VendedorRepository vendedorRepository, ArticuloRepository articuloRepository) {
        this.facturaRepository = facturaRepository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
        this.articuloRepository = articuloRepository;
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
        Factura f= new Factura(0L, null, "",null, null, null);
        model.addAttribute("factura", f);
        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("vendedores", vendedorRepository.findAll());
        model.addAttribute("articulos", articuloRepository.findAll());
        return "factura_create";
    }

    @PostMapping("/new")
    public String create(@Valid Factura factura, Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("clientes", clienteRepository.findAll());
            model.addAttribute("vendedores", vendedorRepository.findAll());
            model.addAttribute("articulos", articuloRepository.findAll());
            return "factura_create";
        }

        for(DetalleFactura d : factura.getDetallesFactura()) {
            d.setFactura(factura);
        }

        facturaRepository.save(factura);
        return "redirect:/facturas";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Factura f = facturaRepository.findById(id).orElse(null);
        model.addAttribute("factura", f);

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
