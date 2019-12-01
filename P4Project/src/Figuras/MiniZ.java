package Figuras;

import java.awt.Color;
import java.awt.Graphics;

public class MiniZ extends Tetrominoes {

    private boolean stand;

    public MiniZ(int x, int y) {
        super(x, y);
        super.color = Color.MAGENTA;
        super.parts.add(new Figure(x, y, color));
        super.parts.add(new Figure(x + bsize * 1, y, color));
        super.parts.add(new Figure(x + bsize * 1, y + bsize * 1, color));
        super.parts.add(new Figure(x + bsize * 2, y + bsize * 1, color));
        super.width = 30;
        stand = false;
    }

    @Override
    public void draw(Graphics g) {
        parts.forEach((b) -> {
            b.draw(g);
        });
    }

    @Override
    public void move(int x, int y) {
        if (stand) {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x, y + bsize * 1);
            super.parts.get(2).move(x - bsize * 1, y + bsize * 1);
            super.parts.get(3).move(x - bsize * 1, y + bsize * 2);
        } else {
            super.parts.get(0).move(x, y);
            super.parts.get(1).move(x + bsize * 1, y);
            super.parts.get(2).move(x + bsize * 1, y + bsize * 1);
            super.parts.get(3).move(x + bsize * 2, y + bsize * 1);

        }

        this.setChanged();
        this.notifyObservers();
    }

    @Override
    public void turn() {
        stand = !stand;
    }

}
