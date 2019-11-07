package main;

import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.Nodo;
import rmi.ClienteRmi;

/**
 *
 * @author Alejo
 */
public class MainCliente 
{
    public static void main(String[] args) 
    {
        //Le paso la IP del servidor
        ClienteRmi cliente = new ClienteRmi("localhost");
        //Con este cliente me conecto al objeto "usuarios".
        //Nodo n = cliente.getNodo("usuarios");
        //Nodo n2 = cliente.getNodo("grupos");
        
        //Ventana del cliente
        VentanaCliente ventana = new VentanaCliente(cliente);
        ventana.setVisible(true);
        
        
//        try 
//        {   
            //n2.actualizar("1,nombres,raspachin");
            //n2.eliminar(2);
            //String res = n2.crear("nombres:Docentes,vacio:vacio,vacio:vacio");
//            String crearGroup = n2.leer(1);
//            System.out.println("RESULTADOO: " + crearGroup);
            
//            String actualizar = n.actualizar("1,apellidos,Contreras");
//            if(actualizar.equals("yes"))
//                System.out.println("Actualiiiizoooo");
//            else
//                System.out.println("nnnnnooooooooooo6");
            
//            String eliminar = n.eliminar(2);
//            if(eliminar.equals("yes"))

//                System.out.println("SIIIIIIIIIIIUUUUUUUUUUUU");
//            else
//                System.out.println("NNNNNNOOOOOOOOOOOOOOOOOOOOOOOOOO");
            
//            String crear = n.crear("nombres:Ever,apellidos:Pena,vacio:vacio");
//            if(crear.equals("yes"))
//                System.out.println("EPPPPPPPPPPPPPPPPPPPPPPPAAAAAAAAAAA");
//            else
//                System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
                    
            //System.out.println("ESTOY LEYENDO PAPI");
            //String userInfo = n.leer(1);
            //System.out.println("SACANDOLE AL USUARIO");
            //System.out.println(userInfo);
            
//        } catch (RemoteException ex) {
//            Logger.getLogger(MainCliente.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
    }
    
}
