package unapec.facturacion.contabilidad.model;

import lombok.Data;
@Data
public class DetallesEntradaContable {
    public String cuentaId;
    public String tipoMovimiento;
    public double montoAsiento;
}
