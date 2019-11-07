package multicast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import rmi.ServidorRmi;

/**
 *
 * @author Alejo
 */
public class MulticastServer 
{
    private final String multicastAddress = "224.111.112.113";
    private InetAddress group;
    private final int port = 1234;
    private MulticastSocket socket;

    public MulticastServer(ArrayList<String> nombres, ServidorRmi servRmi) 
    {
        try {
            group = InetAddress.getByName(multicastAddress);
            socket = new MulticastSocket(port);
            socket.setBroadcast(false);
            socket.setLoopbackMode(false);
            socket.setTimeToLive(2);
            socket.joinGroup(group);
            new HiloEscucha(socket, nombres, servRmi);
        } catch (Exception e) {
        }
        
    }
    
    public void enviar(String msj)
    {
        try {
            DatagramPacket dp = new DatagramPacket(msj.getBytes(), msj.length(), group, port);
            socket.send(dp);
        } catch (IOException ex) {
            Logger.getLogger(MulticastServer.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    
    
}
