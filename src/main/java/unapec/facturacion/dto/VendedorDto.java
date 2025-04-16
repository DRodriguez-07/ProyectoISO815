package unapec.facturacion.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import unapec.facturacion.annotation.RNCCedulaConstraint;
import unapec.facturacion.domain.Cliente;
import unapec.facturacion.domain.Factura;

import java.util.ArrayList;
import java.util.List;

public interface VendedorDto {
    Long getId();
    String getNombre();
    double getPorcientoComision();
}
