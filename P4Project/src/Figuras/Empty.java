/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Figuras;

import java.awt.Color;

/**
 *
 * @author eduardobaldivieso
 */
public class Empty extends Block{
    private boolean occupied;
    private boolean inmovable;
    
    public Empty(int x, int y, Color color) {
        super(x, y, color);
        this.occupied = false;
        this.inmovable = false;
    }
    
    public Empty(int x, int y, Color color,boolean ocupado) {
        super(x, y, color);
        this.occupied = ocupado;
        this.inmovable = false;
    }

    public Empty(int x, int y, Color color,boolean occupied, boolean inmovable) {
        super(x, y, color);
        this.occupied = occupied;
        this.inmovable = inmovable;
    }
    
    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean ocupado) {
        this.occupied = ocupado;
    }

    public boolean isInmovable() {
        return inmovable;
    }

    public void setInmovable(boolean inmovable) {
        this.inmovable = inmovable;
    }
    
    
}
