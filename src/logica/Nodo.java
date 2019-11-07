package logica;

import java.rmi.*;
import modelo.Usuario;

/**
 *
 * @author Alejo
 */
public interface Nodo extends Remote
{
    String crear(String info)
            throws RemoteException;
    
    String leer(int id)
          throws RemoteException;   
    
    String actualizar(String info)
          throws RemoteException;  
    
    String eliminar(int id)
          throws RemoteException;  
}
