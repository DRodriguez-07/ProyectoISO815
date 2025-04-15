package unapec.facturacion.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class AsientoContable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String descripcion;
    @Min(value = 1)
    private Integer cuentaContable = 13;
    private Date fecha = new Date();
    private Date fechaDesde = new Date();
    private Date fechaHasta = new Date();
    private double monto;
    @Enumerated(EnumType.ORDINAL)
    private EstadoAsientoContable estado;

    @ManyToOne
    private Cliente cliente;

    public enum EstadoAsientoContable {
        Pendiente, Registrado
    }
}
