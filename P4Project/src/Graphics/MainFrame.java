/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import javax.swing.JFrame;

/**
 *
 * @author eduardobaldivieso
 */
public class MainFrame extends JFrame{
    private final MainPanel mp;
    private final Thread th;
    
    public MainFrame(){
        mp = new MainPanel();
        th = new Thread(mp);
        th.start();
        
        this.setSize(500,620);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setContentPane(mp);
        
    }
}
