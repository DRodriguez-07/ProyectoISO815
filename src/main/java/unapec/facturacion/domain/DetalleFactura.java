package unapec.facturacion.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Min(value = 1, message = "Debe seleccionar al menos 1")
    private int cantidad;
    @Min(value = 0, message = "El precio debe ser mayor a 0")
    private double precioUnitario;

    @ManyToOne
    private Articulo articulo;

    @ManyToOne
    private Factura factura;
}
