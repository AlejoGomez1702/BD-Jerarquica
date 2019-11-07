package rmi;

import bd.BaseDatos;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import logica.Nodo;
import logica.NodoImpl;
import multicast.MulticastServer;

/**
 *
 * @author Alejo
 */
public class ServidorRmi 
{
    private String ip;
    private BaseDatos bd;
    private MulticastServer mcs;
    private ArrayList<String> nombres;

    public ServidorRmi(String ip, BaseDatos bd, String name) 
    {
        this.ip = ip;
        this.bd = bd;
        nombres = new ArrayList<>();
        nombres.add(name);
        System.setProperty("java.rmi.server.hostname", ip);
        System.setProperty("java.security.policy", "server.policy");
        if(System.getSecurityManager() == null)                
            System.setSecurityManager(new SecurityManager());      
        mcs = new MulticastServer(this.nombres, this);        
    }
    
    public void agregarNodo(String nombre, String llave, int num)
    {
        this.nombres.add(nombre);
        try 
        {
            System.out.println("[Servidor] Instanciado el objeto que va a ser accedido remotamente.");            
            Nodo unNodo = new NodoImpl(llave, bd);
            System.out.println("[Servidor] Creando el 'stub' del objeto que va a ser accedido remotamente.");            
            Nodo stub = (Nodo) UnicastRemoteObject.exportObject(unNodo,num);
            // Puerto por defecto del RMI Registry: 1099            
            System.out.println("[Servidor] Iniciando su propio servicio de RMI Registry.");            
            Registry registry = LocateRegistry.createRegistry(1099);            
            /*
            // Localizar un 'registry' ya existente, para esto se requiere que
            // previamente se haya ejecutado: SET CLASSPATH=""; rmiregistry &

            System.out.println("[Servidor] Localizando un servicio de RMI Registry ya existente.");
            
            Registry registry = LocateRegistry.getRegistry();
            */
            
            System.out.println("[Servidor] Publicando el stub del objeto remoto en el RMI Registry.");            
            registry.rebind(nombre, stub);
            
            System.out.println("Enviando el mensaje multicast");
            mcs.enviar("add," + nombre + "," + llave + "," + num);
            

            // Naming.rebind("Saludador", stub);   /**/
            // Naming.rebind("//localhost.Saludador", stub);   /**/
            // Naming.rebind("//localhost/Saludador", stub);   /**/

            System.out.println("[Servidor] Servidor listo!");
            
        } catch (RemoteException e) 
        {
            System.out.println("ERROOOOOOOOOOOOOOOOOOOOORRRR");
            
        }
        
    }
    
    public void detectarNodos()
    {
        
    }
    
    
    
}
