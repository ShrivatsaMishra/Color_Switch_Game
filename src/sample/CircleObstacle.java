package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.Serializable;

public class CircleObstacle extends Obstacle implements Serializable {

    private Circle crcl;
    private int type=0;

    CircleObstacle(int x, int y, int c) {
        super(x, y, c);
        crcl = new Circle(20, getC(color));
    }

    @Override
    public void makeHard()
    {
        if(type==0)
            type=1;
        else
            type=0;
    }

    @Override
    boolean checkX() {
        return Math.abs(xPos-200)<=10;
    }

    @Override
    boolean checkY(int y) {
        return Math.abs(yPos-y)<=20;
    }

    @Override
    boolean checkColor(int c) {
        return c==color;
    }

    @Override
    int getxPos() {
        return xPos;
    }

    @Override
    int getyPos() {
        return yPos;
    }

    @Override
    void setPos(int x, int y) {
        xPos = (x);
        yPos = (y);
    }

    @Override
    void moveX(int x) {
        xPos+=x;
    }

    @Override
    void moveY(int y) {
        yPos+=y;
    }

    @Override
    Image getImage() {
        return null;
    }

    @Override
    void draw(GraphicsContext gc) {
        Color cc;
        cc = getC(color);
        gc.setFill(cc);
        gc.fillOval(xPos, yPos, 20, 20);
    }

    @Override
    Color getC(int x) {

        switch (color) {
            case 0:
                return Color.web("#4a30d5", 1);
            case 1:
                return Color.web("#ffd700", 1);
            case 2:
                return Color.web("#009aff", 1);
            case 3:
                return Color.web("#fa535b", 1);
        }
        return null;
    }

    @Override
    int getColor() {
        return color;
    }

    @Override
    void setColor(int c) {
        color = c;
    }

    @Override
    void animate() {
        return;
    }

    @Override
    int getRot() {
        return 0;
    }
}
