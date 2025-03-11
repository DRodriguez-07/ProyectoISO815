/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.dr.facturacion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AsientoContableServlet", urlPatterns = {"/asiento"})
public class AsientoContableServlet extends HttpServlet {
    private AsientoContableDAO asientoDAO;

    @Override
    public void init() throws ServletException {
        try {
            Connection connection = DBConnection.getConnection();
            asientoDAO = new AsientoContableDAO(connection);
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
                    request.getRequestDispatcher("AsientoContable-form.jsp").forward(request, response);
                    break;
                case "edit":
                    int id = Integer.parseInt(request.getParameter("id"));
                    AsientoContable asiento = asientoDAO.obtenerAsiento(id);
                    request.setAttribute("asiento", asiento);
                    request.getRequestDispatcher("AsientoContable-form.jsp").forward(request, response);
                    break;
                case "delete":
                    asientoDAO.eliminarAsiento(Integer.parseInt(request.getParameter("id")));
                    response.sendRedirect("asiento?action=list");
                    break;
                default:
                    List<AsientoContable> listaAsientos = asientoDAO.obtenerTodosAsientos();
                    request.setAttribute("listaAsientos", listaAsientos);
                    request.getRequestDispatcher("AsientoContable-list.jsp").forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
}
