
package Modelo;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.DriverManager;


public class Conexion {

    public static Statement createStatement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
      Connection con=null;
    
    
    String bd="TIENDA";
    String url="jdbc:mysql://localhost:3311/" + bd;
    String user="root";
    String password="";
    
     public Connection getConexion(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //SIEMPRE SERA ASI EL NOMBRE DE LA CONEXCION
            con=DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            System.err.println(e);
        }
        return con;
    }
}
