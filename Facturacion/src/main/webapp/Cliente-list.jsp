<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Lista de Clientes</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #eef2f7; font-family: 'Segoe UI', sans-serif; }
        .container { margin-top: 50px; }
        h1 { color: #007bff; font-weight: bold; }
        .table-container { background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1); }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Lista de Clientes</h1>
        <div class="table-container">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre Comercial</th>
                        <th>RNC / Cédula</th>
                        <th>Cuenta Contable</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="cliente" items="${listaClientes}">
                        <tr>
                            <td>${cliente.id}</td>
                            <td>${cliente.nombre}</td>
                            <td>${cliente.rncCedula}</td>
                            <td>${cliente.cuentaContable}</td>
                            <td>${cliente.estado}</td>
                            <td>
                                <a href="cliente?action=edit&id=${cliente.id}" class="btn btn-outline-primary btn-sm">Editar</a>
                                <a href="cliente?action=delete&id=${cliente.id}" class="btn btn-outline-danger btn-sm" 
                                  onclick="return confirm('¿Está seguro de eliminar este cliente de la lista?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <div class="text-center mt-4">
            <a href="Cliente-form.jsp" class="btn btn-success">Nuevo Cliente</a>
            <a href="Menu.jsp" class="btn btn-info">Volver al Menú</a>
        </div>
    </div>
</body>
</html>
