/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jimena
 */
public class HistorialDAO {
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;


    // Método para obtener el historial de movimientos filtrados por tipo (entrada/salida)
    public List<Historial> obtenerHistorialPorTipo(String tipo) {
        List<Historial> historial = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement("SELECT * FROM movimientos WHERE tipo = ?");
            ps.setString(1, tipo);
            System.out.println(ps);
            System.out.println(ps+ "ps");
            rs = ps.executeQuery();
            while (rs.next()) {
                Historial hm = new Historial(
                        rs.getInt("id"),
                        rs.getInt("idProducto"),
                        rs.getString("tipo"),
                        rs.getInt("cantidad"),
                        rs.getString("usuario"),
                        rs.getTimestamp("fechaHora")
                );
                historial.add(hm);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener historial: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return historial;
    }
     public int agregarMovimiento(int id, String tipo, int cantidad ) {
        System.out.println("Entra a agregar");
        String sql = "insert into movimientos(idProducto, tipo, cantidad, usuario)values(?,?,?,?)";
        System.out.println(sql);
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, tipo);
            ps.setInt(3, cantidad);
            ps.setString(4, "admin" );
            System.out.println(sql);
            ps.executeUpdate();
            System.out.println("ps" + ps);
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
         System.out.println("salio exitosa" + r);
        return r;
    }

    // Método para obtener todo el historial de movimientos (sin filtro de tipo)
    public List<Historial> obtenerTodoElHistorial() {
        List<Historial> historial = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement("SELECT * FROM movimientos");
            rs = ps.executeQuery();
            while (rs.next()) {
                Historial hm = new Historial(
                        rs.getInt("id"),
                        rs.getInt("idProducto"),
                        rs.getString("tipo"),
                        rs.getInt("cantidad"),
                        rs.getString("usuario"),
                        rs.getTimestamp("fechaHora")
                );
                historial.add(hm);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener historial completo: " + e.getMessage());
        } finally {
            cerrarConexiones();
        }
        return historial;
    }

    // Cerrar las conexiones
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
