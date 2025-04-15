package unapec.facturacion.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import unapec.facturacion.repository.*;

@Controller
@Slf4j
@RequestMapping("/admin")
public class AdminController {
    private final FacturaRepository facturaRepository;
    private final ClienteRepository clienteRepository;
    private final VendedorRepository vendedorRepository;
    private final ArticuloRepository articuloRepository;
    private final UserRepository userRepository;

    @Autowired
    public AdminController(FacturaRepository facturaRepository,
                           ClienteRepository clienteRepository, VendedorRepository vendedorRepository,
                           ArticuloRepository articuloRepository, UserRepository userRepository) {
        this.facturaRepository = facturaRepository;
        this.clienteRepository = clienteRepository;
        this.vendedorRepository = vendedorRepository;
        this.articuloRepository = articuloRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model model) {
        model.addAttribute("usuariosCount", userRepository.count());
        model.addAttribute("clientCount", vendedorRepository.count());
        model.addAttribute("articulosCount", articuloRepository.count());
        model.addAttribute("facturas", facturaRepository.findAll());

        return "dashboard";
    }
}
