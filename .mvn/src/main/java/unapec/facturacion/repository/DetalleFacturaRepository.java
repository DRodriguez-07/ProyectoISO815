package unapec.facturacion.repository;

import org.springframework.data.repository.CrudRepository;
import unapec.facturacion.domain.DetalleFactura;

public interface DetalleFacturaRepository extends CrudRepository<DetalleFactura, Long> {
}
