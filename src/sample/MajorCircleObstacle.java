package sample;

import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.scene.Group;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

public class MajorCircleObstacle extends Obstacle implements Serializable, majorObstacle{

    //ArrayList<CircleObstacle> list;
    private int rado, radi, type, top;

    MajorCircleObstacle(int x, int y, int c) {
        super(x, y, c);
        rado=0;
        radi=0;
        type=0;
        top=0;
        image = new SerImage("file:///C:/Users/shriv/IdeaProjects/Color_Switch/Graphic%20Assets%20CS/MajorCircle.png");
    }

    @Override
    boolean checkX() {
        return true;
    }

    @Override
    boolean checkY(int y) {
        if(Math.abs(yPos+95-y)<=15)
        {
            top = 0;
            return true;
        }
        else if (Math.abs(yPos-y-95)<=15)
        {
            top = 1;
            return true;
        }
        else if(type==1)
        {
            if (Math.abs(yPos+75-y)<=15)
            {
                top=2;
                return true;
            }
            else if (Math.abs(yPos-75-y)<=15)
            {
                top=3;
                return true;
            }
            return false;
        }
        else
            return  false;
    }

    @Override
    void makeHard() {
        type=1;
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
//        for(int i=0;i<20;i++)
//        {
//            CircleObstacle ob = list.get(i);
//            int deltay = yPos-y;
//            int xi = ob.getxPos();
//            int yi = ob.getyPos()-deltay;
//            ob.setPos(xi, yi);
//        }
        xPos=x;
        yPos=y;
    }

    @Override
    void moveX(int x) {
        xPos+=x;
//        for(int i=0;i<=20;i++)
//        {
//            list.get(i).moveX(x);
//        }
    }

    @Override
    void moveY(int y) {
        yPos+=y;
//        for(int i=0;i<=20;i++)
//        {
//            list.get(i).moveY(y);
//        }
    }

    @Override
    Image getImage() {
        return image;
    }

    @Override
    void draw(GraphicsContext gc) {

        gc.save();
        rotate(gc, rado, xPos, yPos);
        gc.drawImage(image, xPos-100, yPos-100, 200, 200);
        gc.restore();
        if(type==1)
        {
            gc.save();
            rotate(gc, radi, xPos, yPos);
            gc.drawImage(image, xPos-80, yPos-80, 160, 160);
            gc.restore();
        }

//        Color cc;
//        cc = getC(color);
//        gc.setFill(cc);
//        gc.setFill(cc);
//        for(int i=0;i<20;i++)
//        {
//            Obstacle ob = list.get(i);
//            ob.draw(gc);
//        }
    }

    /*
    1:Purple
    2:Yellow
    3:Blue
    4:Red
     */
    @Override
    Color getC(int x) {

        //color = list.get(getMid()).getColor();
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
        //color = list.get(getMid()).getColor();

        int rad=rado;
        if(top>=2)
        {
            top-=2;
            rad=radi;
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
    public int getMid()
    {
//        for(int i=0;i<20;i++)
//        {
//            if(list.get(i).checkY(yPos+rad))
//            {
//                return i;
//            }
//        }
        return 0;
    }

    @Override
    public void animate() {
//        int c = list.get(19).getColor();
//        for(int i=0;i<20;i++)
//        {
//            CircleObstacle ob = list.get(i);
//            int tmp = c;
//            c = ob.getColor();
//            ob.setColor(tmp);
//        }

//        ImageView iv = new ImageView(image);
//        RotateTransition r = new RotateTransition(Duration.millis(1000), iv);
//        r.setByAngle(360);
//        //r.setInterpolator(Interpolator.LINEAR);
//        r.setCycleCount(4);
//        r.play();
        rado+=2;
        radi-=3;
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
