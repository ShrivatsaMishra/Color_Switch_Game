package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Arc;
import javafx.scene.shape.ArcType;
import javafx.scene.transform.Rotate;

import java.io.Serializable;

public class SquareObstacles extends Obstacle implements Serializable, majorObstacle {

    private int rad, top;

    SquareObstacles(int x, int y, int c) {
        super(x, y, c);
        rad=0;
        top=0;
        image = new SerImage("file:///C:/Users/shriv/IdeaProjects/Color_Switch/Graphic%20Assets%20CS/RectangleObstacle.png");
    }

    @Override
    boolean checkX() {
        return true;
    }

    @Override
    boolean checkY(int y) {
        int r = rad%90;
        double ycos = 100/Math.cos(Math.toRadians(r));
        double ysin = 100/Math.sin(Math.toRadians(r));
        if(r<45)
        {
            if(Math.abs(yPos-ycos-y)<=5)
            {
                top=0;
                return true;
            }
            if(Math.abs(yPos+ycos-y)<=5)
            {
                top=1;
                return true;
            }
            return false;
        }
        else
        {
            if(Math.abs(yPos-ysin-y)<=5)
            {
                top=0;
                return true;
            }
            if(Math.abs(yPos+ysin-y)<=5)
            {
                top=1;
                return true;
            }
            return false;
        }
    }

    @Override
    void makeHard() {

    }

    @Override
    boolean checkColor(int c) {
        int x = getColor();
        return c!=x;
    }

    @Override
    Color getC(int x) {
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
        if(rad>360)
        {
            rad = rad%360;
        }
        if(top==0)
        {
            if(rad<45)
            {
                return 0;
            }
            else if(rad<135)
            {
                return 3;
            }
            else if(rad<225)
            {
                return 2;
            }
            else if(rad<315)
            {
                return 1;
            }
            return 0;
        }
        else if(top== 1)
        {
            if(rad<45)
            {
                return 2;
            }
            else if(rad<135)
            {
                return 1;
            }
            else if(rad<225)
            {
                return 0;
            }
            else if(rad<315)
            {
                return 3;
            }
            return 2   ;
        }
        return color;
    }

    @Override
    void setColor(int c) {
        return;
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
        xPos=x;
        yPos=y;
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
    void draw(GraphicsContext gc) {
        gc.save();
        rotate(gc, rad, xPos, yPos);
        gc.drawImage(image, xPos-100, yPos-100, 200, 200);
        gc.restore();
    }

    @Override
    Image getImage() {
        return image;
    }

    @Override
    public int getMid() {
        return 0;
    }

    @Override
    public void animate() {
        rad+=2;
    }

    @Override
    int getRot() {
        return rad;
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
