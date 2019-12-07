/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import java.io.IOException;
import javax.swing.JFrame;

/**
 *
 * @author eduardobaldivieso
 */
public class MainFrame extends JFrame {

    private final MainPanel mp;
    //private final Thread th;

    public MainFrame(String user) throws IOException {
        mp = new MainPanel(user, 400, 500);
        //th = new Thread(mp);
        //th.start();
        mp.beginGame();
        this.setSize(400, 500 );
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mp);

    }

    public MainFrame() throws IOException {
        mp = new MainPanel(200,320);
        //th = new Thread(mp);
       //th.start();
        mp.beginGame();
        this.setSize(200, 320);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mp);

    }
}
