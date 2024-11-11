/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author Jimena
 */
import Config.Conexion;

import java.sql.*;
import Modelo.Usuario;

public class UsuarioDAO {

    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;

    // Método para validar el login del usuario
    public Usuario validar(String nombreUsuario, String contrasena) {
        Usuario usuario = new Usuario();
        String sql = "SELECT * FROM usuarios WHERE nombreUsuario = ? AND contrasena = ?";
        con = cn.Conexion();
        System.out.println(sql);
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, nombreUsuario);
            ps.setString(2, contrasena);
            rs = ps.executeQuery(); // Corrección: usar executeQuery sin parámetros

            if (rs.next()) { // Solo necesitas una llamada a rs.next()
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
            } else {
                System.out.println("Usuario no encontrado o contraseña incorrecta.");
            }
        } catch (SQLException e) {
            System.out.println("Error en la validación del usuario: " + e.getMessage());
        }
        System.out.println(usuario + "usuarios");
        return usuario;
        
    }

    // Método para obtener un usuario por su ID
    public Usuario obtenerUsuarioPorId(int id) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNombreUsuario(rs.getString("nombreUsuario"));
                usuario.setContrasena(rs.getString("contrasena"));
                usuario.setRol(rs.getString("rol"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el usuario por ID: " + e.getMessage());
        } 

        return usuario;
    }

    // Método para registrar un nuevo usuario
    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombreUsuario, contrasena, rol) VALUES (?, ?, ?)";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getRol());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al registrar el usuario: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }

        return false;
    }

    // Método para actualizar los datos del usuario (por ejemplo, cambiar contrasena)
    public boolean actualizarUsuario(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombreUsuario = ?, contrasena = ?, rol = ? WHERE id = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getContrasena());
            ps.setString(3, usuario.getRol());
            ps.setInt(4, usuario.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }

        return false;
    }

    // Método para eliminar un usuario (baja lógica)
    public boolean eliminarUsuario(int id) {
        String sql = "UPDATE usuarios SET estatus = 'INACTIVO' WHERE id = ?";

        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }

        return false;
    }

    // Cerrar las conexiones a la base de datos
    private void cerrarConexiones() {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexiones: " + e.getMessage());
        }
    }
}
