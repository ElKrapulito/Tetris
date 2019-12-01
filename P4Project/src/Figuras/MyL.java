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
public class MyL extends Tetrominoes {

    private boolean straight, right, left;

    public MyL(int x, int y) {
        super(x, y);
        super.color = Color.GREEN;
        super.parts.add(new Figure(x, y, color));
        super.parts.add(new Figure(x, y + (bsize * 1), color));
        super.parts.add(new Figure(x, y + (bsize * 2), color));
        super.parts.add(new Figure(x + (bsize * 1), y + (bsize * 2), color));

        straight = true;
        right = false;
        left = false;
    }

    @Override
    public void draw(Graphics g) {
        for (Block part : parts) {
            part.draw(g);
        }
    }

    @Override
    public void move(int x, int y) {
        if (straight && !left && !right) {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x, y + (bsize * 1));
            super.parts.get(2).move(x, y + (bsize * 2));
            super.parts.get(3).move(x + (bsize * 1), y + (bsize * 2));
        } else if (!straight && !left && right) {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x, y + (bsize * 1));
            super.parts.get(2).move(x + (bsize * 1), y);
            super.parts.get(3).move(x + (bsize * 2), y);
        } else if (!straight && left && !right) {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x +(bsize * 1), y);
            super.parts.get(2).move(x + (bsize * 1), y + (bsize * 1));
            super.parts.get(3).move(x + (bsize * 1), y + (bsize * 2));
        } else if (!straight && !left && !right) {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x, y - (bsize * 1));
            super.parts.get(2).move(x - (bsize * 1), y);
            super.parts.get(3).move(x - (bsize * 2), y);
        }
    }

    @Override
    public void turn() {
        if (straight && !left && !right) {
            straight = false;
            left = false;
            right = true;
        } else if (!straight && !left && right) {
            straight = false;
            left = true;
            right = false;
        } else if (!straight && left && !right) {
            straight = false;
            left = false;
            right = false;
        } else if (!straight && !left && !right) {
            straight = true;
            left = false;
            right = false;
        }
    }

}
