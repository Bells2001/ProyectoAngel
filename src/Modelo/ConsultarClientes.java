
package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ConsultarClientes extends Conexion{
    
    public boolean Guardar(Clientes cli) {
        PreparedStatement es = null;
        Connection con = getConexion();

        String sql = "INSERT INTO clientes(descripcion,direccion,ruc,telefono)VALUES(?,?,?,?)";

        try {
            es = con.prepareStatement(sql);
            es.setString(1, cli.getDescripcion());
            es.setString(2, cli.getDireccion());
            es.setInt(3,cli.getRuc());
            es.setInt(4,cli.getTelefono());
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
    public boolean Modificar (Clientes cli){
        PreparedStatement es = null;
        Connection con = getConexion();

        String sql = "UPDATE clientes SET descripcion=?, direccion=?, telefono=?, ruc=? where id=?";

        try {
            es = con.prepareStatement(sql);
            es.setString(1, cli.getDescripcion());
            es.setString(2, cli.getDireccion());
            es.setInt(3,cli.getTelefono());
            es.setInt(4,cli.getRuc());
            
            es.setInt(5, cli.getId());
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
    
    public boolean Eliminar(Clientes cli){
        PreparedStatement es = null;
        Connection con = getConexion();

        String sql = "DELETE FROM clientes WHERE telefono=?";

        try {
            es = con.prepareStatement(sql);
            es.setInt(1, cli.getTelefono());
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
    
    public boolean Buscar(Clientes cli){
        PreparedStatement es = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM clientes WHERE telefono=?";

        try {
            es = con.prepareStatement(sql);
            es.setInt(1, cli.getTelefono());
            rs=es.executeQuery();
            
            if(rs.next()){
                cli.setId(Integer.parseInt(rs.getString("id")));
                cli.setDescripcion(rs.getString("Descripcion"));
                cli.setDireccion(rs.getString("Direccion"));
                cli.setTelefono(Integer.parseInt(rs.getString("Telefono")));
                cli.setRuc(Integer.parseInt(rs.getString("Ruc")));
                
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
     
    public List<Clientes> listarTodos() {
        List<Clientes> listaClientes = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = getConexion();

        String sql = "SELECT * FROM clientes ORDER BY id";

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Clientes prv = new Clientes();
                prv.setId(rs.getInt("id"));
                prv.setDescripcion(rs.getString("descripcion"));
                prv.setDireccion(rs.getString("direccion"));
                prv.setTelefono(rs.getInt("telefono"));
                prv.setRuc(rs.getInt("ruc"));
                listaClientes.add(prv);
            }
            return listaClientes;

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
