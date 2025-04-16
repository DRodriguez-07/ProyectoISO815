<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Sistema de Facturación - Menú Principal</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free/js/all.min.js"></script>

    <style>
        body {
            background-color: #eef2f7;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            margin-top: 50px;
        }
        .navbar {
            background-color: #004085;
            padding: 15px;
        }
        .navbar-brand {
            color: white;
            font-size: 1.5rem;
            font-weight: bold;
        }
        .logout-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            transition: 0.3s;
        }
        .logout-btn:hover {
            background-color: #bd2130;
        }
        .card {
            border: none;
            border-radius: 10px;
            transition: 0.3s;
            box-shadow: 0px 5px 15px rgba(0, 0, 0, 0.1);
        }
        .card:hover {
            transform: scale(1.05);
            box-shadow: 0px 8px 20px rgba(0, 0, 0, 0.2);
        }
        .card-title {
            font-weight: bold;
        }
        .card-icon {
            font-size: 2.5rem;
            color: #004085;
        }
        .footer {
            margin-top: 40px;
            padding: 20px;
            background-color: #004085;
            color: white;
            text-align: center;
        }
    </style>
</head>
<body>

    <!-- Barra de navegación -->
    <nav class="navbar navbar-dark">
        <div class="container-fluid">
            <span class="navbar-brand">Sistema de Facturación</span>
        </div>
    </nav>

    <div class="container text-center">
        <h1 class="mt-4">Bienvenido al Sistema de Facturación</h1>
        <p>Seleccione una opción para gestionar el sistema:</p>

        <div class="row mt-4">

            <!-- Gestión de Clientes -->
            <div class="col-md-4">
                <div class="card p-4">
                    <i class="fas fa-user-tie card-icon"></i>
                    <h5 class="card-title mt-3">Gestión de Clientes</h5>
                    <p>Administra los clientes y sus datos de facturación.</p>
                    <a href="cliente?action=list" class="btn btn-primary">Ver Clientes</a>
                    <a href="Cliente-form.jsp" class="btn btn-success">Nuevo Cliente</a>
                </div>
            </div>

            <!-- Gestión de Artículos -->
            <div class="col-md-4">
                <div class="card p-4">
                    <i class="fas fa-box-open card-icon"></i>
                    <h5 class="card-title mt-3">Gestión de Artículos</h5>
                    <p>Administra los productos y servicios de la empresa.</p>
                    <a href="articulo?action=list" class="btn btn-primary">Ver Artículos</a>
                    <a href="Articulo-form.jsp" class="btn btn-success">Nuevo Artículo</a>
                </div>
            </div>

            <!-- Gestión de Vendedores -->
            <div class="col-md-4">
                <div class="card p-4">
                    <i class="fas fa-user-tag card-icon"></i>
                    <h5 class="card-title mt-3">Gestión de Vendedores</h5>
                    <p>Administra los vendedores y sus comisiones.</p>
                    <a href="vendedor?action=list" class="btn btn-primary">Ver Vendedores</a>
                    <a href="Vendedor-form.jsp" class="btn btn-success">Nuevo Vendedor</a>
                </div>
            </div>
        </div>

        <!-- Segunda fila de opciones -->
        <div class="row mt-4">

            <!-- Facturación de Artículos -->
            <div class="col-md-4">
                <div class="card p-4">
                    <i class="fas fa-file-invoice-dollar card-icon"></i>
                    <h5 class="card-title mt-3">Facturación</h5>
                    <p>Genera y gestiona facturas de ventas.</p>
                    <a href="factura?action=list" class="btn btn-primary">Ver Facturas</a>
                    <a href="Factura-form.jsp" class="btn btn-success">Nueva Factura</a>
                </div>
            </div>

            <!-- Asientos Contables -->
            <div class="col-md-4">
                <div class="card p-4">
                    <i class="fas fa-balance-scale card-icon"></i>
                    <h5 class="card-title mt-3">Asientos Contables</h5>
                    <p>Registra movimientos contables.</p>
                    <a href="asiento?action=list" class="btn btn-primary">Ver Asientos</a>
                    <a href="Asiento-form.jsp" class="btn btn-success">Nuevo Asiento</a>
                </div>
            </div>

            <!-- Reportes (opcional, se puede ocultar si no es necesario) -->
            <c:if test="${sessionScope.role == 'administrador'}">
                <div class="col-md-4">
                    <div class="card p-4">
                        <i class="fas fa-chart-bar card-icon"></i>
                        <h5 class="card-title mt-3">Reportes</h5>
                        <p>Visualiza reportes y estadísticas.</p>
                        <a href="reporte?action=list" class="btn btn-primary">Ver Reportes</a>
                    </div>
                </div>
            </c:if>
        </div>
    </div>

    <!-- Pie de página -->
    <div class="footer">
        <p>&copy; 2024 Sistema de Facturación - Todos los derechos reservados</p
        <p>Domingo Rodriguez - Zoilo Reyes - Carlos Svelti </p
    </div>

</body>
</html>

