
package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

/**
 *
 * @author eduardobaldivieso
 */
public abstract class Block extends Observable{

    protected int x;
    protected int y;
    private final int size;
    private final Color color;

    
    
    public Block(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
        this.size = 20;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, size, size);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, size, size);
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
        
        this.setChanged();
        this.notifyObservers();
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
    
    
}
