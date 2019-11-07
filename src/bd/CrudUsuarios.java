/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Usuario;

/**
 *
 * @author Alejo
 */
public class CrudUsuarios 
{
    /**
     * Conexión con la base de datos.
     */
    private Conexion conexion;

    public CrudUsuarios(Conexion conexion) 
    {
        this.conexion = conexion;
    }
    
    public Usuario getUserById(int id)
    {
        Usuario user = null;
        String name;
        String apellidos;
        int grupo_id;
        
        String consulta = "SELECT * FROM usuarios WHERE id = ?";
        
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                name = rs.getString("nombres");
                apellidos = rs.getString("apellidos");
                grupo_id = rs.getInt("grupo_id");
                user = new Usuario(name, apellidos, grupo_id);
                break;
                //System.out.println("Si desde verificar ADmin    ");
            }            
        } catch (SQLException e) 
        {
            //System.out.println(e.getMessage());
            return null;
        }
        
        return user;        
    }
    
    public boolean createUser(Usuario user)
    {
        boolean bandera = true;
        String names = user.getNombre(); 
        String lastNames = user.getApellidos();
        int groupId = user.getGrupo_id();
        
        String consulta = "";
        if(groupId == -1)
        {
            consulta = "INSERT INTO usuarios (nombres, apellidos)"
                        + " VALUES (?, ?)";
        }
        else
        {
            consulta = "INSERT INTO usuarios (nombres, apellidos, grupo_id)"
                        + " VALUES (?, ?, ?)";
        }     
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setString(1, names);
            ps.setString(2, lastNames);
            if(groupId != -1)
                ps.setInt(3, groupId);
            ps.executeUpdate();            
        } catch (SQLException e) 
        {
            bandera = false;
        }                                
        
        return bandera;        
    }
    
    public boolean deleteUserById(int id)
    {
        if(id <= 0)
            return false;
        
        boolean bandera = true;
        String consulta = "DELETE FROM usuarios WHERE id = ?";
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setInt(1,  id);
            ps.executeUpdate(); 
        } catch (SQLException e) 
        {
            return false;
        }
        
        return bandera;
    }
    
    public boolean updateUserData(int id, String llave, String valor)
    {
        //System.out.println("PEROOO SIIIII ENTRAAA");
        boolean bandera = true;
        if(id <= 0 || llave == null || valor == null)
        {
            //System.out.println("SE CUMPLLEEEE LA CONDICION");
            return false;
        }
            
        
        String consulta = "UPDATE usuarios SET " + llave + " = ? WHERE id = ? ";
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            //ps.setString(1, llave);
            ps.setString(1, valor);
            ps.setInt(2, id);
            //System.out.println("ANTEEESSS DEEEE");
            ps.executeUpdate(); 
            //System.out.println("DESPPUUUESSS DEEEEE");
        } catch (SQLException e) 
        {
            //System.out.println("MUCHHHHAAA PPPERRRAAA: " + e.getMessage());
            return false;
        }
        
        return bandera;
    }
    
    
}
