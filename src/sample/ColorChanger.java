package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.io.Serializable;
import java.util.Random;

public class ColorChanger extends Component implements Serializable {
    private Random rand;

    public ColorChanger(int x, int y)
    {
        super(x, y);
        image = new SerImage("file:///C:/Users/shriv/IdeaProjects/Color_Switch/Graphic%20Assets%20CS/colorchanger.png");
        rand = new Random();
    }

    @Override
    Image getImage()
    {
        return image;
    }

    @Override
    void draw(GraphicsContext gc)
    {
        gc.drawImage(image, xPos-10, yPos-10, 20, 20);
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
    void moveY(int y)
    {
        yPos+=y;
    }

    int getColor()
    {
        return rand.nextInt(5);
    }

    boolean checkPos(int x)
    {
        return Math.abs(yPos-x)<=20;
    }


}
