/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Historial;
import Modelo.HistorialDAO;
import Modelo.Productos;
import Modelo.ProductosDAO;
import Modelo.Usuario;
import Modelo.Ventas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jimena
 */
@WebServlet(name = "Controladorr", urlPatterns = {"/Controladorr"})
public class Controladorr extends HttpServlet {

    Usuario em = new Usuario();
    Usuario edao = new Usuario();

    ProductosDAO pdao = new ProductosDAO();
    Productos p = new Productos();
    int ide;
    int idc;
    int idp;

    Ventas v = new Ventas();
    List<Ventas> lista = new ArrayList<>();
    int item;
    int cod;
    String descripcion;
    double precio;
    int cant;
    double subtotal;
    double totalPagar;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");

        if (menu.equals("Principal")) {
            request.getRequestDispatcher("Principal.jsp").forward(request, response);
        }
        if (menu.equals("Producto")) {
            switch (accion) {
                case "Listar":
                    List<Productos> productos = pdao.listar();
                    request.setAttribute("productos", productos);
                    System.out.println(productos);
                    request.getRequestDispatcher("Producto.jsp").forward(request, response);

                case "Agregar":
                    System.out.println(accion);
                    String nombre = request.getParameter("txtNombre");
                    double precia = Double.parseDouble(request.getParameter("txtPrecio"));
                    int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
                    String estado = request.getParameter("txtEstado");
                    p.setNombre(nombre);
                    p.setCantidad(cantidad);
                    p.setEstado(estado);
                    p.setPrecio(precia);
                    pdao.agregar(p);

                    request.getRequestDispatcher("Controladorr?menu=Producto&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    ide = Integer.parseInt(request.getParameter("id"));
                    Productos producto = pdao.listarId(ide);
                    request.setAttribute("producto", producto);  // Enviamos el producto a la vista para que se llene el formulario
                    request.getRequestDispatcher("Producto.jsp").forward(request, response);
                    break;

                case "Actualizar":
                    int id = Integer.parseInt(request.getParameter("id"));
                    nombre = request.getParameter("txtNombre");
                    precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    cantidad = Integer.parseInt(request.getParameter("txtCantidad"));

                    Productos productoActualizado = new Productos();
                    productoActualizado.setId(id);
                    productoActualizado.setNombre(nombre);
                    productoActualizado.setPrecio(precio);
                    productoActualizado.setCantidad(cantidad);
                    System.out.println("actu" + productoActualizado);

                    pdao.actualizarstock(id, cantidad);

                    response.sendRedirect("Controladorr?menu=Producto&accion=Listar");
                    break;

                case "Delete":
                    ide = Integer.parseInt(request.getParameter("id"));
                    System.out.println("ide" + ide);
                    pdao.delete(ide);
                    request.getRequestDispatcher("Controladorr?menu=Producto&accion=Listar").forward(request, response);
                    break;
                default:
            }
        }
        if (menu.equals("Ventas")) {
            switch (accion) {
                case "Listar":
                    List<Productos> productosActivos = pdao.listarProductosActivos();
                    request.setAttribute("productos", productosActivos);
                    request.getRequestDispatcher("RealizarVenta.jsp").forward(request, response);
                    break;
                case "Salida":
                    // Recibir los datos de salida de inventario
                    System.out.println("se entro a salida");
                    int id = Integer.parseInt(request.getParameter("id"));
                    int cantidadSalida = Integer.parseInt(request.getParameter("cantidadSalida"));
                    System.out.println("idProducto" + id);
                    // Buscar el producto por ID para obtener su stock actual
                    Productos producto = new Productos();
                    try {
                        producto = pdao.buscar(id);
                        System.out.println("estoy en el try" + producto);

                    } catch (SQLException ex) {
                        Logger.getLogger(Controladorr.class.getName()).log(Level.SEVERE, null, ex);
                    }
//                     Verificar que el producto esté activo
//                    if (!producto.getEstado().equals("INACTIVO")) {
//                        request.setAttribute("menssajeError", "El producto no está activo.");
//                        request.getRequestDispatcher("Controladorr?menu=Ventas&accion=Listar").forward(request, response);
//                        break;
//                    }

                    if (producto == null) {
                        request.setAttribute("mensajeError", "Producto no encontrado.");
                        request.getRequestDispatcher("Controladorr?menu=Ventas&accion=Listar").forward(request, response);
                        break;
                    }

                    // Validar que la cantidad solicitada no exceda la cantidad en inventario
                    if (cantidadSalida > producto.getCantidad()) {
                        System.out.println(cantidadSalida + "salida");
                        System.out.println("cantidad" + producto.getCantidad());
                        request.setAttribute("mensajeError", "Cantidad solicitada excede el stock disponible.");
                    } else {
                        // Actualizar el inventario restando la cantidad solicitada
                        int nuevoStock = producto.getCantidad() - cantidadSalida;
                        pdao.actualizarstock(id, nuevoStock);
                        request.setAttribute("mensajeExito", "Salida de producto realizada exitosamente.");
                    }

                    // Redirigir a la página de salida de productos para mostrar resultados
                    request.getRequestDispatcher("Controladorr?menu=Ventas&accion=Listar").forward(request, response);
                    break;

                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción desconocida");
                    break;
            }
        }
         HistorialDAO histoDAO = new HistorialDAO();
List<Historial> historial = null;

if (menu.equals("Historial")) {
    System.out.println(accion + "accion");
    
    switch (accion) {
        case "Entrada":
            // Filtrar solo las entradas
            historial = histoDAO.obtenerHistorialPorTipo("Entrada");
            request.setAttribute("historial", historial);
            request.getRequestDispatcher("Historia.jsp").forward(request, response);

            break;
        case "Salida":
            // Filtrar solo las salidas
            historial = histoDAO.obtenerHistorialPorTipo("Salida");
            request.setAttribute("historial", historial);
            request.getRequestDispatcher("Historia.jsp").forward(request, response);
            break;
        case "Todos":
            // Obtener todos los movimientos sin filtro
            historial = histoDAO.obtenerTodoElHistorial();
            request.setAttribute("historial", historial);
            request.getRequestDispatcher("Historia.jsp").forward(request, response);

            break;
        default:
            // En caso de que no coincida con ninguno de los casos
            historial = new ArrayList<>(); // Lista vacía o un manejo de error
            break;
    }

    // Pasar el historial al atributo de la request para mostrarlo en la vista
    request.setAttribute("historial", historial);
}


       
          
//            HistorialDAO histoDAO = new HistorialDAO();
//            List<Historial> historial = null;
//            if (menu.equals("Historial")) {
//                System.out.println(accion+ "accion");
//            switch (accion) {
//                case "Listar":
//                     request.setAttribute("historial", historial);
//                     request.getRequestDispatcher("Historia.jsp").forward(request, response);
//                       historial = histoDAO.obtenerTodoElHistorial();
//                       request.getRequestDispatcher("Historia.jsp").forward(request, response);
////
//                    break;
//                case "Salida":
//                     List<Productos> productos = pdao.listar();
//                    request.setAttribute("productos", productos);
//                    System.out.println(productos);
//                    request.getRequestDispatcher("Producto.jsp").forward(request, response);
//                    
//                    break;
//                    
//                case "entrada":
//                    historial = histoDAO.obtenerHistorialPorTipo("entrada");
//
//                    System.out.println("soy entrdaa");
//                    
//                    break;
//                case "Todos": 
//                     historial = histoDAO.obtenerTodoElHistorial();
////
//                    break;
//                    default:
//                        historial = histoDAO.obtenerTodoElHistorial(); // Si no se recibe un valor válido, mostrar todo
//                        break;
//                }
//            request.setAttribute("historial", historial);
//            request.getRequestDispatcher("Historia.jsp").forward(request, response);
//        }
//
//        request.setAttribute("historial", historial);
//        request.getRequestDispatcher("Historia.jsp").forward(request, response);
//    
//
//    }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            processRequest(request, response);
            HistorialDAO histoDAO = new HistorialDAO();
            List<Historial> historial = null;
            String menu = request.getParameter("menu");
            String accion = request.getParameter("accion");

        if (menu.equals("Historial")) {          
                // Filtrar el historial en base al tipo de movimiento seleccionado
                System.out.println(accion+ "accion");
                switch (accion) {
                    case "entrada":
                        historial = histoDAO.obtenerHistorialPorTipo("entrada");
                        
                        break;
                    case "salida":
                        historial = histoDAO.obtenerHistorialPorTipo("salida");
                        request.getRequestDispatcher("Historia.jsp").forward(request, response);

                        break;
                    case "todos":
                        historial = histoDAO.obtenerTodoElHistorial();
                        request.getRequestDispatcher("Historia.jsp").forward(request, response);

                        break;
                    default:
                        historial = histoDAO.obtenerTodoElHistorial(); // Si no se recibe un valor válido, mostrar todo
                        break;
                }
            request.setAttribute("historial", historial);
            request.getRequestDispatcher("Historia.jsp").forward(request, response);
        }

        request.setAttribute("historial", historial);
        request.getRequestDispatcher("Historia.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
