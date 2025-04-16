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

@WebServlet(name = "ClienteServlet", urlPatterns = {"/cliente"})
public class ClienteServlet extends HttpServlet {
    private ClienteDAO clienteDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DBConnection.getConnection();
            clienteDAO = new ClienteDAO(connection);
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
                    eliminarCliente(request, response);
                    break;
                default:
                    listarClientes(request, response);
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
                insertarCliente(request, response);
            } else if ("update".equals(action)) {
                actualizarCliente(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarClientes(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Cliente> listaClientes = clienteDAO.obtenerTodosClientes();
        request.setAttribute("listaClientes", listaClientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cliente-list.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cliente-form.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Cliente clienteExistente = clienteDAO.obtenerCliente(id);
        request.setAttribute("cliente", clienteExistente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Cliente-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nombre = request.getParameter("nombre");
        String rnc = request.getParameter("rnc_cedula");
        String cuentaContable = request.getParameter("cuenta_contable");
        String estado = request.getParameter("estado");

        Cliente nuevoCliente = new Cliente(0, nombre, rnc, cuentaContable, estado);
        clienteDAO.insertarCliente(nuevoCliente);
        response.sendRedirect("cliente?action=list");
    }

    private void actualizarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String rnc = request.getParameter("rnc_cedula");
        String cuentaContable = request.getParameter("cuenta_contable");
        String estado = request.getParameter("estado");

        Cliente cliente = new Cliente(id, nombre, rnc, cuentaContable, estado);
        clienteDAO.actualizarCliente(cliente);
        response.sendRedirect("cliente?action=list");
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        clienteDAO.eliminarCliente(id);
        response.sendRedirect("cliente?action=list");
    }
}
