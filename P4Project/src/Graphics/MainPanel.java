/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graphics;

import coordenadas_figuritas.*;
import Figuras.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;
import javax.swing.Timer;
import server_client_stuff.NetworkClient;

/**
 *
 * @author eduardobaldivieso
 */
public class MainPanel extends JPanel implements KeyListener, Observer, ActionListener {

    private BorderLayout bl;
    private Tetrominoes miniGun;
    private Spaces spaces;
    private int x;
    private int y;
    private boolean conti;
    private Random r;
    private final int bsize;
    private NetworkClient client;
    private Timer t;

    public MainPanel(String user, int w, int h) throws IOException {
        bl = new BorderLayout();
        spaces = new Spaces(w, h);
        client = new NetworkClient();
        client.beginClient(user);

        this.setLayout(bl);
        this.setSize(w, h);
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

    public MainPanel(int w, int h) {
        bl = new BorderLayout();
        spaces = new Spaces(w, h);

        this.setLayout(bl);
        this.setSize(w, h);
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

    public void beginGame() {
        t = new Timer(250, this);
        t.start();
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
                if (client != null) {
                    try {
                        client.sendRows(1);
                    } catch (IOException ex) {
                        Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

//                    int rows = 0;
                //client.sendRows(1);
//                    if (client != null) {
//                        rows = client.readRows();
//                        for (int i = 0; i < rows; i++) {
//                            spaces.putInmovableBlocks();
//                            System.out.println("Hello there!");
//                        }
//                    }
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

    /*@Override
    public void run() {
        while (true) {
            try {
                for (Block part : miniGun.getParts()) {
                    if (part.getY() + part.getSize() >= spaces.getHeight() || spaces.verticaIntersect(miniGun)) {
                        conti = false;
                        spaces.insertFigure(miniGun);
                        int rows = spaces.checkRows();

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
                                miniGun = new MiniS(x, ay);
                                break;
                            case 5:
                                miniGun = new MyL(x, ay);
                                break;
                            case 6:
                                miniGun = new InvL(x, ay);
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
//                for (int i = 0; i < client.readRows(); i++) {
//                    spaces.putInmovableBlocks();
//                    System.out.println("koliii!");
//                }
                Thread.sleep(250);
            } catch (InterruptedException ex) {
                Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }*/
    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            for (Block part : miniGun.getParts()) {
                if (part.getY() + part.getSize() >= spaces.getHeight() || spaces.verticaIntersect(miniGun)) {
                    conti = false;
                    spaces.insertFigure(miniGun);
                    int rows = spaces.checkRows();
                    if (client != null && rows > 0) {
                        client.sendRows(rows);
                    }
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
                            miniGun = new MiniS(x, ay);
                            break;
                        case 5:
                            miniGun = new MyL(x, ay);
                            break;
                        case 6:
                            miniGun = new InvL(x, ay);
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
            if (client != null) {
                for (int i = 0; i < client.readRows(); i++) {
                    spaces.putInmovableBlocks();
                }
            }

//                for (int i = 0; i < client.readRows(); i++) {
//                    spaces.putInmovableBlocks();
//                    System.out.println("koliii!");
//                }
        } catch (IOException ex) {
            Logger.getLogger(MainPanel.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
