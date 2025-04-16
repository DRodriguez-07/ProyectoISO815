package unapec.facturacion.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import unapec.facturacion.domain.Cliente;
import unapec.facturacion.domain.DetalleFactura;
import unapec.facturacion.domain.Vendedor;

import java.util.Date;
import java.util.List;

public interface FacturaDto {
    Long getId();
    Date getFecha();
    String getComentario();
    List<DetalleFacturaDto> getDetallesFactura();
    ClienteDto getCliente();
    VendedorDto getVendedor();
}
