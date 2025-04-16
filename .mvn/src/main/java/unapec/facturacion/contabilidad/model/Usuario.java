package unapec.facturacion.contabilidad.model;

import lombok.Data;

@Data
public class Usuario {
    public int id;
    public String nombre;
    public String email;
    public int sistemaAuxiliarId;
}
