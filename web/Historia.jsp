<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Historial de Movimientos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <h1>Historia de Movimientos</h1>

        <form action="Controladorr?menu=Historial" method="post">
            <div class="mb-3">
                <select class="form-select" name="accion" id="accion">
                    <option value="Todos">Todos</option>
                    <option value="Entrada">Entrada</option>
                    <option value="Salida">Salida</option>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Filtrar</button>
        </form>

        <table class="table table-striped">
            <thead>
                <tr>
                    <th>ID Movimiento</th>
                    <th>ID Producto</th>
                    <th>Tipo</th>
                    <th>Cantidad</th>
                    <th>Usuario</th>
                    <th>Fecha y Hora</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="movimiento" items="${historial}">
                    <tr>
                        <td>${movimiento.id}</td>
                        <td>${movimiento.idProducto}</td>
                        <td>${movimiento.tipo}</td>
                        <td>${movimiento.cantidad}</td>
                        <td>${movimiento.usuario}</td>
                        <td>${movimiento.fechaHora}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>