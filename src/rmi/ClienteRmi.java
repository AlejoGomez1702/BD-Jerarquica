package rmi;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import logica.Nodo;

/**
 *
 * @author Alejo
 */
public class ClienteRmi 
{
    private String ipServidor;
    private Nodo nodo;    

    public ClienteRmi(String ipServidor) 
    {
        this.nodo = null;
        this.ipServidor = ipServidor;
        System.out.println("[Client] Especificando el nombre de la politica de seguridad.");        
        System.setProperty("java.security.policy", "client.policy");
        System.out.println("[Cliente] Estableciendo el manejador de seguridad.");
        
        if (System.getSecurityManager() == null)
           // System.setSecurityManager(new RMISecurityManager());
           System.setSecurityManager(new SecurityManager());
    }
    
    public Nodo getNodo(String objName)
    {
        Nodo nodo = null;
        
        try
        {
            System.out.println("[Cliente] Obteniendo la referencia al RMI Registry.");            
            Registry registry = LocateRegistry.getRegistry(this.ipServidor);            
            System.out.println("[Cliente] Buscando la referencia del objeto remoto en el RMI Registry.");
            
            nodo = (Nodo)registry.lookup(objName);
            this.nodo = nodo;

            // saludador = (Saludable) Naming.lookup("//localhost.Saludable");
            // saludador = (Saludable) Naming.lookup("//localhost.Saludable");
            // saludador = (Saludable) Naming.lookup("//localhost/Saludador");

//            System.out.println("[Cliente] Invocando el objeto remoto en múltiples ocasiones.");
//            
//            System.out.println("Hola a las 08:00 am: " + saludador.saludar(8,  "Jorge"));
//            System.out.println("Hola a las 16:00 am: " + saludador.saludar(16, "Jorge"));
//            System.out.println("Hola a las 20:00 am: " + saludador.saludar(20, "Jorge"));
        } 
        catch(RemoteException e) 
        {
            System.out.println("[Cliente] (RemoteException): " + e.getMessage());
        }
        catch(NotBoundException e) 
        {
            System.out.println("[Cliente] (NotBoundException): " + e.getMessage());
        }
        
        return nodo;
    }
    
    
    
}
