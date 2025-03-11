<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Lista de Asientos Contables</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; font-family: Arial, sans-serif; }
        .container { margin-top: 50px; }
        .table-container { background: #ffffff; padding: 20px; border-radius: 8px; box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1); }
        .btn { margin: 5px; }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center mb-4">Lista de Asientos Contables</h1>

        <div class="table-container">
            <table class="table table-striped table-hover">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Descripción</th>
                        <th>ID Cliente</th>
                        <th>Cuenta</th>
                        <th>Movimiento</th>
                        <th>Fecha</th>
                        <th>Monto</th>
                        <th>Estado</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="asiento" items="${listaAsientos}">
                        <tr>
                            <td>${asiento.id}</td>
                            <td>${asiento.descripcion}</td>
                            <td>${asiento.clienteId}</td>
                            <td>${asiento.cuenta}</td>
                            <td>${asiento.tipoMovimiento}</td>
                            <td>${asiento.fecha}</td>
                            <td>$${asiento.monto}</td>
                            <td>${asiento.estado}</td>
                            <td>
                                <a href="asiento?action=edit&id=${asiento.id}" class="btn btn-outline-primary btn-sm">Editar</a>
                                <a href="asiento?action=delete&id=${asiento.id}" class="btn btn-outline-danger btn-sm" onclick="return confirm('¿Estás seguro de eliminar este asiento?')">Eliminar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <!-- Botones adicionales -->
        <div class="text-center mt-4">
            <a href="AsientoContable-form.jsp" class="btn btn-success">Nuevo Asiento</a>
            <a href="Menu.jsp" class="btn btn-info">Volver al Menú</a>
        </div>
    </div>
</body>
</html>

