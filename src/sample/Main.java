package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.SnapshotParameters;

public class Main extends Application implements Serializable {

    static Obstacle obstacles[];
    static Player p;
    static Star s[];
    static ColorChanger c[];
    static SaveState savefile = null;

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent set = FXMLLoader.load(getClass().getResource("/sample/settings.fxml"));
        set = FXMLLoader.load(getClass().getResource("/sample/gameover.fxml"));
        set = FXMLLoader.load(getClass().getResource("/sample/loadscreen.fxml"));
        set = FXMLLoader.load(getClass().getResource("/sample/pausemenu.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("mainmenu.fxml"));


        //String path = "file://Users/prakhar/Desktop/ColorSwitch/src/sample/Icons/backgroundmusic.mp3";
        //Media media = new Media(new File(path).toURI().toString());

        //MediaPlayer mediaPlayer = new MediaPlayer(media);

        //mediaPlayer.setAutoPlay(true);

        Scene scene = new Scene(root, 400, 700);

        primaryStage.setTitle("FXML Welcome");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void Save(String name)
    {
        obstacles = new Obstacle[]{new SquareObstacles(200, 200, 1), new LineCircleObstacle(200, -100, 1), new MajorCircleObstacle(200, -400, 1), new DiskObstacle(200, -750, 1), new WindmillObstacles(150, -1100, 1, 1)};
        p = new Player(200, 500);
        s = new Star[]{new Star(200, 200), new Star(200, -150), new Star(200, -400), new Star(200, -750), new Star(200, -1100)};
        c = new ColorChanger[]{new ColorChanger(200, 50), new ColorChanger(200, -250), new ColorChanger(200, -550), new ColorChanger(200, -900), new ColorChanger(200, -1250)};
        savefile = new SaveState(p, s, obstacles, c, 0);
        try {
            FileOutputStream file = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(file);

            savefile.set(p, s, obstacles, c, 0);
            // Method for serialization of object
            out.writeObject(savefile);

            out.close();
            file.close();

            System.out.println("Object has been serialized");
        }
        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }
    }

    public static void main(String[] args) {
        Save("file.ser");
        launch(args);
    }
}
