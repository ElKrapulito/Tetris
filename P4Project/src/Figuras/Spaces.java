package Figuras;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Observable;

/**
 *
 * @author eduardobaldivieso
 */
public class Spaces extends Observable {

    private final Block[][] miniSpace;
    private final int height;
    private final int width;
    private int solid;
    private int rowSolid;
    private final Empty center;
    private final int bsize;
    
    public Spaces(int x, int y) {
        Block temp = new Figure(0,0,Color.BLACK);
        bsize = temp.getSize();
        miniSpace = new Empty[y / bsize][x / bsize];
        int tempx = 0;
        int tempy = 0;

        for (int i = 0; i < miniSpace.length; i++) {
            tempx = 0;
            for (int j = 0; j < miniSpace[i].length; j++) {
                miniSpace[i][j] = new Empty(tempx, tempy, Color.white);
                tempx += bsize;
            }
            tempy += bsize;
        }
        height = tempy;
        width = tempx;
        solid = miniSpace.length - 1;
        rowSolid = 1;
        center = (Empty)miniSpace[0][(this.width/bsize)/2];
    }

    public void draw(Graphics g) {

        for (Block[] emptys : miniSpace) {
            for (Block empty : emptys) {
                empty.draw(g);
            }
        }
        this.setChanged();
        this.notifyObservers();
    }

    public Empty[][] getMiniSpace() {
        return (Empty[][]) miniSpace;
    }

    public void insertFigure(Tetrominoes figure) {
        if (miniSpace == null) {
            return;
        }

        for (int i = 0; i < miniSpace.length; i++) {
            for (int j = 0; j < miniSpace[i].length; j++) {
                for (Block part : figure.getParts()) {
                    Block block = miniSpace[i][j];
                    if (block.getX() == part.getX() && block.getY() == part.getY()) {
                        block = new Empty(block.getX(), block.getY(), part.getColor(), true);
                        miniSpace[i][j] = block;
                    }
                }
            }

        }

        this.setChanged();
        this.notifyObservers();
    }

    public boolean verticaIntersect(Tetrominoes figure) {
        for (int i = 0; i < miniSpace.length; i++) {
            for (int j = 0; j < miniSpace[i].length; j++) {
                for (Block part : figure.getParts()) {
                    Empty block = (Empty) miniSpace[i][j];
                    if ((block.getX() == part.getX() && block.getY() == (part.getY() + bsize) && block.isOccupied()) || part.getY() + bsize >= this.height) {

                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean horizontalIntersectRight(Tetrominoes figure) {
        for (int i = 0; i < miniSpace.length; i++) {
            for (int j = 0; j < miniSpace[i].length; j++) {
                Empty block = (Empty) miniSpace[i][j];
                for (Block part : figure.getParts()) {
                    if (part.getX() + bsize >= this.width || part.getX() + bsize == block.getX() && part.getY() == block.getY() && block.isOccupied()) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean horizontalIntersectLeft(Tetrominoes figure) {
        for (int i = 0; i < miniSpace.length; i++) {
            for (int j = 0; j < miniSpace[i].length; j++) {
                Empty block = (Empty) miniSpace[i][j];
                for (Block part : figure.getParts()) {
                    if (part.getX() <= 0 || part.getX() - bsize == block.getX() && part.getY() == block.getY() && block.isOccupied()) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    public boolean checkRows() {
        boolean complete = true;
        int rowsDeleted = 0;
        for (int i = 0; i < miniSpace.length; i++) {
            complete = true;
            for (int k = 0; k < miniSpace[i].length; k++) {
                Empty e = (Empty) miniSpace[i][k];
                if (!e.isOccupied() || e.isInmovable()) {
                    complete = false;
                    break;
                }
            }

            if (complete) {
                for (int j = miniSpace.length - rowSolid; j > 0; j--) {
                    for (int k = 0; k < miniSpace[j].length; k++) {
                        Block b = miniSpace[j - 1][k];
                        Empty e = (Empty) miniSpace[j][k];
                        if (b.getColor().equals(Color.white)) {
                            miniSpace[j][k] = new Empty(e.getX(), e.getY(), Color.white);

                        } else {
                            miniSpace[j][k] = new Empty(e.getX(), e.getY(), b.getColor(), true);
                        }
                        miniSpace[j - 1][k] = new Empty(b.getX(), b.getY(), Color.white);
                    }
                }
                rowsDeleted++;
                
            }
        }
        for (int i = 0; i < rowsDeleted; i++) {
            putInmovableBlocks();
        }
        
        this.setChanged();
        this.notifyObservers();
        return complete;
    }
    
    public void putInmovableBlocks(){
        
        outerloop:
        for (int i = miniSpace.length - rowSolid; i > 0; i--) {
            for (int j = 0; j < miniSpace[i].length; j++) {
                Empty e = (Empty) miniSpace[i][j];
                if(!e.isInmovable()){
                    for (int k = 0; k < miniSpace.length - rowSolid; k++) {
                        for (int l = 0; l < miniSpace[k].length; l++) {
                            Empty t = (Empty)miniSpace[k + 1][l];
                            Empty aux = (Empty)miniSpace[k][l];
                            miniSpace[k][l] = new Empty(aux.getX(),aux.getY(),t.getColor(),t.isOccupied());
                        }
                    }
                    for (int k = 0; k < miniSpace[solid].length; k++) {
                        Block b = miniSpace[solid][k];
                        miniSpace[solid][k] = new Empty(b.getX(),b.getY(),Color.gray,true,true);
                    }
                    solid--;
                    rowSolid++;                    
                    this.setChanged();
                    this.notifyObservers();
                    break outerloop;
                }
            }
        }
        
    }
    
    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Empty getCenter() {
        return center;
    }

    
}
