package unapec.facturacion.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Min(value = 1, message = "El artículo es requerido")
    private Long articuloId;
    @Min(value = 1, message = "Debe seleccionar al menos 1")
    private int cantidad;
    @Min(value = 0, message = "El precio debe ser mayor a 0")
    private double precioUnitario;
}
