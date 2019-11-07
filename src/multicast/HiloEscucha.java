package multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;
import java.util.ArrayList;
import rmi.Protocolo;
import rmi.ServidorRmi;

/**
 *
 * @author Alejo
 */
public class HiloEscucha extends Thread
{
    private MulticastSocket msocket;
    private DatagramPacket recv;
    private Protocolo protocolo;
    private ArrayList<String> namesNodos;
    private ServidorRmi serve;

    public HiloEscucha(MulticastSocket msock, ArrayList<String> namesNodos, ServidorRmi serv)//,InetAddress groupNo , int portNo)
    {
        this.namesNodos = namesNodos;
        this.serve = serv;
        protocolo = new Protocolo(serv);
        msocket = msock;
        start();                // start calls run
    }

    @Override
    public void run()
    {
        byte[] buf = new byte[1000];
        String tmp;
        try
        {
            for(;;)
            {
               // Handle the incoming data and print it to stnd output.
               recv = new DatagramPacket(buf, buf.length);
               msocket.receive(recv);
               tmp = new String(recv.getData(),0,recv.getLength());
               String res = this.protocolo.comprobarComunicacion(tmp, this.namesNodos);
               System.out.println("EL RESULTADOOOO: " + res);
               System.out.println("\n\nRecived: \""+ tmp + "\"\nMessage Length is: " + tmp.length());
            }
        } 
        catch (IOException e) 
        {
            System.out.println("Exit...");
            e.printStackTrace();
        } 
        finally 
        {
            msocket.close();
        }
    }       
    
}
