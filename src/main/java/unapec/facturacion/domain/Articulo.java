package unapec.facturacion.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Articulo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
    @Enumerated(EnumType.ORDINAL)
    private EstadoArticulo estado;

    @Min(value = 0,message = "El valor debe ser mayor a 0")
    private double precioUnitario;

    public enum EstadoArticulo {
        ACTIVO, INACTIVO
    }
}
