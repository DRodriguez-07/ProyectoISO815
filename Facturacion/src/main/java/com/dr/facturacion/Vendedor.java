/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dr.facturacion;

public class Vendedor {
    private int id;
    private String nombre;
    private double porcientoComision;
    private String estado;

    public Vendedor() {}

    public Vendedor(int id, String nombre, double porcientoComision, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.porcientoComision = porcientoComision;
        this.estado = estado;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public double getPorcientoComision() { return porcientoComision; }
    public void setPorcientoComision(double porcientoComision) { this.porcientoComision = porcientoComision; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

