package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;

abstract class Component implements Serializable {
    protected SerImage image;
    protected int xPos, yPos;

    Component(int x, int y)
    {
        xPos=x;
        yPos=y;
    }

    abstract int getxPos();
    abstract int getyPos();
    abstract void setPos(int x, int y);
    abstract void moveX(int x);
    abstract void moveY(int y);
    abstract void draw(GraphicsContext gc);
    abstract Image getImage();

}
