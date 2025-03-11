/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dr.facturacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VendedorDAO {
    private final Connection connection;

    public VendedorDAO(Connection connection) {
        this.connection = connection;
    }

    // Obtener todos los vendedores
    public List<Vendedor> obtenerTodosVendedores() throws SQLException {
        List<Vendedor> vendedores = new ArrayList<>();
        String query = "SELECT * FROM vendedores";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Vendedor vendedor = new Vendedor();
                vendedor.setId(rs.getInt("id"));
                vendedor.setNombre(rs.getString("nombre"));
                vendedor.setPorcientoComision(rs.getDouble("porciento_comision"));
                vendedor.setEstado(rs.getString("estado"));
                vendedores.add(vendedor);
            }
        }
        return vendedores;
    }

    // Obtener un vendedor por ID
    public Vendedor obtenerVendedor(int id) throws SQLException {
        String query = "SELECT * FROM vendedores WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Vendedor vendedor = new Vendedor();
                    vendedor.setId(rs.getInt("id"));
                    vendedor.setNombre(rs.getString("nombre"));
                    vendedor.setPorcientoComision(rs.getDouble("porciento_comision"));
                    vendedor.setEstado(rs.getString("estado"));
                    return vendedor;
                }
            }
        }
        return null;
    }

    // Insertar un nuevo vendedor
    public void insertarVendedor(Vendedor vendedor) throws SQLException {
        String query = "INSERT INTO vendedores (nombre, porciento_comision, estado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vendedor.getNombre());
            stmt.setDouble(2, vendedor.getPorcientoComision());
            stmt.setString(3, vendedor.getEstado());
            stmt.executeUpdate();
        }
    }

    // Actualizar un vendedor
    public void actualizarVendedor(Vendedor vendedor) throws SQLException {
        String query = "UPDATE vendedores SET nombre = ?, porciento_comision = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, vendedor.getNombre());
            stmt.setDouble(2, vendedor.getPorcientoComision());
            stmt.setString(3, vendedor.getEstado());
            stmt.setInt(4, vendedor.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminar un vendedor
    public void eliminarVendedor(int id) throws SQLException {
        String query = "DELETE FROM vendedores WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}