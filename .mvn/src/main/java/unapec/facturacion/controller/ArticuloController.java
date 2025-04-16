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
import unapec.facturacion.domain.Articulo;
import unapec.facturacion.repository.ArticuloRepository;

@Slf4j
@Controller
@RequestMapping("/articulos")
public class ArticuloController {
    private final ArticuloRepository articuloRepository;

    @Autowired
    public ArticuloController(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @GetMapping("")
    public String index(Model model) {
        model.addAttribute("articulos", articuloRepository.findAll());
        return "articulo_list";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable Long id, Model model) {
        Articulo a = articuloRepository.findById(id).orElse(null);
        model.addAttribute("articulo", a);

        return "articulo_detail";
    }

    @GetMapping("/new")
    public String create(Model model) {
        Articulo a = new Articulo(0L, "", Articulo.EstadoArticulo.ACTIVO,0.0, null);
        model.addAttribute("articulo", a);
        return "articulo_create";
    }

    @PostMapping("/new")
    public String createForm(@Valid Articulo articulo, Errors errors) {
        if(errors.hasErrors()) {
            return "articulo_create";
        }
        log.info("Guardando Nuevo Articulo: {}", articulo);
        articuloRepository.save(articulo);

        return "redirect:/articulos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        Articulo a = articuloRepository.findById(id).orElse(null);
        model.addAttribute("articulo", a);
        return "articulo_edit";
    }

    @PostMapping("/edit/{id}")
    public String editForm(@Valid Articulo articulo, Errors errors) {
        if(errors.hasErrors()) {
            return "articulo_edit";
        }
        articuloRepository.save(articulo);
        return "redirect:/articulos";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, Model model) {
        Articulo a = articuloRepository.findById(id).orElse(null);
        if(a != null) {
            a.setEstado(Articulo.EstadoArticulo.INACTIVO);
            articuloRepository.save(a);
        }
        return "redirect:/articulos";
    }
}
