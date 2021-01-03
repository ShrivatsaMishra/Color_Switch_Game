package sample;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.Serializable;
import java.util.Random;

public class Star extends Component implements Serializable {

    public Star(int x, int y) {
        super(x, y);
        image = new SerImage("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/star.png");
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

    boolean checkPos(int y)
    {
        return Math.abs(yPos-y)<=20;
    }

    public void lightMode()
    {
        image = new SerImage("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/colorStar.png");
    }

    public void darkMode()
    {
        image = new SerImage("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/star.png");
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
}
