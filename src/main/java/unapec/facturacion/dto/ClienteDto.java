package unapec.facturacion.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import unapec.facturacion.annotation.RNCCedulaConstraint;
import unapec.facturacion.domain.Cliente;

public interface ClienteDto {
    Long getId();
    String getNombreRazon();
    String getRncCedula();
    Integer getCuentaContable();
}
