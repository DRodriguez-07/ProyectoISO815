package unapec.facturacion.repository;

import org.springframework.data.repository.CrudRepository;
import unapec.facturacion.domain.Vendedor;

public interface VendedorRepository extends CrudRepository<Vendedor, Long> {
}
