package unapec.facturacion.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticuloDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;
    @Enumerated(EnumType.ORDINAL)
    private EstadoArticulo estado;

    @Min(value = 1,message = "El valor debe ser mayor a 0")
    private double precioUnitario = 0.0;

    public enum EstadoArticulo {
        ACTIVO, INACTIVO
    }
}
