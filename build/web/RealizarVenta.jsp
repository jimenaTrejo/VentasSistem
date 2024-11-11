<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="container mt-4">
  <div class="row">
    <div class="col-lg-5 parte01 border rounded shadow-sm">
      <div class="card-header bg-primary text-white">
        <h4>Salida de Productos</h4>
      </div>
      <div class="card-body">
        <form action="Controladorr?menu=Ventas&accion=Salida" method="POST">
          <div class="form-group">
            <label for="id">Producto</label>
            <select name="id" class="form-control" required>
              <c:forEach var="producto" items="${productos}">
                <option value="${producto.id}">
                  ${producto.nombre} - Stock: ${producto.cantidad}
                </option>
              </c:forEach>
            </select>
          </div>
          <div class="form-group">
            <label for="cantidadSalida">Cantidad a Retirar</label>
            <input type="number" name="cantidadSalida" class="form-control" required min="1">
          </div>
          <button type="submit" value="Salida" class="btn btn-primary">Procesar Salida</button>
        </form>
      </div>
    </div>

    <div class="col-sm-7 parte02 border rounded shadow-sm">
      <div class="card-header bg-secondary text-white">
        <h4>Detalle de la Venta</h4>
      </div>
      <div class="card-body">

        <c:if test="${not empty mensajeError}">
          <div class="alert alert-danger">${mensajeError}</div>
        </c:if>
        <c:if test="${not empty mensajeExito}">
          <div class="alert alert-success">${mensajeExito}</div>
        </c:if>

        <table class="table table-striped table-hover">
          <thead>
            <tr class="text-center">
              <th>ID</th>
              <th>PRODUCTO</th>
              <th>PRECIO</th>
              <th>CANTIDAD</th>
              <th>ESTADO</th>
              <th>SUBTOTAL</th>
            </tr>
          </thead>
          <tbody>
            <c:forEach var="em" items="${productos}">
              <tr>
                <td class="text-center">${em.getId()}</td>
                <td>${em.getNombre()}</td>
                <td>${em.getPrecio()}</td>
                <td>${em.getCantidad()}</td>
                <td>${em.getEstado()}</td>

                
                <td>${list.getSubtotal()}</td>
              </tr>
            </c:forEach>
          </tbody>
        </table>

        <div class="card-footer bg-light">
          <div class="row">
            <div class="col-sm-6">
              <a href="Controlador?menu=NuevaVenta&accion=GenerarVenta" class="btn btn-success">Generar Venta</a>
              <input type="submit" name="accion" value="Cancelar" class="btn btn-danger">
            </div>
            <div class="col-sm-6 d-flex justify-content-end align-items-center">
              <label class="mr-2">Total a Pagar</label>
              <input type="text" name="txtTotal" value="S/.${totalpagar}0" class="form-control text-center font-weight-bold" style="font-size: 18px;">
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
            
            <div class="form-group">
  <label for="id">Producto</label>
  <select id="productoSelect" name="id" class="form-control" required>
    <c:forEach var="producto" items="${productos}">
      <option value="${producto.id}" data-precio="${producto.precio}">
        ${producto.nombre} - Stock: ${producto.cantidad}
      </option>
    </c:forEach>
  </select>
</div>
<div class="form-group">
  <label for="cantidadSalida">Cantidad a Retirar</label>
  <input type="number" id="cantidadInput" name="cantidadSalida" class="form-control" required min="1">
</div>
<button type="button" id="addProductButton" class="btn btn-primary">Agregar Producto</button>

<!-- Tabla de productos seleccionados -->
<table class="table table-striped table-hover" id="detalleVentaTable">
  <thead>
    <tr class="text-center">
      <th>Producto</th>
      <th>Cantidad</th>
      <th>Precio</th>
      <th>Subtotal</th>
      <th>Eliminar</th>
    </tr>
  </thead>
  <tbody>
  <script>
document.getElementById("addProductButton").addEventListener("click", function() {
  // Obtener los datos del producto seleccionado
  let productoSelect = document.getElementById("productoSelect");
  let productoId = productoSelect.value;
  let productoNombre = productoSelect.options[productoSelect.selectedIndex].text;
  let productoPrecio = parseFloat(productoSelect.options[productoSelect.selectedIndex].getAttribute("data-precio"));
  let cantidad = parseInt(document.getElementById("cantidadInput").value);
  
  if (cantidad > 0) {
    let subtotal = productoPrecio * cantidad;

    // Crear una nueva fila en la tabla
    let tbody = document.getElementById("detalleVentaTable").getElementsByTagName("tbody")[0];
    let row = tbody.insertRow();
    
    row.innerHTML = `
      <td>${productoNombre}</td>
      <td>${cantidad}</td>
      <td>S/. ${productoPrecio.toFixed(2)}</td>
      <td>S/. ${subtotal.toFixed(2)}</td>
      <td><button class="btn btn-danger btn-sm" onclick="removeProduct(this, ${subtotal})">Eliminar</button></td>
    `;

    // Actualizar el total
    updateTotal(subtotal);
  }
});

function removeProduct(button, subtotal) {
  let row = button.closest("tr");
  row.remove();

  // Restar el subtotal del total
  updateTotal(-subtotal);
}

function updateTotal(amount) {
  let totalInput = document.getElementById("totalPagar");
  let currentTotal = parseFloat(totalInput.value.replace('S/.', '') || 0);
  currentTotal += amount;
  totalInput.value = `S/. ${currentTotal.toFixed(2)}`;
}
  </script>

  </tbody>
</table>

<div class="card-footer bg-light">
  <div class="row">
    <div class="col-sm-6">
      <a href="Controlador?menu=NuevaVenta&accion=GenerarVenta" class="btn btn-success">Generar Venta</a>
      <input type="submit" name="accion" value="Cancelar" class="btn btn-danger">
    </div>
    <div class="col-sm-6 d-flex justify-content-end align-items-center">
      <label class="mr-2">Total a Pagar</label>
      <input type="text" id="totalPagar" class="form-control text-center font-weight-bold" style="font-size: 18px;" readonly>
    </div>
  </div>
</div>


<%@ include file="commons/footer.jspf"%>