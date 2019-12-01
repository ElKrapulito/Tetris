/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Observable;

/**
 *
 * @author eduardobaldivieso
 */
public abstract class Tetrominoes extends Observable{
    
    protected int x,y;
    protected Color color;
    protected ArrayList<Block> parts;
    protected int width;
    protected final int bsize;
    
    public Tetrominoes(int x, int y) {
        Block t = new Figure(0,0,Color.black);
        this.x = x;
        this.y = y;
        parts = new ArrayList();
        bsize = t.getSize();
    }
    
    public abstract void draw(Graphics g);
    
    public abstract void move(int x, int y);
    
    public abstract void turn();

    public ArrayList<Block> getParts() {
        return parts;
    }

    public int getWidth() {
        return width;
    }
    
    
}
