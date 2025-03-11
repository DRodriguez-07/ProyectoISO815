/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dr.facturacion;

public class Cliente {
    private int id;
    private String nombre;
    private String rncCedula;
    private String cuentaContable;
    private String estado;

    // Constructor vacío
    public Cliente() {}

    // Constructor completo
    public Cliente(int id, String nombre, String rncCedula, String cuentaContable, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.rncCedula = rncCedula;
        this.cuentaContable = cuentaContable;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getRncCedula() { return rncCedula; }
    public void setRncCedula(String rncCedula) { this.rncCedula = rncCedula; }

    public String getCuentaContable() { return cuentaContable; }
    public void setCuentaContable(String cuentaContable) { this.cuentaContable = cuentaContable; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

