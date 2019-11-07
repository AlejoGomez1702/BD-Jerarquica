package main;

import bd.BaseDatos;
import bd.Conexion;
import rmi.ServidorRmi;

/**
 *
 * @author Alejo
 */
public class MainServidor 
{
    public static void main(String[] args) 
    {
        
        Conexion con = new Conexion();
        BaseDatos bd = new BaseDatos(con);
        ServidorRmi server = new ServidorRmi("localhost", bd, "usuarios");
        server.agregarNodo("usuarios", "#datos#usuarios", 0);//Cambiar este 0 por cada servicio
        //server.agregarNodo("grupos", "#datos#grupos", 1);
        
    }
    
    
}
