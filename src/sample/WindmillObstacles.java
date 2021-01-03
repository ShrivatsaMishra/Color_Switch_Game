package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.io.Serializable;

public class WindmillObstacles extends Obstacle implements Serializable, majorObstacle {
    private int rad,left, top;

    WindmillObstacles(int x, int y, int c, int left) {
        super(x, y, c);
        this.left = left;
        rad=0;
        top=0;
        image = new SerImage("file:///C:/Users/shriv/IdeaProjects/Color_Switch/Graphic%20Assets%20CS/WindmillObstacle.png");
    }

    @Override
    boolean checkX() {
        rad = rad%360;
        return true;
    }

    @Override
    boolean checkY(int y) {
        double yval = 50/Math.tan(Math.toRadians(rad));
        double xval = 50*Math.tan(Math.toRadians(rad));
        if(rad<=45 || rad>=315 || (rad>135 && rad<225))
        {
            if(Math.abs(yPos+xval-y)<=5)
            {
                top = 0;
                return true;
            }
        }
        else
        {
            if(Math.abs(yPos-yval-y)<=5)
            {
                top = 0;
                return true;
            }
        }
        return  false;
    }

    @Override
    void makeHard() {

    }

    @Override
    boolean checkColor(int c) {
        return c!=getColor();
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
        if(left==1)
        {
            if(rad<45)
            {
                return 1;
            }
            else if(rad<135)
            {
                return 0;
            }
            else if(rad<225)
            {
                return 3;
            }
            else if(rad<315)
            {
                return 2;
            }
            return 1;
        }
        else if(left==0)
        {
            int r = rad+180;
            if(r<45)
            {
                return 1;
            }
            else if(r<135)
            {
                return 0;
            }
            else if(r<225)
            {
                return 3;
            }
            else if(r<315)
            {
                return 2;
            }
            return 1;
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
        if(rad==360)
            rad=0;
        rad+=1;
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
