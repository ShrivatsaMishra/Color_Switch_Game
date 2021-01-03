package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

import java.io.Serializable;

public class DiskObstacle extends Obstacle implements Serializable, majorObstacle {

    private int rado, radi, type, top;

    DiskObstacle(int x, int y, int c) {
        super(x, y, c);
        rado=0;
        radi=0;
        type=1;
        top=0;
        image = new SerImage("file:///C:/Users/shriv/IdeaProjects/Color_Switch/Graphic%20Assets%20CS/Disk.png");
    }

    @Override
    public void makeHard() {
        if(type==0)
            type = 1;
        else
            type=0;
    }

    @Override
    boolean checkX() {
        return true;
    }

    @Override
    boolean checkY(int y) {
        if(Math.abs(yPos+95-y)<=5)
        {
            top = 0;
            return true;
        }
        else if (Math.abs(yPos-y-95)<=5)
        {
            top = 1;
            return true;
        }
        else if(type==1)
        {

            if(Math.abs(yPos-y+80)<=5) {
                top = 2;
                return true;
            }
            else if(Math.abs(yPos-y-80)<=5)
            {
                top = 3;
                return true;
            }
            return false;
        }
        else
            return  false;
    }

    @Override
    boolean checkColor(int c) {
        return c!=getColor();
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
        int rad = rado;
        if(top>1) {
            rad = radi;
            top-=2;
        }
        if(top==1)
        {
            if(rad%360<90)
            {
                return 3;
            }
            else if(rad%360<180)
            {
                return 2;
            }
            else if(rad%360<270)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        if(top==0)
        {
            int r = rad+180;
            if(r%360<90)
            {
                return 3;
            }
            else if(r%360<180)
            {
                return 2;
            }
            else if(r%360<270)
            {
                return 1;
            }
            else
            {
                return 0;
            }
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
        rotate(gc, rado, xPos, yPos);
        gc.drawImage(image, xPos-100, yPos-100, 200, 200);
        gc.restore();
        if(type==1) {
            gc.save();
            rotate(gc, radi, xPos, yPos);
            gc.drawImage(image, xPos - 80, yPos - 80, 160, 160);
            gc.restore();
        }
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
        rado+=2;
        if(rado>=360)
        {
            rado=rado%360;
        }
        if(type==1) {
            radi -= 3;
            if(radi<=0)
            {
                radi+=360;
            }
        }
    }

    @Override
    int getRot() {
        return rado;
    }

    private void rotate(GraphicsContext gc, double angle, double px, double py) {
        Rotate r = new Rotate(angle, px, py);
        gc.setTransform(r.getMxx(), r.getMyx(), r.getMxy(), r.getMyy(), r.getTx(), r.getTy());
    }
}
