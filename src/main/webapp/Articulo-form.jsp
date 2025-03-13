<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${articulo != null ? "Editar Artículo" : "Nuevo Artículo"}</title>
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
        .form-title {
            font-size: 1.5rem;
            font-weight: bold;
            color: #007bff;
            text-align: center;
            margin-bottom: 20px;
        }
        .form-section {
            background: #ffffff;
            border-radius: 8px;
            padding: 20px;
            box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
        }
        .btn-container {
            display: flex;
            justify-content: space-between;
            gap: 10px;
        }
    </style>
</head>
<body>
    <div class="container">
        <div class="form-section">
            <h1 class="form-title">${articulo != null ? "Editar Artículo" : "Nuevo Artículo"}</h1>

            <form action="articulo?action=${articulo != null ? 'update' : 'insert'}" method="post">
                <input type="hidden" name="id" value="${articulo != null ? articulo.id : ''}">

                <div class="mb-3">
                    <label for="descripcion" class="form-label">Descripción:</label>
                    <input type="text" class="form-control" id="descripcion" name="descripcion" 
                           value="${articulo != null ? articulo.descripcion : ''}" required>
                </div>

                <div class="mb-3">
                    <label for="precio_unitario" class="form-label">Precio Unitario:</label>
                    <input type="number" step="0.01" class="form-control" id="precio_unitario" name="precio_unitario" min="0"
                           value="${articulo != null ? articulo.precioUnitario : ''}" required>
                </div>

                <div class="mb-3">
                    <label for="estado" class="form-label">Estado:</label>
                    <select class="form-control" id="estado" name="estado" required>
                        <option value="Activo" ${articulo != null && articulo.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                        <option value="Inactivo" ${articulo != null && articulo.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                    </select>
                </div>

                <div class="btn-container mt-4">
                    <button type="submit" class="btn btn-primary w-100">${articulo != null ? 'Actualizar' : 'Guardar'}</button>
                    <a href="articulo?action=list" class="btn btn-secondary w-100">Lista</a>
                    <a href="Menu.jsp" class="btn btn-info w-100">Volver al Menú</a>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
