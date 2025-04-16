<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>${cliente != null ? "Editar Cliente" : "Nuevo Cliente"}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        .container {
            margin-top: 50px;
            max-width: 600px;
        }
        .form-section {
            background: #ffffff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .form-title {
            font-size: 1.5rem;
            font-weight: bold;
            color: #007bff;
            text-align: center;
            margin-bottom: 20px;
        }
        .btn-container {
            display: flex;
            justify-content: space-between;
            gap: 10px;
        }
        .error-message {
            color: red;
            font-size: 0.9rem;
            margin-top: -10px;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-section">
            <h1 class="form-title">${cliente != null ? "Editar Cliente" : "Nuevo Cliente"}</h1>

            <!-- Formulario -->
            <form action="cliente?action=${cliente != null ? 'update' : 'insert'}" method="post" id="clienteForm">
                <input type="hidden" name="id" value="${cliente != null ? cliente.id : ''}">

                <!-- Campo Nombre Comercial / Razón Social -->
                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre Comercial / Razón Social:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="${cliente != null ? cliente.nombre : ''}" required>
                </div>

                <!-- Campo RNC o Cédula -->
                <div class="mb-3">
                    <label for="rnc_cedula" class="form-label">RNC o Cédula:</label>
                    <input type="number" class="form-control" id="rnc_cedula" name="rnc_cedula" value="${cliente != null ? cliente.rncCedula : ''}" required>
                </div>

                <!-- Campo Cuenta Contable -->
                <div class="mb-3">
                    <label for="cuenta_contable" class="form-label">Cuenta Contable:</label>
                    <input type="number" class="form-control" id="cuenta_contable" name="cuenta_contable" value="${cliente != null ? cliente.cuentaContable : ''}" required>
                </div>

                <!-- Campo Estado -->
                <div class="mb-3">
                    <label for="estado" class="form-label">Estado:</label>
                    <select class="form-control" id="estado" name="estado" required>
                        <option value="Activo" ${cliente != null && cliente.estado == "Activo" ? "selected" : ""}>Activo</option>
                        <option value="Inactivo" ${cliente != null && cliente.estado == "Inactivo" ? "selected" : ""}>Inactivo</option>
                    </select>
                </div>

                <!-- Botones -->
                <div class="btn-container mt-4">
                    <button type="submit" class="btn btn-primary w-100">
                        ${cliente != null ? 'Actualizar' : 'Guardar'}
                    </button>
                    <a href="cliente?action=list" class="btn btn-secondary w-100">Lista</a>
                    <a href="Menu.jsp" class="btn btn-info w-100">Volver al Menú</a>
                </div>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>


