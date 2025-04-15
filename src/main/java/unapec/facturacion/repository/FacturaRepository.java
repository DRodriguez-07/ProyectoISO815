package unapec.facturacion.repository;

import org.springframework.data.repository.CrudRepository;
import unapec.facturacion.domain.Factura;

public interface FacturaRepository extends CrudRepository<Factura,Long> {
    Iterable<Factura> findFacturasByVendedor_Id(Long id);
    Iterable<Factura> findFacturasByCliente_Id(Long id);
    Iterable<Factura> findFacturasByVendedor_IdAndCliente_Id(Long vendedorId, Long clienteId);
}
