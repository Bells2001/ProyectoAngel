
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ConsultarProveedores extends Conexion{
    
    
    
    public boolean Guardar (Proveedores pro){
        PreparedStatement es = null;
        Connection con = getConexion();
          String sql = "INSERT INTO proveedores(descripcion,direccion,telefono)VALUES(?,?,?)";

        try {
            es = con.prepareStatement(sql);
            es.setString(1, pro.getDescripcion());
            es.setString(2, pro.getDireccion());
            es.setInt(3,pro.getTelefono());
          
            es.execute();
            return true;

            //SI NO SALE GUARDAD SALDRA ERROR
        } catch (SQLException e) {
            System.err.println(e);
            return false;

            //SI NO SE TERMINA LO GUARDADO SE CERRARA
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
        
    }
    
    public boolean Modificar(Proveedores pro){
        PreparedStatement es = null;
        Connection con = getConexion();
          String sql = "UPDATE  proveedores SET descripcion=?, direccion=?, telefono=? where id=?";

        try {
            es = con.prepareStatement(sql);
            es.setString(1, pro.getDescripcion());
            es.setString(2, pro.getDireccion());
            es.setInt(3,pro.getTelefono());
            
            es.setInt(4,pro.getId());
          
            es.execute();
            return true;

            //SI NO SALE GUARDAD SALDRA ERROR
        } catch (SQLException e) {
            System.err.println(e);
            return false;

            //SI NO SE TERMINA LO GUARDADO SE CERRARA
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean Eliminar (Proveedores pro){
        PreparedStatement es = null;
        Connection con = getConexion();
          String sql = "DELETE FROM proveedores WHERE id=?";

        try {
            es = con.prepareStatement(sql);
            es.setInt(1,pro.getId());
          
            es.execute();
            return true;

            //SI NO SALE GUARDAD SALDRA ERROR
        } catch (SQLException e) {
            System.err.println(e);
            return false;

            //SI NO SE TERMINA LO GUARDADO SE CERRARA
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
    public boolean Buscar (Proveedores pro){
         PreparedStatement es = null;
         ResultSet rs=null;
        Connection con = getConexion();
        
        
          String sql = "SELECT * FROM proveedores WHERE telefono=?";

        try {
            es = con.prepareStatement(sql);
            es.setInt(1,pro.getTelefono());
            rs=es.executeQuery();
          
            if(rs.next()){
                pro.setId(Integer.parseInt(rs.getString("id")));
                pro.setDescripcion(rs.getString("Descripcion"));
                pro.setDireccion(rs.getString("Direccion"));
                pro.setTelefono(Integer.parseInt(rs.getString("Telefono")));
                
                return true;

            }
            return false;
                              
            

            //SI NO SALE GUARDAD SALDRA ERROR
        } catch (SQLException e) {
            System.err.println(e);
            return false;

            //SI NO SE TERMINA LO GUARDADO SE CERRARA
        } finally {
            try {
                con.close();
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
    
     public List<Proveedores> listarTodos() {
        List<Proveedores> listaProveedoreses = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM proveedores ORDER BY id";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Proveedores prv = new Proveedores();
                prv.setId(rs.getInt("id"));
                prv.setDescripcion(rs.getString("descripcion"));
                prv.setDireccion(rs.getString("direccion"));
                prv.setTelefono(rs.getInt("telefono"));
               
                listaProveedoreses.add(prv);
            }
            return listaProveedoreses;

        } catch (SQLException e) {
            System.err.println(e);
            return null;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

}
