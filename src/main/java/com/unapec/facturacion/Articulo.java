/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unapec.facturacion;

public class Articulo {
    private int id;
    private String descripcion;
    private double precioUnitario;
    private String estado;

    // Constructor vacío
    public Articulo() {}

    // Constructor completo
    public Articulo(int id, String descripcion, double precioUnitario, String estado) {
        this.id = id;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.estado = estado;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}

