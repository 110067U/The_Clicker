package example.isuru.com.wbn_test1.Network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Created by Isuru on 10/22/2015.
 */
public class Client {
    public static DatagramSocket ds;
    public static byte buffer[] = new byte[1024];

    public void clientProcess(){

        try{
//            Socket clientSocket = new Socket(hostName, portNumber);
            Socket clientSocket = new Socket("localhost", 80);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String fromServer;
            String fromUser;

//            while ((fromServer = in.readLine()) != null) {
//                System.out.println("Server: " + fromServer);
//                if (fromServer.equals("Bye."))
//                    break;
//
//                fromUser = stdIn.readLine();
//                if (fromUser != null) {
//                    System.out.println("Client: " + fromUser);
//                    out.println(fromUser);
//                }
//            }
            out.println("hi");
//            /*simple chat */
//            while ((fromServer = in.readLine()) != null) {
//                System.out.println("Server: " + fromServer);
////                out.println(chat());
//            }

        }
        catch (UnknownHostException u){
            System.err.println("Don't know about local host");
            System.exit(1);
        }
        catch (IOException e){
            System.err.println("Couldn't get I/O for the connection to 9998");
            System.exit(1);
        }
    }

//    public void receiveBroadcast()  {
//        ReceiveThread rt = new ReceiveThread(ds);
//        rt.run();
////        sendAnswer("localhost", 80);
//    }

    public void sendAnswer(String hostName, int portNumber){
        try {
            System.out.println("sending............");
            Socket clientSocket = new Socket(hostName, portNumber);
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            out.println("110228P:3");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String fromServer;
            if ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
            }
        }
        catch (IOException i){
            i.printStackTrace();
        }
    }

    public void init() {
        System.out.println("for quitting client press ctrl+c");
        try {
            ds = new DatagramSocket(8080);
        }
        catch (BindException b){
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, b);
        }
        catch (SocketException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
        ReceiveThread rt = new ReceiveThread(ds);
        rt.run();
    }

}
