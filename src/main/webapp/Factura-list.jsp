<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Lista de Facturas</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; font-family: Arial, sans-serif; }
        .container { margin-top: 50px; }
        .table-container { background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }
        .btn-sm { margin: 2px; }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center mb-4">Lista de Facturas</h1>

        <!-- Tabla de Facturas -->
        <div class="table-container">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Vendedor ID</th>
                        <th>Cliente ID</th>
                        <th>Fecha</th>
                        <th>Comentario</th>
                        <th>Artículo ID</th>
                        <th>Cantidad</th>
                        <th>Precio Unitario</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="factura" items="${listaFacturas}">
                        <tr>
                            <td>${factura.id}</td>
                            <td>${factura.vendedorId}</td>
                            <td>${factura.clienteId}</td>
                            <td>${factura.fecha}</td>
                            <td>${factura.comentario}</td>
                            <td>${factura.articuloId}</td>
                            <td>${factura.cantidad}</td>
                            <td>$${factura.precioUnitario}</td>
                            <td>
                                <a href="factura?action=edit&id=${factura.id}" class="btn btn-sm btn-outline-primary">Editar</a>
                                <a href="factura?action=delete&id=${factura.id}" class="btn btn-sm btn-outline-danger" onclick="return confirm('¿Está seguro de eliminar esta factura?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Botones -->
        <div class="text-center mt-4">
            <a href="Factura-form.jsp" class="btn btn-success">Nueva Factura</a>
            <a href="Menu.jsp" class="btn btn-info">Menú</a>
        </div>
    </div>
</body>
</html>

