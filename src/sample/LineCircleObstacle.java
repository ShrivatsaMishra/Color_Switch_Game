package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.io.Serializable;
import java.util.ArrayList;

public class LineCircleObstacle extends Obstacle implements Serializable, majorObstacle{

    private ArrayList<CircleObstacle> list;

    LineCircleObstacle(int x, int y, int c) {
        super(x, y, c);
        x=0;
        list = new ArrayList<>();
        for(int i=0;i<=4;i++)
        {
            for(int j=0;j<5;j++)
            {
                list.add(new CircleObstacle(x, y, i));
                x+=25;
            }
        }
    }

    @Override
    boolean checkX() {
        return true;
    }

    @Override
    boolean checkY(int y) {
        return Math.abs(yPos-y)<=20;
    }

    @Override
    void makeHard() {

    }

    @Override
    boolean checkColor(int c) {
        return c!=getColor();
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
        yPos = y;
        xPos = x;
        x=0;
        for(int i=0;i<=20;i++)
        {
            list.get(i).setPos(x,y);
            x+=25;
        }
    }

    @Override
    void moveX(int x) {
        xPos+=x;
        for(int i=0;i<=20;i++)
        {
            list.get(i).moveX(x);
        }
    }

    @Override
    void moveY(int y) {
        yPos+=y;
        for(int i=0;i<=20;i++)
        {
            list.get(i).moveY(y);
        }
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
        gc.setFill(cc);
        for(int i=0;i<20;i++)
        {
            Obstacle ob = list.get(i);
            ob.draw(gc);
        }
    }

    @Override
    Color getC(int x) {

        color = list.get(getMid()).getColor();
        switch (color) {
            case 0:
                return Color.web("#4a30d5", 0.75);
            case 1:
                return Color.web("#ffd700", 0.75);
            case 2:
                return Color.web("#009aff", 0.75);
            case 3:
                return Color.web("#fa535b", 0.75);
        }
        return null;
    }

    @Override
    int getColor() {
        color = list.get(getMid()).getColor();
        return color;
    }

    @Override
    void setColor(int c) {
        return;
    }

    @Override
    public int getMid()
    {
        for(int i=0;i<20;i++)
        {
            if(list.get(i).checkX())
            {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void animate() {
        for(int i=0;i<20;i++)
        {
            CircleObstacle ob = list.get(i);
            if(ob.getxPos()>495)
                ob.setPos(0, yPos);
            else
                ob.moveX(5);
        }
    }

    @Override
    int getRot() {
        return xPos;
    }

}
