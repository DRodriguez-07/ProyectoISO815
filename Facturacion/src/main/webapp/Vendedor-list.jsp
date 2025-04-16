<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Vendedores</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; font-family: Arial, sans-serif; }
        .container { margin-top: 50px; }
        h1 { color: #007bff; text-align: center; }
        .table-container { background-color: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }
        .btn { margin: 5px; }
    </style>
</head>
<body>
    <div class="container">
        <h1>Lista de Vendedores</h1>

        <!-- Tabla de Vendedores -->
        <div class="table-container">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Porcentaje Comisión</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="vendedor" items="${listaVendedores}">
                        <tr>
                            <td>${vendedor.id}</td>
                            <td>${vendedor.nombre}</td>
                            <td>${vendedor.porcientoComision}%</td>
                            <td>${vendedor.estado}</td>
                            <td>
                                <a href="vendedor?action=edit&id=${vendedor.id}" class="btn btn-outline-primary btn-sm">Editar</a>
                                <a href="vendedor?action=delete&id=${vendedor.id}" class="btn btn-outline-danger btn-sm"
                                   onclick="return confirm('¿Está seguro de que desea eliminar este vendedor?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Botones adicionales -->
        <div class="text-center mt-4">
            <a href="Vendedor-form.jsp" class="btn btn-success">Nuevo Vendedor</a>
            <a href="Menu.jsp" class="btn btn-info">Volver al Menú</a>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
