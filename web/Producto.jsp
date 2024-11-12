<%-- Document   : Producto.jsp
    Created on : 6 nov 2024, 1:15:57
    Author     : Jimena
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>

<div class="container mt-4">
    <h1>Inventario</h1>
    <div class="row">
        <div class="col-sm-4">
            <div class="card">
                <div class="card-body">
                    <form action="Controladorr?menu=Producto" method="POST">
                        <input type="hidden" name="nombreUsuario" value="${usuario.getNombreUsuario()}" />
                        <input type="hidden" name="id" value="${producto.getId()}" />
                        <div class="form-group">
                            <label>Producto</label>
                            <input type="text" value="${producto.getNombre()}" name="txtNombre" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Precio</label>
                            <input type="text" value="${producto.getPrecio()}" name="txtPrecio" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Cantidad</label>
                            <input type="text" value="${producto.getCantidad()}" name="txtCantidad" class="form-control">
                        </div>
                        <div class="form-group">
                            <label>Estado</label>
                            <select class="form-control" name="txtEstado">
                                <option value="1">ACTIVO</option>
                                <option value="0">INACTIVO</option>
                            </select>
                        </div>   

                        <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </form>
                </div>                         
            </div>

        </div>                     
        <div class="col-sm-8">
            <div class="card">
                <div class="card-body">
                    <table class="table table-hover" style="width: 100%" id="example">
                        <thead>
                            <tr class="text-center">
                                <th>#</th>
                                <th>Nombres</th>    
                                <th>Precio</th>
                                <th>Stock</th>                                    
                                <th>ESTADO</th>                                    
                                <th>ACCION</th>
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


                                    <td class="text-center">
                                        <a class="btn btn-outline-warning btn-sm" href="Controladorr?menu=Producto&accion=Editar&id=${em.getId()}"><i class="bi bi-pencil-square"></i></a>
                                        <a class="btn btn-outline-danger btn-sm" href="Controladorr?menu=Producto&accion=Delete&id=${em.getId()}"><i class="bi bi-trash"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>      
</div>      
<%@ include file="commons/footer.jspf"%>


