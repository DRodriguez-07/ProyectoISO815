package unapec.facturacion.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unapec.facturacion.domain.Articulo;
import unapec.facturacion.domain.Factura;

public interface DetalleFacturaDto {
    Long getId();
    int getCantidad();
    double getPrecioUnitario();
}
