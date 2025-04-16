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

@WebServlet(name = "ArticuloServlet", urlPatterns = {"/articulo"})
public class ArticuloServlet extends HttpServlet {
    private ArticuloDAO articuloDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DBConnection.getConnection();
            articuloDAO = new ArticuloDAO(connection);
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
                    eliminarArticulo(request, response);
                    break;
                default:
                    listarArticulos(request, response);
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
                insertarArticulo(request, response);
            } else if ("update".equals(action)) {
                actualizarArticulo(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listarArticulos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Articulo> listaArticulos = articuloDAO.obtenerTodosArticulos();
        request.setAttribute("listaArticulos", listaArticulos);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Articulo-list.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioNuevo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("Articulo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void mostrarFormularioEdicion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Articulo articuloExistente = articuloDAO.obtenerArticulo(id);
        request.setAttribute("articulo", articuloExistente);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Articulo-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertarArticulo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String descripcion = request.getParameter("descripcion");
        double precioUnitario = Double.parseDouble(request.getParameter("precio_unitario"));
        String estado = request.getParameter("estado");

        Articulo nuevoArticulo = new Articulo();
        nuevoArticulo.setDescripcion(descripcion);
        nuevoArticulo.setPrecioUnitario(precioUnitario);
        nuevoArticulo.setEstado(estado);

        articuloDAO.insertarArticulo(nuevoArticulo);
        response.sendRedirect("articulo?action=list");
    }

    private void actualizarArticulo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        double precioUnitario = Double.parseDouble(request.getParameter("precio_unitario"));
        String estado = request.getParameter("estado");

        Articulo articulo = new Articulo();
        articulo.setId(id);
        articulo.setDescripcion(descripcion);
        articulo.setPrecioUnitario(precioUnitario);
        articulo.setEstado(estado);

        articuloDAO.actualizarArticulo(articulo);
        response.sendRedirect("articulo?action=list");
    }

    private void eliminarArticulo(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        articuloDAO.eliminarArticulo(id);
        response.sendRedirect("articulo?action=list");
    }
}
