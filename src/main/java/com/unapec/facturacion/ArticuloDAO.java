/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.unapec.facturacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticuloDAO {
    private Connection connection;

    public ArticuloDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Articulo> obtenerTodosArticulos() throws SQLException {
        List<Articulo> articulos = new ArrayList<>();
        String query = "SELECT * FROM articulos";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Articulo articulo = new Articulo();
                articulo.setId(rs.getInt("id"));
                articulo.setDescripcion(rs.getString("descripcion"));
                articulo.setPrecioUnitario(rs.getDouble("precio_unitario"));
                articulo.setEstado(rs.getString("estado"));
                articulos.add(articulo);
            }
        }
        return articulos;
    }

    public Articulo obtenerArticulo(int id) throws SQLException {
        String query = "SELECT * FROM articulos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Articulo articulo = new Articulo();
                    articulo.setId(rs.getInt("id"));
                    articulo.setDescripcion(rs.getString("descripcion"));
                    articulo.setPrecioUnitario(rs.getDouble("precio_unitario"));
                    articulo.setEstado(rs.getString("estado"));
                    return articulo;
                }
            }
        }
        return null;
    }

    public void insertarArticulo(Articulo articulo) throws SQLException {
        String query = "INSERT INTO articulos (descripcion, precio_unitario, estado) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, articulo.getDescripcion());
            stmt.setDouble(2, articulo.getPrecioUnitario());
            stmt.setString(3, articulo.getEstado());
            stmt.executeUpdate();
        }
    }

    public void actualizarArticulo(Articulo articulo) throws SQLException {
        String query = "UPDATE articulos SET descripcion = ?, precio_unitario = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, articulo.getDescripcion());
            stmt.setDouble(2, articulo.getPrecioUnitario());
            stmt.setString(3, articulo.getEstado());
            stmt.setInt(4, articulo.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminarArticulo(int id) throws SQLException {
        String query = "DELETE FROM articulos WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
