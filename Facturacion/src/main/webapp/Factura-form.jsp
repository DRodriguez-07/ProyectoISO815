<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>${factura != null ? "Editar Factura" : "Nueva Factura"}</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body { background-color: #f8f9fa; font-family: Arial, sans-serif; }
        .container { margin-top: 50px; max-width: 600px; }
        .form-title { font-size: 1.5rem; font-weight: bold; color: #007bff; text-align: center; }
        .form-section { background: #ffffff; border-radius: 8px; padding: 20px; box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-section">
            <h1 class="form-title">${factura != null ? "Editar Factura" : "Nueva Factura"}</h1>
            <form action="factura?action=${factura != null ? 'update' : 'insert'}" method="post">
                <input type="hidden" name="id" value="${factura != null ? factura.id : ''}">
                
                <!-- Campo Vendedor -->
                <div class="mb-3">
                    <label for="vendedor_id" class="form-label">Vendedor ID:</label>
                    <input type="number" class="form-control" id="vendedor_id" name="vendedor_id" min="1" required
                           value="${factura != null ? factura.vendedorId : ''}">
                </div>

                <!-- Campo Cliente -->
                <div class="mb-3">
                    <label for="cliente_id" class="form-label">Cliente ID:</label>
                    <input type="number" class="form-control" id="cliente_id" name="cliente_id" min="1" required
                           value="${factura != null ? factura.clienteId : ''}">
                </div>

                <!-- Campo Fecha -->
                <div class="mb-3">
                    <label for="fecha" class="form-label">Fecha:</label>
                    <input type="date" class="form-control" id="fecha" name="fecha" required
                           value="${factura != null ? factura.fecha : ''}">
                </div>

                <!-- Campo Comentario -->
                <div class="mb-3">
                    <label for="comentario" class="form-label">Comentario:</label>
                    <input type="text" class="form-control" id="comentario" name="comentario"
                           value="${factura != null ? factura.comentario : ''}">
                </div>

                <!-- Campo Artículo -->
                <div class="mb-3">
                    <label for="articulo_id" class="form-label">Artículo ID:</label>
                    <input type="number" class="form-control" id="articulo_id" name="articulo_id" min="1" required
                           value="${factura != null ? factura.articuloId : ''}">
                </div>

                <!-- Campo Cantidad -->
                <div class="mb-3">
                    <label for="cantidad" class="form-label">Cantidad:</label>
                    <input type="number" class="form-control" id="cantidad" name="cantidad" required
                           value="${factura != null ? factura.cantidad : ''}">
                </div>

                <!-- Campo Precio Unitario -->
                <div class="mb-3">
                    <label for="precio_unitario" class="form-label">Precio Unitario:</label>
                    <input type="number" step="0.01" class="form-control" id="precio_unitario" name="precio_unitario" min="0" required
                           value="${factura != null ? factura.precioUnitario : ''}">
                </div>

                <!-- Botones -->
                <div class="d-flex justify-content-between mt-4">
                    <button type="submit" class="btn btn-primary">${factura != null ? 'Actualizar' : 'Guardar'}</button>
                    <a href="factura?action=list" class="btn btn-secondary">Lista</a>
                    <a href="Menu.jsp" class="btn btn-info">Menú</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>


