package unapec.facturacion.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    @Max(value = 1, message = "La comisión no puede superar el 100%")
    @Min(value = 0, message = "La comisión no puede ser inferior a 0%")
    private double porcientoComision;
    @Enumerated(EnumType.ORDINAL)
    private EstadoVendedor estado;

    public enum EstadoVendedor {
        ACTIVO, INACTIVO
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vendedor")
    private List<Factura> facturas = new ArrayList<Factura>();
}
