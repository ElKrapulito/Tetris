/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p4project;

import java.io.IOException;
import server_client_stuff.NetworkClient;

/**
 *
 * @author eduardobaldivieso
 */
public class TestClient {
    public static void main(String[] args) throws IOException {
        NetworkClient n = new NetworkClient();
        n.beginClient("Sam");
    }
}
