<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navigate.jspf"%>
<div class="container mt-5 text-center">
    <h1 class="display-4 text-primary mb-4">Bienvenidos a Castores</h1>
    <p class="lead mb-5">Tu servicio confiable de paquetería, siempre a tiempo y con el mejor cuidado.</p>
    
    <div class="d-flex justify-content-center flex-wrap">
        <!-- Botón de Venta Nueva -->
        <div class="p-3">
            <form action="Controlador?menu=NuevaVenta" method="POST">    
                <button type="submit" name="accion" value="ventanueva" class="btn btn-light shadow-lg rounded-circle p-4">
                    <img src="img/venta.jfif" alt="Venta Nueva" class="icon-img" width="90"/>
                </button>
                <div class="mt-2">
                    <h6 class="text-dark">VENTA NUEVA</h6>
                </div> 
            </form>
        </div>
        
        <!-- Botón de Cierre Caja -->
        <div class="p-3">
            <form action="" method="POST">    
                <button type="submit" value="" class="btn btn-light shadow-lg rounded-circle p-4">
                    <img src="img/caja.jfif" alt="Cierre Caja" class="icon-img" width="90"/>
                </button>
                <div class="mt-2">
                    <h6 class="text-dark">CIERRE CAJA</h6>
                </div> 
            </form>
        </div>
        
        <!-- Botón de Reportes -->
        <div class="p-3">
            <form action="" method="POST">    
                <button type="submit" value="" class="btn btn-light shadow-lg rounded-circle p-4">
                    <img src="img/report.jfif" alt="Reportes" class="icon-img"width="90"/>
                </button>
                <div class="mt-2">
                    <h6 class="text-dark">REPORTES</h6>
                </div> 
            </form>
        </div>
        
        <!-- Botón de Otros -->
        <div class="p-3">
            <form action="" method="POST">    
                <button type="submit" value="" class="btn btn-light shadow-lg rounded-circle p-4">
                    <img src="img/otro.jfif" alt="Otros" class="icon-img" width="90"/>
                </button>
                <div class="mt-2">
                    <h6 class="text-dark">OTROS</h6>
                </div> 
            </form>
        </div>
    </div>
</div>
<%@ include file="commons/footer.jspf"%>
