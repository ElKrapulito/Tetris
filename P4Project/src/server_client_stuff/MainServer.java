/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_client_stuff;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardobaldivieso
 */
public class MainServer {

    public static List<Client> list = new ArrayList();
    private BufferedReader br;

    public void beginServer() {

        Socket s = null;
        ServerSocket ss2 = null;
        System.out.println("Server Listening......");
        try {
            ss2 = new ServerSocket(4445); // can also use static final PORT_NUM , when defined

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server error");
        }

        while (true) {
            try {
                s = ss2.accept();
                System.out.println("connection Established");
                br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                String user = br.readLine();
                Client c = new Client(user, s);
                list.add(c);
                System.out.println("Se ingreso a: " + c.getUser());

                ServerThread st = new ServerThread(s, list);
                st.start();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");

            }
        }

    }
}
