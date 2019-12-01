/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author eduardobaldivieso
 */
public class Bar extends Tetrominoes{
    private boolean stand;
    
    public Bar(int x, int y) {
        super(x, y); 
        super.color = Color.CYAN;
        
        super.parts.add(new Figure(x,y,color));
        super.parts.add(new Figure(x,y+bsize * 1,color));
        super.parts.add(new Figure(x,y+bsize * 2,color));
        super.parts.add(new Figure(x,y+bsize * 3,color));
        stand = true;
    }

    @Override
    public void draw(Graphics g) {
        parts.forEach((b) -> {
            b.draw(g);
        });
        
    }

    @Override
    public void move(int x, int y) {
        if(stand){
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x, y+(bsize * 1));
            super.parts.get(2).move(x, y+(bsize * 2));
            super.parts.get(3).move(x, y+(bsize * 3));
        } else {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x+(bsize * 1), y);
            super.parts.get(2).move(x+(bsize * 2), y);
            super.parts.get(3).move(x+(bsize * 3), y);
        }
        
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void turn() {
        stand = !stand;
    }


    
}
