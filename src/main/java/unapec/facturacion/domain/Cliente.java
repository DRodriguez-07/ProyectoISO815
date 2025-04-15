package unapec.facturacion.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import unapec.facturacion.annotation.RNCCedulaConstraint;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nombreRazon;
    @RNCCedulaConstraint
    private String rncCedula;
    @Min(value = 1)
    private Integer cuentaContable;
    @Enumerated(EnumType.ORDINAL)
    private EstadoCliente estado;

    public Cliente(String cartonesDelCaribeSas, String number, String number1, EstadoCliente estadoCliente, List<Factura> facturas) {
    }

    public enum EstadoCliente  {
        ACTIVO, INACTIVO
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cliente")
    private List<Factura> facturas = new ArrayList<Factura>();
}
