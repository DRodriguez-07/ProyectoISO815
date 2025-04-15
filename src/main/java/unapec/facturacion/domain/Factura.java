package unapec.facturacion.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public Date fecha = new Date();

    public String comentario;

    //@NotNull
    @OneToMany(cascade = CascadeType.ALL)
    @Size(min = 1, message = "Debe agregar al menos 1 artículo")
    private List<DetalleFactura> detallesFactura;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Vendedor vendedor;
}
