/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dr.facturacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAO {
    private Connection connection;

    public FacturaDAO(Connection connection) {
        this.connection = connection;
    }

    // Insertar una nueva factura
    public void insertarFactura(Factura factura) throws SQLException {
        String sql = "INSERT INTO facturas (vendedor_id, cliente_id, fecha, comentario, articulo_id, cantidad, precio_unitario) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, factura.getVendedorId());
            stmt.setInt(2, factura.getClienteId());
            stmt.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            stmt.setString(4, factura.getComentario());
            stmt.setInt(5, factura.getArticuloId());
            stmt.setInt(6, factura.getCantidad());
            stmt.setDouble(7, factura.getPrecioUnitario());

            stmt.executeUpdate();
        }
    }

    // Obtener todas las facturas
    public List<Factura> obtenerTodasFacturas() throws SQLException {
        List<Factura> listaFacturas = new ArrayList<>();
        String sql = "SELECT * FROM facturas";

        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Factura factura = new Factura();
                factura.setId(rs.getInt("id"));
                factura.setVendedorId(rs.getInt("vendedor_id"));
                factura.setClienteId(rs.getInt("cliente_id"));
                factura.setFecha(rs.getDate("fecha"));
                factura.setComentario(rs.getString("comentario"));
                factura.setArticuloId(rs.getInt("articulo_id"));
                factura.setCantidad(rs.getInt("cantidad"));
                factura.setPrecioUnitario(rs.getDouble("precio_unitario"));

                listaFacturas.add(factura);
            }
        }
        return listaFacturas;
    }

    // Obtener una factura por ID
    public Factura obtenerFactura(int id) throws SQLException {
        String sql = "SELECT * FROM facturas WHERE id = ?";
        Factura factura = null;

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    factura = new Factura();
                    factura.setId(rs.getInt("id"));
                    factura.setVendedorId(rs.getInt("vendedor_id"));
                    factura.setClienteId(rs.getInt("cliente_id"));
                    factura.setFecha(rs.getDate("fecha"));
                    factura.setComentario(rs.getString("comentario"));
                    factura.setArticuloId(rs.getInt("articulo_id"));
                    factura.setCantidad(rs.getInt("cantidad"));
                    factura.setPrecioUnitario(rs.getDouble("precio_unitario"));
                }
            }
        }
        return factura;
    }

    // Actualizar factura
    public void actualizarFactura(Factura factura) throws SQLException {
        String sql = "UPDATE facturas SET vendedor_id = ?, cliente_id = ?, fecha = ?, comentario = ?, articulo_id = ?, cantidad = ?, precio_unitario = ? WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, factura.getVendedorId());
            stmt.setInt(2, factura.getClienteId());
            stmt.setDate(3, new java.sql.Date(factura.getFecha().getTime()));
            stmt.setString(4, factura.getComentario());
            stmt.setInt(5, factura.getArticuloId());
            stmt.setInt(6, factura.getCantidad());
            stmt.setDouble(7, factura.getPrecioUnitario());
            stmt.setInt(8, factura.getId());

            stmt.executeUpdate();
        }
    }

    // Eliminar factura
    public void eliminarFactura(int id) throws SQLException {
        String sql = "DELETE FROM facturas WHERE id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
