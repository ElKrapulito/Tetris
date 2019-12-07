/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server_client_stuff;

import java.net.Socket;

/**
 *
 * @author eduardobaldivieso
 */
public class Client {
    private String user;
    private final Socket socket;

    public Client(Socket socket) {
        this.socket = socket;
    }

    public Client(String user, Socket socket) {
        this.user = user;
        this.socket = socket;
    }
    
    
    public String getUser() {
        return user;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
    
}
