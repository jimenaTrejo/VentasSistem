/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Modelo.UsuarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jimena
 */
@WebServlet(name = "Validarr", urlPatterns = {"/Validarr"})
public class Validarr extends HttpServlet {
    UsuarioDAO edao = new UsuarioDAO();
    Usuario em = new Usuario();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       String accion = request.getParameter("accion");
            HttpSession session=request.getSession();        
        if (accion.equalsIgnoreCase("Ingresar")) {
            String nombreUsuario = request.getParameter("nombreUsuario");
            String contrasena = request.getParameter("contrasena");
            em = edao.validar(nombreUsuario, contrasena);
            System.out.println(em);
            if (em.getNombreUsuario()!= null) {
                System.out.println("Soy exitosa");
                session.setAttribute("usuario", em);
                request.getRequestDispatcher("Controladorr?menu=Principal").forward(request, response);
            } else {
                session.invalidate();
                request.getRequestDispatcher("index.jsp").forward(request, response);
                System.out.println("esta mal algo");
            }
        } else {
            session.invalidate();
            request.getRequestDispatcher("index.jsp").forward(request, response);
            System.out.println("Esta mal todo");
        }
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
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    
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
