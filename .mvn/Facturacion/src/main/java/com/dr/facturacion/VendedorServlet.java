/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
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

@WebServlet(name = "VendedorServlet", urlPatterns = {"/vendedor"})
public class VendedorServlet extends HttpServlet {
    private VendedorDAO vendedorDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DBConnection.getConnection();
            vendedorDAO = new VendedorDAO(connection);
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
                    eliminarVendedor(request, response);
                    break;
                default:
                    listarVendedores(request, response);
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
                insertarVendedor(request, response);
            } else if ("update".equals(action)) {
                actualizarVendedor(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarVendedores(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Vendedor> listaVendedores = vendedorDAO.obtenerTodosVendedores();
        request.setAttribute("listaVendedores", listaVendedores);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Vendedor-list.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Vendedor-form.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Vendedor vendedorExistente = vendedorDAO.obtenerVendedor(id);
        request.setAttribute("vendedor", vendedorExistente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Vendedor-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertarVendedor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nombre = request.getParameter("nombre");
        double porcientoComision = Double.parseDouble(request.getParameter("porciento_comision"));
        String estado = request.getParameter("estado");

        Vendedor nuevoVendedor = new Vendedor();
        nuevoVendedor.setNombre(nombre);
        nuevoVendedor.setPorcientoComision(porcientoComision);
        nuevoVendedor.setEstado(estado);

        vendedorDAO.insertarVendedor(nuevoVendedor);
        response.sendRedirect("vendedor?action=list");
    }

    private void actualizarVendedor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        double porcientoComision = Double.parseDouble(request.getParameter("porciento_comision"));
        String estado = request.getParameter("estado");

        Vendedor vendedor = new Vendedor();
        vendedor.setId(id);
        vendedor.setNombre(nombre);
        vendedor.setPorcientoComision(porcientoComision);
        vendedor.setEstado(estado);

        vendedorDAO.actualizarVendedor(vendedor);
        response.sendRedirect("vendedor?action=list");
    }

    private void eliminarVendedor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        vendedorDAO.eliminarVendedor(id);
        response.sendRedirect("vendedor?action=list");
    }
}

