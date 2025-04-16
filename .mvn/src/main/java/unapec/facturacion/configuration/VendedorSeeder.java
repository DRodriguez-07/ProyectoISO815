package unapec.facturacion.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import unapec.facturacion.domain.Articulo;
import unapec.facturacion.domain.Vendedor;
import unapec.facturacion.repository.ArticuloRepository;
import unapec.facturacion.repository.VendedorRepository;

@Configuration
public class VendedorSeeder implements ApplicationRunner {
    private final VendedorRepository vendedorRepository;

    @Autowired
    public VendedorSeeder(VendedorRepository vendedorRepository) {
        this.vendedorRepository = vendedorRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if(vendedorRepository.findById(1L).isEmpty()) {
            addInitialData();
        }
    }

    public void addInitialData() {

        vendedorRepository.save(new Vendedor(null,"Ángel Nicasio Martínez Acevedo", 0.10, Vendedor.EstadoVendedor.ACTIVO, null));
        vendedorRepository.save(new Vendedor(null, "Bartolo Antonio de Jesús", 0.05, Vendedor.EstadoVendedor.ACTIVO, null));
        vendedorRepository.save(new Vendedor(null, "Bernarda Abreu Santos", 0.05, Vendedor.EstadoVendedor.ACTIVO, null));
        vendedorRepository.save(new Vendedor(null, "Carlos Enmanuel Portorreal", 0.1, Vendedor.EstadoVendedor.ACTIVO, null));
        vendedorRepository.save(new Vendedor(null, "Damaris María Amparo ", 0.05, Vendedor.EstadoVendedor.ACTIVO, null));
        vendedorRepository.save(new Vendedor(null, "Danilo Miguel Gil Burgos ", 0.10, Vendedor.EstadoVendedor.ACTIVO, null));
        vendedorRepository.save(new Vendedor(null, "Dayana Miguelina Mena Gil", 0.10, Vendedor.EstadoVendedor.ACTIVO, null));
        vendedorRepository.save(new Vendedor(null, "Dhariana Suriel Tavárez ", 0.05, Vendedor.EstadoVendedor.ACTIVO, null));
        vendedorRepository.save(new Vendedor(null, "Dorca Martínez Villar", 0.10, Vendedor.EstadoVendedor.ACTIVO, null));
        vendedorRepository.save(new Vendedor(null, "Erickson Miguel Moronta", 0.05, Vendedor.EstadoVendedor.ACTIVO, null));
        vendedorRepository.save(new Vendedor(null, "Félix Blanco", 0.05, Vendedor.EstadoVendedor.ACTIVO, null));
    }
}
