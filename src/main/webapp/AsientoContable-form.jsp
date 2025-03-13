<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>${asiento != null ? "Editar Asiento Contable" : "Nuevo Asiento Contable"}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; font-family: Arial, sans-serif; }
        .container { margin-top: 50px; max-width: 600px; }
        .form-title { font-size: 1.5rem; font-weight: bold; color: #007bff; margin-bottom: 20px; text-align: center; }
        .form-section { background: #ffffff; border-radius: 8px; padding: 20px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }
        .btn-container { display: flex; justify-content: space-between; gap: 10px; }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-section">
            <h1 class="form-title">${asiento != null ? "Editar Asiento Contable" : "Nuevo Asiento Contable"}</h1>

            <form action="asiento?action=${asiento != null ? 'update' : 'insert'}" method="post">
                <input type="hidden" name="id" value="${asiento != null ? asiento.id : ''}">

                <!-- Descripción -->
                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción:</label>
                    <input type="text" class="form-control" id="descripcion" name="descripcion" value="${asiento != null ? asiento.descripcion : ''}" required>
                </div>

                <!-- Cliente ID -->
                <div class="mb-3">
                    <label for="cliente_id" class="form-label">ID Cliente:</label>
                    <input type="number" class="form-control" id="cliente_id" name="cliente_id" min="1" value="${asiento != null ? asiento.clienteId : ''}" required>
                </div>

                <!-- Cuenta -->
                <div class="mb-3">
                    <label for="cuenta" class="form-label">Cuenta:</label>
                    <input type="text" class="form-control" id="cuenta" name="cuenta" value="${asiento != null ? asiento.cuenta : ''}" required>
                </div>

                <!-- Tipo de Movimiento -->
                <div class="mb-3">
                    <label for="tipo_movimiento" class="form-label">Tipo de Movimiento:</label>
                    <select class="form-control" id="tipo_movimiento" name="tipo_movimiento" required>
                        <option value="DB" ${asiento != null && asiento.tipoMovimiento == 'DB' ? 'selected' : ''}>Débito</option>
                        <option value="CR" ${asiento != null && asiento.tipoMovimiento == 'CR' ? 'selected' : ''}>Crédito</option>
                    </select>
                </div>

                <!-- Fecha Asiento -->
                <div class="mb-3">
                    <label for="fecha" class="form-label">Fecha de Asiento:</label>
                    <input type="datetime-local" class="form-control" id="fecha" name="fecha" value="${asiento != null ? asiento.fecha : ''}" required>
                </div>

                <!-- Monto -->
                <div class="mb-3">
                    <label for="monto" class="form-label">Monto:</label>
                    <input type="number" step="0.01" class="form-control" id="monto" name="monto" value="${asiento != null ? asiento.monto : ''}" required>
                </div>

                <!-- Estado -->
                <div class="mb-3">
                    <label for="estado" class="form-label">Estado:</label>
                    <select class="form-control" id="estado" name="estado" required>
                        <option value="Activo" ${asiento != null && asiento.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                        <option value="Inactivo" ${asiento != null && asiento.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                    </select>
                </div>

                <!-- Botones -->
                <div class="btn-container mt-4">
                    <button type="submit" class="btn btn-primary w-100">${asiento != null ? 'Actualizar' : 'Guardar'}</button>
                    <a href="asiento?action=list" class="btn btn-secondary w-100">Lista</a>
                    <a href="Menu.jsp" class="btn btn-info w-100">Volver al Menú</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
