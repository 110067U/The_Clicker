package example.isuru.com.wbn_test1.Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
/**
 * Created by Isuru on 10/22/2015.
 */
public class ReceiveThread extends Thread {

    private DatagramSocket ds;
    public static byte buffer[] = new byte[1024];

    public ReceiveThread(DatagramSocket ds){
        this.ds = ds;
    }

    @Override
    public void run() {
        try {
            while (true) {
                DatagramPacket p = new DatagramPacket(buffer, buffer.length);
                ds.receive(p);
                String ip = new String(p.getData(),1, p.getLength());
                if(ip.contains(".")){
                    System.out.println(ip);
                    System.out.println("receive");
                    break;
                }
                else{
                    System.out.println("still not get");
                }
            }
        }
        catch (IOException i){
            i.printStackTrace();
        }

    }
}
