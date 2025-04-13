package unapec.facturacion.domain;


import jakarta.persistence.*;
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
    private Long clienteId;
    @Column(length = 50)
    private String cuentaContable;
    @Enumerated(EnumType.ORDINAL)
    private TipoMovmienteoAsienteContable tipoMovimiento;
    private Date fecha = new Date();
    private double monto;
    private EstadoAsientoContable estado;


    public enum TipoMovmienteoAsienteContable {
        DB, CR
    }

    public enum EstadoAsientoContable {
        Activo, Inactivo
    }
}
