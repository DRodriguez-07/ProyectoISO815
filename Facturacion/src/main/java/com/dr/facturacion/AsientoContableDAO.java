/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dr.facturacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AsientoContableDAO {

    private Connection connection;

    public AsientoContableDAO(Connection connection) {
        this.connection = connection;
    }

    // Insertar Asiento Contable
    public void insertarAsiento(AsientoContable asiento) throws SQLException {
        String sql = "INSERT INTO asientos_contables (descripcion, cliente_id, cuenta, tipo_movimiento, fecha, monto, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, asiento.getDescripcion());
            stmt.setInt(2, asiento.getClienteId());
            stmt.setString(3, asiento.getCuenta());
            stmt.setString(4, asiento.getTipoMovimiento());
            if (asiento.getFecha() != null) {
                stmt.setDate(5, new java.sql.Date(asiento.getFecha().getTime()));
            } else {
                stmt.setDate(5, new java.sql.Date(System.currentTimeMillis())); // Usa la fecha actual
            }
            stmt.setDouble(6, asiento.getMonto());
            stmt.setString(7, asiento.getEstado());
            stmt.executeUpdate();
        }
    }

    // Obtener todos los asientos contables
    public List<AsientoContable> obtenerTodosAsientos() throws SQLException {
        List<AsientoContable> listaAsientos = new ArrayList<>();
        String sql = "SELECT * FROM asientos_contables";

        try (PreparedStatement stmt = connection.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                AsientoContable asiento = new AsientoContable(
                        rs.getInt("id"),
                        rs.getString("descripcion"),
                        rs.getInt("cliente_id"),
                        rs.getString("cuenta"),
                        rs.getString("tipo_movimiento"),
                        rs.getDate("fecha"),
                        rs.getDouble("monto"),
                        rs.getString("estado")
                );
                listaAsientos.add(asiento);
            }
        }
        return listaAsientos;
    }

    // Obtener Asiento por ID
    public AsientoContable obtenerAsiento(int id) throws SQLException {
        String sql = "SELECT * FROM asientos_contables WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new AsientoContable(
                            rs.getInt("id"),
                            rs.getString("descripcion"),
                            rs.getInt("cliente_id"),
                            rs.getString("cuenta"),
                            rs.getString("tipo_movimiento"),
                            rs.getDate("fecha"),
                            rs.getDouble("monto"),
                            rs.getString("estado")
                    );
                }
            }
        }
        return null;
    }

    // Actualizar Asiento Contable
    public void actualizarAsiento(AsientoContable asiento) throws SQLException {
        String sql = "UPDATE asientos_contables SET descripcion=?, cliente_id=?, cuenta=?, tipo_movimiento=?, fecha=?, monto=?, estado=? WHERE id=?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, asiento.getDescripcion());
            stmt.setInt(2, asiento.getClienteId());
            stmt.setString(3, asiento.getCuenta());
            stmt.setString(4, asiento.getTipoMovimiento());
            if (asiento.getFecha() != null) {
                stmt.setDate(5, new java.sql.Date(asiento.getFecha().getTime()));
            } else {
                stmt.setDate(5, new java.sql.Date(System.currentTimeMillis())); // Usa la fecha actual
            }
            stmt.setDouble(6, asiento.getMonto());
            stmt.setString(7, asiento.getEstado());
            stmt.setInt(8, asiento.getId());
            stmt.executeUpdate();
        }
    }

    // Eliminar Asiento Contable
    public void eliminarAsiento(int id) throws SQLException {
        String sql = "DELETE FROM asientos_contables WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
