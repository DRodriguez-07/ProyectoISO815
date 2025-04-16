package unapec.facturacion.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import unapec.facturacion.contabilidad.model.EntradaContable;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fecha = new Date();
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaDesde = new Date(GregorianCalendar.from(LocalDate.of(1999,1,1).atStartOfDay(ZoneId.systemDefault())).getTimeInMillis());
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaHasta = new Date(GregorianCalendar.from(LocalDate.of(2050,1,1).atStartOfDay(ZoneId.systemDefault())).getTimeInMillis());
    private double monto;
    @Enumerated(EnumType.ORDINAL)
    private EstadoAsientoContable estado;
    private Integer entradaContableId;

    @ManyToOne
    private Cliente cliente;

    public enum EstadoAsientoContable {
        Pendiente, Registrado
    }
}
