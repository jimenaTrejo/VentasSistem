package Modelo;

import Config.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Modelo.Productos;

public class ProductosDAO {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public Productos buscar(int id) throws SQLException {
        Productos p = null; // Inicializamos como null para poder comprobar si se encuentra el producto
        String sql = "SELECT * FROM productos WHERE id = ?";
        System.out.println("consulta" + sql);
        try {
            // Establecemos la conexión
            con = cn.Conexion();
            // Preparamos la sentencia SQL
            ps = con.prepareStatement(sql);
            // Asignamos el parámetro a la consulta (evitar inyecciones SQL)
            ps.setInt(1, id);
            System.out.println("soy ps" + ps);

            // Ejecutamos la consulta
            rs = ps.executeQuery();

            // Si encontramos resultados, llenamos el objeto Producto
            if (rs.next()) {
                p = new Productos(); // Creamos un nuevo objeto solo si encontramos datos
                p.setId(rs.getInt("id"));  // Asumimos que la columna se llama 'idProducto'
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setEstado(rs.getString("estatus"));
            }

        } catch (Exception e) {
            // Imprimimos el error si algo sale mal
            System.out.println("Error en la consulta: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // Cerramos los recursos
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }

        System.out.println("Soy el retorno" + p);
        return p;
    }

    public int actualizarstock(int id, int cantidad) {
        String sql = "update productos set cantidad=? where id=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cantidad);
            ps.setInt(2, id);
            ps.executeUpdate();
            ps.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return r;
    }

    //*******Operaciones CRUD***************//    
    public List listar() {
        String sql = "select * from productos";
        List<Productos> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos em = new Productos();
                em.setId(rs.getInt(1));
                em.setNombre(rs.getString(2));
                em.setCantidad(rs.getInt(3));
                em.setEstado(rs.getString(4));
                em.setPrecio(rs.getDouble(5));
                lista.add(em);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        System.out.println(lista + "soy la lista");
        return lista;

    }

    public int agregar(Productos p) {
        System.out.println("Entra a agregar");
        String sql = "insert into productos(id, nombre,cantidad, precio, estatus )values(?,?,?,?, ?)";
        System.out.println(sql);
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setString(2, p.getNombre());
            ps.setInt(4, 0);
            ps.setDouble(3, p.getPrecio());
            ps.setString(5, p.getEstado());
            System.out.println(sql);
            ps.executeUpdate();
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return r;

    }

    public Productos listarId(int id) {
        Productos pr = new Productos();
        String sql = "select * from productos where id=" + id;
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                pr.setId(rs.getInt(1));
                pr.setNombre(rs.getString(2));
                pr.setPrecio(rs.getDouble(3));
                pr.setCantidad(rs.getInt(4));
                pr.setEstado(rs.getString(5));
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return pr;
    }

    public int actualizar(Productos em) {
        String sql = "update productos set nombre=?, precio=?, cantidad=? where id=?";
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, em.getNombre());
            ps.setDouble(2, em.getPrecio());
            ps.setInt(3, em.getCantidad());
            ps.setInt(5, em.getId());
            ps.executeUpdate();
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error:" + e);
        }
        return r;
    }

    public void delete(int id) {
        String sql = "UPDATE productos SET estatus = 'inactivo' WHERE id = ?";
        try {
            con = cn.Conexion(); // Suponiendo que cn.Conexion() devuelve una conexión a la base de datos
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id); // Establece el valor del parámetro id
            ps.executeUpdate();
            // Cerrar los recursos (asegúrate de cerrar solo los que se abrieron en este método)
            ps.close();
        } catch (SQLException e) {
            System.out.println("Error al cambiar el estado: " + e);
        }
    }
//        String sql = "delete from productos where id=" + id;
//        try {
//            con = cn.Conexion();
//            ps = con.prepareStatement(sql);
//            ps.executeUpdate();
//            ps.close();
//            con.close();
//            rs.close();
//        } catch (SQLException e) {
//            System.out.println("Error:" + e);
//        }
//}

    public List<Productos> listarProductosActivos() {
        String sql = "SELECT * FROM productos WHERE estatus = 'ACTIVO'";
        List<Productos> lista = new ArrayList<>();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Productos p = new Productos();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad"));
                p.setEstado(rs.getString("estatus"));
                lista.add(p);
            }
            ps.close();
            con.close();
            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al listar productos activos: " + e);
        }
        return lista;
    }

}
