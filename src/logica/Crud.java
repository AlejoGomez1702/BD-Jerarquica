package logica;

import bd.BaseDatos;
import modelo.Usuario;

/**
 *
 * @author Alejo
 */
public class Crud 
{
    private BaseDatos bd;

    public Crud(BaseDatos bd) {
        this.bd = bd;
    }
    
    //Retorna "YES" o "NO"
    public String crearUsuario(String[] informacion)
    {
        String resultado = "";
        //Informacion tiene 3 posiciones, que significan el campo en la bd.
        String nombres = informacion[0].split(":")[1];
        if(nombres.equals("vacio"))
            nombres = null;
        String apellidos = informacion[1].split(":")[1];
        if(apellidos.equals("vacio"))
            apellidos = null;
        String grupoId = informacion[2].split(":")[1];
        int grupoIdInteger = -1;
        try {
            grupoIdInteger = Integer.parseInt(grupoId);
        } catch (NumberFormatException e) 
        {
            grupoIdInteger = -1;
        }
        //Creando el objeto de usuario.
        Usuario user = new Usuario(nombres, apellidos, grupoIdInteger);
        boolean create = this.bd.getCrudUsuarios().createUser(user);
        if(create)
            resultado = "yes";
        else
            resultado = "no";
        
        return resultado;
    }
    
    //Retorna "YES" o "NO"
    public String actualizarUsuario(String[] informacion)
    {
        int userId;
        String llave;
        String valor;
        
        try 
        {
            userId = Integer.parseInt(informacion[0]);
            llave = informacion[1];
            valor = informacion[2];            
        } catch (NumberFormatException e) 
        {
            //System.out.println("ERRRRRRRROOOOOOOOOOOOOR   #1");
            return "no";            
        }
        
        boolean bandera = this.bd.getCrudUsuarios().updateUserData(userId, llave, valor);
        
        if(bandera)
            return "yes";
        else
            return "no";
    }
    
    //Retorna "YES" o "NO"
    public String crearGrupo(String[] informacion)
    {
        String nombres = informacion[0].split(":")[1];
        if(nombres.equals("vacio"))
            return "no";
        else
        {
            boolean bandera = this.bd.getCrudGrupos().createGroup(nombres);
            if(bandera)
                return "yes";
            else
                return "no";
        }
    }
    
    //Retorna "YES" o "NO"
    public String actualizarGrupo(String[] informacion)
    {
        int groupId;
        String llave;
        String valor;
        
        try 
        {
            groupId = Integer.parseInt(informacion[0]);
            llave = informacion[1];
            valor = informacion[2];            
        } catch (NumberFormatException e) 
        {
            //System.out.println("ERRRRRRRROOOOOOOOOOOOOR   #1");
            return "no";            
        }
        
        boolean bandera = this.bd.getCrudGrupos().updateGroupData(groupId, llave, valor);
        
        if(bandera)
            return "yes";
        else
            return "no";
    }
   
    
    
}
