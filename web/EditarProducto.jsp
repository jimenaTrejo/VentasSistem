<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Editar Producto</title>
</head>
<body>
    <form action="Controladorr?menu=Producto&accion=Actualizar" method="post">
        <input type="hidden" name="id" value="${producto.id}"/>
        <label>Nombre:</label>
        <input type="text" name="nombre" value="${producto.nombre}" readonly/><br>

        <label>Precio:</label>
        <input type="number" step="0.01" name="precio" value="${producto.precio}" required/><br>

        <label>Cantidad:</label>
        <input type="number" name="cantidad" value="${producto.cantidad}" required/><br>

        <label>Estado:</label>
        <input type="text" name="estado" value="${producto.estado}" required/><br>

        <button type="submit">Actualizar</button>
    </form>
</body>
</html>
