package unapec.facturacion.domain;

import jakarta.persistence.*;
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
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombreRazon;
    private String rncCedula;
    private String cuentaContrable;
    @Enumerated(EnumType.ORDINAL)
    private EstadoCliente estado;
    public enum EstadoCliente  {
        ACTIVO, INACTIVO
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Factura> facturas = new ArrayList<Factura>();
}
