<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Historial de Movimientos</title>
    <link rel="stylesheet" href="/css/styles.css">
</head>
<body>
    <h1>Historial de Movimientos</h1>

    <!-- Filtro por tipo de movimiento -->
      <form action="/Controladorr?menu=Historia"  method="post">
        <select name="opcion">
            <%
                // Suponiendo que usas un array de Strings
                String[] opciones = {"todos", "entrada", "salida"};
                for (String opcion : opciones) {
                    out.println("<option value=\"" + opcion + "\">" + opcion + "</option>");
                }
            %>
            
        </select>w
        
        <input type="submit" value="Enviar">
        <input type="submit" name="accion" value="Todos" class="btn btn-primary">
        <input type="submit" name="accion" value="entrada" class="btn btn-primary">
        <input type="submit" name="accion" value="salida" class="btn btn-primary">


    </form>

    <!-- Tabla de historial de movimientos -->
    <table>
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
            <!-- Iterar sobre la lista de historial y mostrar cada movimiento -->
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

    <a href="/inventario">Volver al Inventario</a>
</body>
</html>
