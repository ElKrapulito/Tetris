/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import Figuras.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author eduardobaldivieso
 */
public class MainPanel extends JPanel implements KeyListener, Runnable, Observer {

    private BorderLayout bl;
    private Tetrominoes miniGun;
    private Spaces spaces;
    private int x;
    private int y;
    private boolean conti;
    private Random r;
    private final int bsize; 
    public MainPanel() {
        bl = new BorderLayout();
        spaces = new Spaces(500, 600);

        this.setLayout(bl);
        this.setSize(500, 600);
        this.setOpaque(true);
        this.setBackground(Color.white);
        this.addKeyListener(this);

        x = 0;
        y = 0;
        miniGun = new MiniZ(x, y);
        miniGun.addObserver(this);
        spaces.addObserver(this);
        conti = true;
        r = new Random();
        bsize = spaces.getCenter().getSize();
        
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        spaces.draw(g);
        miniGun.draw(g);

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                x += bsize;
                if (spaces.horizontalIntersectRight(miniGun)) { //x + miniGun.getWidth() >= spaces.getWidth()
                    x = x - bsize;
                }

                break;
            case KeyEvent.VK_LEFT:
                x -= bsize;
                if (spaces.horizontalIntersectLeft(miniGun)) {
                    x = x + bsize;
                }
                break;
            case KeyEvent.VK_DOWN:
                y += bsize;
                if (spaces.verticaIntersect(miniGun)) {
                    y = y - bsize;
                }
                break;
            case KeyEvent.VK_SPACE:
                miniGun.turn();
                break;
            case KeyEvent.VK_UP:
               // y -= 10;

                break;
            default:
                break;
        }

        moveTetrominoe(miniGun, x, y);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    private void moveTetrominoe(Tetrominoes t, int x, int y) {
        Tetrominoes tetro = t;
        tetro.move(x, y);
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (Block part : miniGun.getParts()) {
                    if (part.getY() + part.getSize() >= spaces.getHeight() || spaces.verticaIntersect(miniGun)) {
                        conti = false;
                        spaces.insertFigure(miniGun);
                        spaces.checkRows();
                     
                        y = -bsize;
                        int ay = -100;
                        this.x = spaces.getCenter().getX();
                        switch (r.nextInt(7)) {
                            case 0:
                                miniGun = new LittleGun(x, ay);
                                break;
                            case 1:
                                miniGun = new MiniZ(x, ay);
                                break;
                            case 2:
                                miniGun = new Bar(x, ay);
                                break;
                            case 3:
                                miniGun = new LittleSquare(x, ay);
                                break;
                            case 4:
                                miniGun = new MiniS(x,ay);
                                break;
                            case 5:
                                miniGun = new MyL(x,ay);
                                break;
                            case 6:
                                miniGun = new InvL(x,ay);
                                break;
                            default:
                                miniGun = new LittleGun(x, ay);
                                break;
                        }

                        conti = true;

                    }

                }
                if (conti) {
                    y += bsize;
                    miniGun.move(x, y);
                }

                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
