package bd;

/**
 * FECHA ==> 2019-09-11.
 * Posee todos los CRUD con la base de datos.
 * @author Luis Alejandro Gómez C.
 * @author Ever Peña B.
 * @version 1.0.0
 */
public class BaseDatos 
{
    /**
     * Conexión con la base de datos.
     */
    private Conexion conexion;
    
    private CrudUsuarios crudUsuarios;
    private CrudGrupos crudGrupos;
        
    
    /**
     * realiza la conexión Prepara todos los cruds con la base de datos.
     * @param conexion Conexión con la base de datos.
     */
    public BaseDatos(Conexion conexion) 
    {
        this.conexion = conexion;
        this.crudUsuarios = new CrudUsuarios(conexion);
        this.crudGrupos = new CrudGrupos(conexion);
    }
    
    /**
     * Obtiene la conexión con la base de datos del sistema.
     * @return Conexión con la base de datos.
     */
    public Conexion getConexion() 
    {
        return conexion;
    }   

    public CrudUsuarios getCrudUsuarios() {
        return crudUsuarios;
    }

    public CrudGrupos getCrudGrupos() {
        return crudGrupos;
    }
    
    

    
    
}
