package sample;

import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

import java.io.Serializable;
import java.util.Random;

public class Player extends Component implements Serializable {
    private int color, stars;
    private static int type;
    private float vel;
    private Random rand = new Random();

    public Player(int x, int y)
    {
        super(x, y);
        color = rand.nextInt(4);
        stars = 0;
        type=5;
        vel=0;
    }

    @Override
    Image getImage()
    {
        return image;
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
        vel = y;
    }

    void drag(int y)
    {
        yPos-=y;
    }


    @Override
    void draw(GraphicsContext gc)
    {
        Color cc;
        cc = getC(color);
        gc.setFill(cc);
        if(type==0)
        {
            gc.fillOval(xPos-10, yPos-10, 20, 20);
        }
        else if(type==1) {
            gc.fillRoundRect(xPos - 10, yPos - 10, 20, 20, 15, 15);
        }
        else if(type==2) {
            gc.fillRect(xPos-10, yPos-10, 20, 20);
        }
        else if(type==3) {
            gc.fillPolygon(new double[]{xPos, xPos - 10, xPos + 10}, new double[]{yPos - 10, yPos + 10, yPos + 10}, 3);
        }
        else if(type==4) {
            gc.fillPolygon(new double[]{xPos,xPos-10, xPos - 10, xPos + 10, xPos+10}, new double[]{yPos-10,yPos, yPos + 10, yPos + 10, yPos}, 5);
        }
        else if(type==5) {
            gc.fillPolygon(new double[]{xPos-10,xPos-10, xPos-5, xPos+6, xPos+10, xPos+10,xPos+6, xPos+6, xPos+3, xPos+3}, new double[]{yPos-10,yPos+7, yPos+10, yPos+10, yPos+7,yPos-7,yPos-7,yPos+3,yPos+3,yPos-10}, 10);
        }
        else if(type==6) {
            gc.fillPolygon(new double[]{xPos-10,xPos-8, xPos+8, xPos+10, xPos+5, xPos,xPos-5}, new double[]{yPos-10,yPos+10, yPos+10, yPos-10, yPos+2,yPos-10,yPos+2}, 7);
        }
        //gc.fillOval(xPos-10, yPos-10, 20, 20);
    }

    public void changeType(int i) {
        type+=i;
        if(type>6) {
            type=type%7;
        }
        while(type<0)
        {
            type+=7;
        }
    }

    public int getType() {
        return type;
    }

    void gravity()
    {
        vel+=1;
        yPos += vel;
    }

    void changeColor(int x)
    {
        color=x;
    }

    int getColor()
    {
        return  color;
    }

    Color getC(int x)
    {

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

    void addStar()
    {
        stars++;
    }

    int getStars() {
        return stars;
    }

    void revive()
    {
        if(stars>=10)
            stars-=10;
    }





}
