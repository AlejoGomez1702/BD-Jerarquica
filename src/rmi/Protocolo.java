package rmi;

import java.util.ArrayList;

/**
 *
 * @author Alejo
 */
public class Protocolo 
{
    private ArrayList<String> namesNodes;
    private ServidorRmi serve;

    public Protocolo(ServidorRmi serve) 
    {
        this.serve = serve;
    }
       
    
    /**
     * Realiza las comprobaciones para saber que información le esta 
     * pidiendo el cliente al servidor.
     * @param peticionCompleta Código de la petición del cliente.
     * @return La respuesta que le da el cliente al servidor.
     */
    public String comprobarComunicacion(String peticionCompleta, ArrayList<String> namesNodes)
    {
        this.namesNodes = namesNodes;
        String respuesta = "";
        boolean bandera = false;
        String[] mensaje = peticionCompleta.split(",");
        
        if(mensaje[0].equals("add"))
            return addNode(peticionCompleta);
        
        return respuesta;
    }
    
    public String addNode(String pet)
    {
        String[] msj = pet.split(",");
        String name = msj[1];
        String key = msj[2];
        int num = Integer.parseInt(msj[3]);
        
        int nodosActuales = this.namesNodes.size();
        for (int i = 0; i < nodosActuales; i++) 
        {
            if(name.equals(this.namesNodes.get(i)))
            {
                System.out.println("YA EXIIISTEEEEEEEEEEEEEEEEE");
            }
            else
            {
                //Registrando el nodo en el servidor
                this.serve.agregarNodo(name, key, (num+5));
            }
            
        }
        
        return "";
    }
    
}
