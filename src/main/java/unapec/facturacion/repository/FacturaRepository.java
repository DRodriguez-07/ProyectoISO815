package unapec.facturacion.repository;

import org.springframework.data.repository.CrudRepository;
import unapec.facturacion.domain.Factura;

public interface FacturaRepository extends CrudRepository<Factura,Long> {
}
