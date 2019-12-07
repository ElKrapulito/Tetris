/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_client_stuff;

import java.io.*;
import java.net.Socket;
import java.util.List;

/**
 *
 * @author eduardobaldivieso
 */
public class ServerThread extends Thread {

    protected Socket socket;
    private BufferedReader br;
    private PrintWriter out;
    private List<Client> list;

    public ServerThread(Socket clientSocket, List<Client> list) {
        this.socket = clientSocket;
        this.list = list;
    }

    @Override
    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e);
        }
        String line;

        try {
            line = br.readLine();

            while (true) {
                if (line != null) {
                    String user = "";
                    for (Client c : list) {
                        if (c.getSocket() == this.socket) {
                            user = c.getUser();
                        }
                    }
                    for (Client s : list) {
                        if (s.getSocket() != this.socket) {
                            out = new PrintWriter(s.getSocket().getOutputStream());
                            out.println(line);
                            out.flush();
                           // System.out.println("Mensaje es: " + line);
                        }
                    }
                    line = br.readLine();
                }

            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException t) {
            System.out.println("Client" + socket.getInetAddress() + " Exiting...");
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (br != null) {
                    br.close();
                    System.out.println(" Socket Input Stream Closed");
                }

                if (out != null) {
                    out.close();
                    System.out.println("Socket Out Closed");
                }
                if (socket != null) {
                    socket.close();
                    System.out.println("Socket Closed");
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }

    }
}
