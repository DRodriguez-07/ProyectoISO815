<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${vendedor != null ? "Editar Vendedor" : "Nuevo Vendedor"}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; font-family: Arial, sans-serif; }
        .container { margin-top: 50px; max-width: 600px; }
        .form-title { font-size: 1.5rem; font-weight: bold; color: #007bff; text-align: center; }
        .form-section { background: #ffffff; border-radius: 8px; padding: 20px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }
        .btn-container { display: flex; justify-content: space-between; gap: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-section">
            <h1 class="form-title">${vendedor != null ? "Editar Vendedor" : "Nuevo Vendedor"}</h1>
            <form action="vendedor?action=${vendedor != null ? 'update' : 'insert'}" method="post">
                <input type="hidden" name="id" value="${vendedor != null ? vendedor.id : ''}">

                <div class="mb-3">
                    <label for="nombre" class="form-label">Nombre:</label>
                    <input type="text" class="form-control" id="nombre" name="nombre" value="${vendedor != null ? vendedor.nombre : ''}" required>
                </div>

                <div class="mb-3">
                    <label for="porciento_comision" class="form-label">Porcentaje de Comisión (%):</label>
                    <input type="number" class="form-control" id="porciento_comision" name="porciento_comision" min="0" step="0.01"
                           value="${vendedor != null ? vendedor.porcientoComision : ''}" required>
                </div>

                <div class="mb-3">
                    <label for="estado" class="form-label">Estado:</label>
                    <select class="form-control" id="estado" name="estado" required>
                        <option value="Activo" ${vendedor != null && vendedor.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                        <option value="Inactivo" ${vendedor != null && vendedor.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                    </select>
                </div>

                <div class="btn-container mt-4">
                    <button type="submit" class="btn btn-primary w-100">${vendedor != null ? 'Actualizar' : 'Guardar'}</button>
                    <a href="vendedor?action=list" class="btn btn-secondary w-100">Lista</a>
                    <a href="Menu.jsp" class="btn btn-info w-100">Volver al Menú</a>
                </div>
            </form>
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
