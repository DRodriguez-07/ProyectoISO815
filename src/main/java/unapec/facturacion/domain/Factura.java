package unapec.facturacion.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access= AccessLevel.PRIVATE, force=true)
@AllArgsConstructor
public class Factura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    public Date fecha = new Date();

    public String comentario;

    @NotNull
    @OneToMany
    @Size(min = 1, message = "Debe agregar al menos 1 artículo")
    private List<DetalleFactura> detalleFacturas;

    @ManyToOne
    private Cliente cliente;

    @ManyToOne
    private Vendedor vendedor;
}
