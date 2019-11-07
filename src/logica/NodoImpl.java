package logica;

import bd.BaseDatos;
import java.rmi.RemoteException;
import modelo.Grupo;
import modelo.Usuario;

/**
 *
 * @author Alejo
 */
public class NodoImpl implements Nodo
{
    private BaseDatos bd;
    private final String domain = "co#edu#autonoma";
    private String llave;
    private String tableName;
    private Crud crud;

    public NodoImpl(String llave, BaseDatos bd) 
    {
        super();
        this.bd = bd;
        this.llave = llave;
        this.crud = new Crud(bd);
        this.tableName = this.getTable();
    }
    
    public String getTable()
    {
        String key = domain + this.llave;
        //System.out.println("LA LLAVE ES: " + key);
        String[] llavecita = key.split("#");
               
        int tam = llavecita.length;
        //System.out.println("El TAMAÑO DE LA LLAVE ES: " + tam);
        String tabla = llavecita[tam-1];
        //System.out.println("EL SACAAAAA: " + tabla);
        
        return tabla;
    }
            
    @Override
    public String crear(String info) throws RemoteException 
    {
        String resultado = "";
        String[] informacion = info.split(",");
        if(this.tableName.equals("usuarios")) //CREAR UN USUARIO
        {
            resultado = this.crud.crearUsuario(informacion);
        }
        else if(this.tableName.equals("grupos")) //CREAR UN GRUPO
        {
            resultado = this.crud.crearGrupo(informacion);            
        }
        
        return resultado;
    }

    @Override
    public String leer(int id) throws RemoteException 
    {
        String resultado = "nada";
        Usuario user = null;
        if(this.tableName.equals("usuarios")) //LEER UN USUARIO
        {
            user = this.bd.getCrudUsuarios().getUserById(id);
            if(user != null)
                resultado = "nombres:" + user.getNombre() + ",apellidos:" + 
                    user.getApellidos() + ",grupo_id:" + user.getGrupo_id();                    
        }   
        else if(this.tableName.equals("grupos")) //LEER UN GRUPO
        {
            Grupo group = this.bd.getCrudGrupos().getGroupById(id);
            if(group != null)
                resultado = "nombre:" + group.getNombres();            
        }
        
        return resultado;
    }

    @Override
    public String actualizar(String info) throws RemoteException 
    {
        String resultado = "";
        if(this.tableName.equals("usuarios")) //ACTUALIZAR UN USUARIO
        {
            String[] informacion = info.split(",");
            //System.out.println("TAMMMAÑOOOO INFOOOO: "+ informacion.length);
            return this.crud.actualizarUsuario(informacion); //"yes" o "no"
        }
        else if(this.tableName.equals("grupos"))
        {
            String[] informacion = info.split(",");
            //System.out.println("TAMMMAÑOOOO INFOOOO: "+ informacion.length);
            return this.crud.actualizarGrupo(informacion); //"yes" o "no"
        }
        
        
        return resultado;
    }

    @Override
    public String eliminar(int id) throws RemoteException 
    {
        String resultado = "nada";
        boolean bandera = false;
        if(this.tableName.equals("usuarios")) //ELIMINAR UN USUARIO
        {
            bandera = this.bd.getCrudUsuarios().deleteUserById(id);
            //bandera==true ? resultado = "yes" : resultado = "no";            
        }   
        else if(this.tableName.equals("grupos"))
        {
            bandera = this.bd.getCrudGrupos().deleteGroupById(id);
        }
        
        if(bandera)
            resultado = "yes";
        else
            resultado = "no";
        
        return resultado;
    }
    
}
