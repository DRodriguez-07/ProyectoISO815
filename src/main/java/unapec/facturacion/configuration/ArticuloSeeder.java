package unapec.facturacion.configuration;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import unapec.facturacion.domain.Articulo;
import unapec.facturacion.repository.ArticuloRepository;

@Configuration
public class ArticuloSeeder implements ApplicationRunner {
    private final ArticuloRepository articuloRepository;

    @Autowired
    public ArticuloSeeder(ArticuloRepository articuloRepository) {
        this.articuloRepository = articuloRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(articuloRepository.findById(1L).isEmpty()) {
            addInitialData();
        }
    }

    public void addInitialData() {
        articuloRepository.save(new Articulo(null, "Televisor Samsung Smart TV UHD 50\" UN50DU7000PXPA", Articulo.EstadoArticulo.ACTIVO, 32_995.00, null));
        articuloRepository.save(new Articulo(null,"Lavadora Semi-Automática 22KG Cetron LTDR2224HBAB0", Articulo.EstadoArticulo.ACTIVO, 17_995, null));
        articuloRepository.save(new Articulo(null,"Televisor Smart FHD Aiwa 43\" AW43B4SFG", Articulo.EstadoArticulo.ACTIVO, 10.0, null));
        articuloRepository.save(new Articulo(null,"Ex", Articulo.EstadoArticulo.ACTIVO, 16_595.00, null));
        articuloRepository.save(new Articulo(null,"Microondas Conex 1.1 P.C. EM0P042DG-P Color Negro", Articulo.EstadoArticulo.ACTIVO, 8_995.00, null));
        articuloRepository.save(new Articulo(null, "BBQ A Carbón 4 Patas Negro Grillmark 22\"", Articulo.EstadoArticulo.ACTIVO, 3_965.50, null));
        articuloRepository.save(new Articulo(null,"BBQ A Carbón 4 Patas Negro Grillmark 18\"", Articulo.EstadoArticulo.ACTIVO, 2_362.50, null));
        articuloRepository.save(new Articulo(null,"BBQ A Carbón Patio Pro250\" Char-Griller", Articulo.EstadoArticulo.ACTIVO, 7_995.00, null));
        articuloRepository.save(new Articulo(null, "BBQ A Gas Negro Con 3 Hornillas 30,000 BTU Char-Broil", Articulo.EstadoArticulo.ACTIVO, 17_995.00, null));
        articuloRepository.save(new Articulo(null, "Sandía Roja Sin Semilla, Lb", Articulo.EstadoArticulo.ACTIVO, 29.95, null));
        articuloRepository.save(new Articulo(null, "Piscina Sin Filtro Beachside Intex 10'X30\"", Articulo.EstadoArticulo.ACTIVO, 7_695.00, null));
        articuloRepository.save(new Articulo(null, "Piscina Tubo Metal 12'X30'' Sin Filtro Intex", Articulo.EstadoArticulo.ACTIVO, 7_995.00, null));
        articuloRepository.save(new Articulo(null, "Cerveza Presidente Regular Lata 12 Onz", Articulo.EstadoArticulo.ACTIVO, 94.00, null));
        articuloRepository.save(new Articulo(null, "Cerveza Modelo Especial Lata 8 Onz, 6 Und/Paq", Articulo.EstadoArticulo.ACTIVO, 509.95, null));
        articuloRepository.save(new Articulo( null,"Manzana Pink Landy, Lb", Articulo.EstadoArticulo.ACTIVO, 88.95, null));
        articuloRepository.save(new Articulo(null,"Nectarines, Lb (Aprox. 3 A 4 Nectarines Por Libra)", Articulo.EstadoArticulo.ACTIVO, 189.00, null));
    }
}
