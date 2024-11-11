package Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {
    Connection con;
    String url="jdbc:mysql://localhost:3306/pruebaCastores";
    String user="root";
    String pass="root";
    public Connection Conexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(url,user,pass);
            System.out.println("Conexion exitosa");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error:"+e);
            System.out.println("No hay conexion");
        }
        return con;
    }
    
    
}
