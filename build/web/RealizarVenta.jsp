<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="container mt-4">
  <div class="row">
    <div class="col-lg-5 parte01 border rounded shadow-sm">
      <div class="card-header bg-danger text-white">
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
        <h4>Productos disponibles</h4>
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
            
            

<%@ include file="commons/footer.jspf"%>