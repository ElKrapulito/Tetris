/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coordenadas_figuritas;

import Figuras.Block;
import Figuras.Figure;
import Figuras.Tetrominoes;
import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author eduardobaldivieso
 */
public class MiniS extends Tetrominoes {

    private boolean stands;

    public MiniS(int x, int y) {
        super(x, y);
        super.color = Color.PINK;
        super.parts.add(new Figure(x, y, color));
        super.parts.add(new Figure(x - bsize * 1, y, color));
        super.parts.add(new Figure(x - bsize * 1, y + bsize * 1, color));
        super.parts.add(new Figure(x - bsize * 2, y + bsize * 1, color));

        stands = false;
    }

    @Override
    public void draw(Graphics g) {
        for (Block part : parts) {
            part.draw(g);
        }
    }

    @Override
    public void move(int x, int y) {
        if (stands) {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x, y + bsize * 1);
            super.parts.get(2).move(x + bsize * 1, y + bsize * 1);
            super.parts.get(3).move(x + bsize * 1, y + bsize * 2);
        } else {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x - bsize * 1, y);
            super.parts.get(2).move(x - bsize * 1, y + bsize * 1);
            super.parts.get(3).move(x - bsize * 2, y + bsize * 1);
        }
    }

    @Override
    public void turn() {
        stands = !stands;
    }

}
