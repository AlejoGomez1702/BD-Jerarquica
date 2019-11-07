package bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Grupo;
import modelo.Usuario;

/**
 *
 * @author Alejo
 */
public class CrudGrupos 
{
    /**
     * Conexión con la base de datos.
     */
    private Conexion conexion;

    public CrudGrupos(Conexion conexion) 
    {
        this.conexion = conexion;
    }
    
    public Grupo getGroupById(int id)
    {
        Grupo grupo = null;
        String name;
        
        String consulta = "SELECT * FROM grupos WHERE id = ?";
        
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {                
                name = rs.getString("nombres");
                grupo = new Grupo(name);
                //System.out.println("Si desde verificar ADmin    ");
            }            
        } catch (SQLException e) 
        {
            return null;
        }
        
        return grupo;        
    }
    
    public boolean createGroup(String groupName)
    {
        boolean bandera = true;
        if(groupName == null)
            return false;
        
        String consulta = "INSERT INTO grupos (nombres)"
                        + " VALUES (?)";
 
        try 
        {
            PreparedStatement ps = this.conexion.getConexion().prepareStatement(consulta);
            ps.setString(1, groupName);
            ps.executeUpdate();            
        } catch (SQLException e) 
        {
            bandera = false;
        }                                
        
        return bandera;        
    }
    
    public boolean deleteGroupById(int id)
    {
        if(id <= 0)
            return false;
        
        boolean bandera = true;
        String consulta = "DELETE FROM grupos WHERE id = ?";
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
    
    public boolean updateGroupData(int id, String llave, String valor)
    {
        //System.out.println("PEROOO SIIIII ENTRAAA");
        boolean bandera = true;
        if(id <= 0 || llave == null || valor == null)
        {
            //System.out.println("SE CUMPLLEEEE LA CONDICION");
            return false;
        }
            
        
        String consulta = "UPDATE grupos SET " + llave + " = ? WHERE id = ? ";
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
