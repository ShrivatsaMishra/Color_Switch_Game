package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

abstract public class Obstacle extends Component implements Serializable {

    protected int color;
    protected Random rand = new Random();

    Obstacle(int x, int y, int c)
    {
        super(x, y);
        color = c;
    }

    abstract boolean checkX();
    abstract boolean checkY(int y);
    abstract void makeHard();
    abstract boolean checkColor(int c);
    abstract Color getC(int x);
    abstract int getColor();
    abstract void setColor(int c);
    abstract void animate();
    abstract int getRot();


}

interface majorObstacle
{
    int getMid();
}