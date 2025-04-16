package unapec.facturacion.repository;

import org.springframework.data.repository.CrudRepository;
import unapec.facturacion.domain.Factura;
import unapec.facturacion.dto.FacturaDto;

import java.util.Date;

public interface FacturaRepository extends CrudRepository<Factura,Long> {
    Iterable<Factura> findFacturasByVendedor_Id(Long id);
    Iterable<Factura> findFacturasByCliente_Id(Long id);
    Iterable<Factura> findFacturasByVendedor_IdAndCliente_Id(Long vendedorId, Long clienteId);
    Iterable<FacturaDto> searchFacturasByCliente_Id(Long clienteId);
}
