package unapec.facturacion.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import unapec.facturacion.domain.Articulo;
import unapec.facturacion.repository.ArticuloRepository;

@RestController
@RequestMapping("/api/articulos")
public class ArticuloApiController {
    private ArticuloRepository articuloRepository;

    public ArticuloApiController(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @GetMapping("/{id}")
    public Articulo details(@PathVariable("id") Long id) {
        return articuloRepository.findById(id).orElse(null);
    }
}
