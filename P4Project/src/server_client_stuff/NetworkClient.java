/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_client_stuff;

/**
 *
 * @author eduardobaldivieso
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class NetworkClient {

    private InetAddress address;
    private Socket s1;
    // private BufferedReader br;
    private BufferedReader is;
    private PrintWriter out;

    public void completedRows(int rows) {
        out.println(rows);
    }

    public void beginClient(String user) throws IOException {
        try {
            address = InetAddress.getLocalHost();
            s1 = new Socket(address, 4445);
            // br = new BufferedReader(new InputStreamReader(System.in));
            is = new BufferedReader(new InputStreamReader(s1.getInputStream()));
            out = new PrintWriter(s1.getOutputStream());

        } catch (IOException e) {
            System.err.print("IO Exception");
        }
        out.println(user);
        out.flush();
        System.out.println("Conectado!");

    }

    public void sendRows(int rows) throws IOException {

        /* if (is.ready()) {
        response = is.readLine();
        System.out.println(response);
        }*/
        if (rows > 0) {
            out.println(rows);
            out.flush();
        }
//        while (!is.ready()) {
//            String s = is.readLine();
//            System.out.println("Recibi algo !" + s);
//            return Integer.parseInt(s);
//        }
//
//        return 0;
        /*if (poner condicion!) {
            is.close();
            out.close();
            //br.close();
            s1.close();
            System.out.println("Connection Closed");
        }*/
    }

    public int readRows() throws IOException {
        if (is.ready()) {
            String s = is.readLine();            
            return Integer.parseInt(s);
        }
        return 0;
    }

}
