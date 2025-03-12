/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dr.facturacion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private Connection connection;

    public ClienteDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Cliente> obtenerTodosClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM clientes";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                clientes.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre_comercial"),
                        rs.getString("rnc_cedula"),
                        rs.getString("cuenta_contable"),
                        rs.getString("estado")
                ));
            }
        }
        return clientes;
    }

    public Cliente obtenerCliente(int id) throws SQLException {
        String query = "SELECT * FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre_comercial"),
                        rs.getString("rnc_cedula"),
                        rs.getString("cuenta_contable"),
                        rs.getString("estado")
                );
            }
        }
        return null;
    }

// Método para insertar un cliente
public void insertarCliente(Cliente cliente) throws SQLException {
    String queryCliente = "INSERT INTO clientes (nombre_comercial, rnc_cedula, cuenta_contable, estado) VALUES (?, ?, ?, ?)";

    try {
        connection.setAutoCommit(false); 

        // Insertar el cliente
        try (PreparedStatement stmtCliente = connection.prepareStatement(queryCliente, Statement.RETURN_GENERATED_KEYS)) {
            stmtCliente.setString(1, cliente.getNombre());
            stmtCliente.setString(2, cliente.getRncCedula());
            stmtCliente.setString(3, cliente.getCuentaContable());
            stmtCliente.setString(4, cliente.getEstado());
            stmtCliente.executeUpdate();

        }

        connection.commit(); // Confirmar cambios
    } catch (SQLException e) {
        connection.rollback(); // Revertir cambios en caso de error
        throw new SQLException("Error al insertar cliente", e);
    } finally {
        connection.setAutoCommit(true); // Restaurar auto-commit
    }
}

    public void actualizarCliente(Cliente cliente) throws SQLException {
        String query = "UPDATE clientes SET nombre_comercial = ?, rnc_cedula = ?, cuenta_contable = ?, estado = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getRncCedula());
            stmt.setString(3, cliente.getCuentaContable());
            stmt.setString(4, cliente.getEstado());
            stmt.setInt(5, cliente.getId());
            stmt.executeUpdate();
        }
    }

    public void eliminarCliente(int id) throws SQLException {
        String query = "DELETE FROM clientes WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
