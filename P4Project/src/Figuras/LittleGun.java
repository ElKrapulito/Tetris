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
public class LittleGun extends Tetrominoes {

    private boolean straight, right, left;

    public LittleGun(int x, int y) {
        super(x, y);
        super.color = Color.red;
        super.parts.add(new Figure(x, y, color));
        super.parts.add(new Figure(x - bsize * 1, y + bsize * 1, color));
        super.parts.add(new Figure(x, y + bsize * 1, color));
        super.parts.add(new Figure(x + bsize * 1, y + bsize * 1, color));
        super.width = 20;
        
        straight = true;
        right = false;
        left = false;
    }

    @Override
    public void draw(Graphics g) {
        parts.forEach((b) -> {
            b.draw(g);
        });

    }

    @Override
    public void move(int x, int y) {
        if (!straight && !right && !left) {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x - bsize * 1, y - bsize * 1);
            super.parts.get(2).move(x, y - bsize * 1);
            super.parts.get(3).move(x + bsize * 1, y - bsize * 1);
        } else if (!straight && right && !left) {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x + bsize * 1, y + bsize * 1);
            super.parts.get(2).move(x + bsize * 1, y);
            super.parts.get(3).move(x + bsize * 1, y - bsize * 1);
        } else if (!straight && !right && left) {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x - bsize * 1, y + bsize * 1);
            super.parts.get(2).move(x - bsize * 1, y);
            super.parts.get(3).move(x - bsize * 1, y - bsize * 1);
        } else if (straight && !right && !left) {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x - bsize * 1, y + bsize * 1);
            super.parts.get(2).move(x, y + bsize * 1);
            super.parts.get(3).move(x + bsize * 1, y + bsize * 1);
        }

        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void turn() {
        if (!straight && !right && !left) {
            straight = false;
            right = true;
            left = false;
        } else if (!straight && right && !left) {
            straight = true;
            right = false;
            left = false;
        } else if (!straight && !right && left) {
            straight = false;
            right = false;
            left = false;
        } else if (straight && !right && !left) {
            straight = false;
            right = false;
            left = true;
        }
    }
}
