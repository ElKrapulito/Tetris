/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenadas_figuritas;

import Figuras.Figure;
import Figuras.Tetrominoes;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author eduardobaldivieso
 */
public class LittleSquare extends Tetrominoes {

    public LittleSquare(int x, int y) {
        super(x, y);
        super.color = Color.PINK;

        super.parts.add(new Figure(x, y, color));
        super.parts.add(new Figure(x + bsize * 1, y, color));
        super.parts.add(new Figure(x, y + bsize * 1, color));
        super.parts.add(new Figure(x + bsize * 1, y + bsize * 1, color));
    }

    @Override
    public void draw(Graphics g) {
        parts.forEach((b) -> {
            b.draw(g);
        });
    }

    @Override
    public void move(int x, int y) {
        super.parts.get(0).move(x, y);
        super.parts.get(1).move(x + bsize * 1, y);
        super.parts.get(2).move(x, y + bsize * 1);
        super.parts.get(3).move(x + bsize * 1, y + bsize * 1);
        
        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void turn() {
        
    }

}
