<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Artículos</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            margin-top: 50px;
        }
        h1 {
            color: #007bff;
            font-weight: bold;
            text-align: center;
            margin-bottom: 20px;
        }
        .table-container {
            background: #ffffff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }
        .btn-container {
            display: flex;
            justify-content: center;
            gap: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Lista de Artículos</h1>

        <!-- Tabla de Artículos -->
        <div class="table-container">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Descripción</th>
                        <th>Precio Unitario</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="articulo" items="${listaArticulos}">
                        <tr>
                            <td>${articulo.id}</td>
                            <td>${articulo.descripcion}</td>
                            <td>$${articulo.precioUnitario}</td>
                            <td>${articulo.estado}</td>
                            <td>
                                <a href="articulo?action=edit&id=${articulo.id}" class="btn btn-outline-primary btn-sm">Editar</a>
                                <a href="articulo?action=delete&id=${articulo.id}" class="btn btn-outline-danger btn-sm"
                                   onclick="return confirm('¿Está seguro de eliminar este artículo?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Botones adicionales -->
        <div class="text-center mt-4">
            <a href="articulo?action=new" class="btn btn-success">Nuevo Artículo</a>
            <a href="Menu.jsp" class="btn btn-info">Volver al Menú</a>
        </div>
    </div>
</body>
</html>
