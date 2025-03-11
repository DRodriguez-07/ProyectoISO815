/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dr.facturacion;

import java.util.Date;

public class AsientoContable {
    private int id;
    private String descripcion;
    private int clienteId;
    private String cuenta;
    private String tipoMovimiento;
    private Date fecha;
    private double monto;
    private String estado;

    public AsientoContable() {}

    public AsientoContable(int id, String descripcion, int clienteId, String cuenta, String tipoMovimiento, Date fecha, double monto, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.clienteId = clienteId;
        this.cuenta = cuenta;
        this.tipoMovimiento = tipoMovimiento;
        this.fecha = fecha;
        this.monto = monto;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public int getClienteId() { return clienteId; }
    public void setClienteId(int clienteId) { this.clienteId = clienteId; }

    public String getCuenta() { return cuenta; }
    public void setCuenta(String cuenta) { this.cuenta = cuenta; }

    public String getTipoMovimiento() { return tipoMovimiento; }
    public void setTipoMovimiento(String tipoMovimiento) { this.tipoMovimiento = tipoMovimiento; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}
