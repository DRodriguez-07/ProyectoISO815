/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dr.facturacion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "FacturaServlet", urlPatterns = {"/factura"})
public class FacturaServlet extends HttpServlet {
    private FacturaDAO facturaDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DBConnection.getConnection();
            facturaDAO = new FacturaDAO(connection);
        } catch (SQLException e) {
            throw new ServletException("Error al conectar a la base de datos", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            switch (action) {
                case "new":
                    mostrarFormularioNuevo(request, response);
                    break;
                case "edit":
                    mostrarFormularioEdicion(request, response);
                    break;
                case "delete":
                    eliminarFactura(request, response);
                    break;
                default:
                    listarFacturas(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("insert".equals(action)) {
                insertarFactura(request, response);
            } else if ("update".equals(action)) {
                actualizarFactura(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarFacturas(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Factura> listaFacturas = facturaDAO.obtenerTodasFacturas();
        request.setAttribute("listaFacturas", listaFacturas);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Factura-list.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Factura-form.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Factura facturaExistente = facturaDAO.obtenerFactura(id);
        request.setAttribute("factura", facturaExistente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Factura-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertarFactura(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int vendedorId = Integer.parseInt(request.getParameter("vendedor_id"));
        int clienteId = Integer.parseInt(request.getParameter("cliente_id"));
        String fecha = request.getParameter("fecha");
        String comentario = request.getParameter("comentario");
        int articuloId = Integer.parseInt(request.getParameter("articulo_id"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double precioUnitario = Double.parseDouble(request.getParameter("precio_unitario"));

        Factura nuevaFactura = new Factura();
        nuevaFactura.setVendedorId(vendedorId);
        nuevaFactura.setClienteId(clienteId);
        nuevaFactura.setFecha(java.sql.Date.valueOf(fecha));
        nuevaFactura.setComentario(comentario);
        nuevaFactura.setArticuloId(articuloId);
        nuevaFactura.setCantidad(cantidad);
        nuevaFactura.setPrecioUnitario(precioUnitario);

        facturaDAO.insertarFactura(nuevaFactura);
        response.sendRedirect("factura?action=list");
    }

    private void actualizarFactura(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int vendedorId = Integer.parseInt(request.getParameter("vendedor_id"));
        int clienteId = Integer.parseInt(request.getParameter("cliente_id"));
        String fecha = request.getParameter("fecha");
        String comentario = request.getParameter("comentario");
        int articuloId = Integer.parseInt(request.getParameter("articulo_id"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));
        double precioUnitario = Double.parseDouble(request.getParameter("precio_unitario"));

        Factura factura = new Factura();
        factura.setId(id);
        factura.setVendedorId(vendedorId);
        factura.setClienteId(clienteId);
        factura.setFecha(java.sql.Date.valueOf(fecha));
        factura.setComentario(comentario);
        factura.setArticuloId(articuloId);
        factura.setCantidad(cantidad);
        factura.setPrecioUnitario(precioUnitario);

        facturaDAO.actualizarFactura(factura);
        response.sendRedirect("factura?action=list");
    }

    private void eliminarFactura(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        facturaDAO.eliminarFactura(id);
        response.sendRedirect("factura?action=list");
    }
}


