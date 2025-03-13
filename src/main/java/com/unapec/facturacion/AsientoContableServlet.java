/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.unapec.facturacion;

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            if ("insert".equals(action)) {
                insertarAsiento(request, response);
            } else if ("update".equals(action)) {
                actualizarAsiento(request, response);
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void insertarAsiento(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String descripcion = request.getParameter("descripcion");
        int clienteId = Integer.parseInt(request.getParameter("cliente_id"));
        String cuenta = request.getParameter("cuenta");
        String tipoMovimiento = request.getParameter("tipo_movimiento");
        double monto = Double.parseDouble(request.getParameter("monto"));
        String estado = request.getParameter("estado");

        AsientoContable nuevoAsiento = new AsientoContable();
        nuevoAsiento.setDescripcion(descripcion);
        nuevoAsiento.setClienteId(clienteId);
        nuevoAsiento.setCuenta(cuenta);
        nuevoAsiento.setTipoMovimiento(tipoMovimiento);
        nuevoAsiento.setMonto(monto);
        nuevoAsiento.setEstado(estado);

        asientoDAO.insertarAsiento(nuevoAsiento);
        response.sendRedirect("asiento?action=list");
    }

    private void actualizarAsiento(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String descripcion = request.getParameter("descripcion");
        int clienteId = Integer.parseInt(request.getParameter("cliente_id"));
        String cuenta = request.getParameter("cuenta");
        String tipoMovimiento = request.getParameter("tipo_movimiento");
        double monto = Double.parseDouble(request.getParameter("monto"));
        String estado = request.getParameter("estado");

        AsientoContable asiento = new AsientoContable();
        asiento.setId(id);
        asiento.setDescripcion(descripcion);
        asiento.setClienteId(clienteId);
        asiento.setCuenta(cuenta);
        asiento.setTipoMovimiento(tipoMovimiento);
        asiento.setMonto(monto);
        asiento.setEstado(estado);

        asientoDAO.actualizarAsiento(asiento);
        response.sendRedirect("asiento?action=list");
    }
}
