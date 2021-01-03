package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.*;
import java.util.Random;

public class GameMenu{

    private static int speed = 10;
    private static long tickcount = 0;
    private static int move = 0;
    private static int gameMode = 1;
    private static Random rand = new Random();
    private static Obstacle obstacles[];
    private static Player p;
    private static Star s[];
    private static ColorChanger c[];
    private static SaveState savefile = null;
    private static String filename = "file.ser";
    private static Stage st;
    private static int HardMode=0;
    private static int Drag=0;
    private static int bgType=2;
    private static Image bgretro= new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/Graphic%20Assets%20CS/retro.png");
    private static Image bgnorm= new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/Graphic%20Assets%20CS/normal.png");
    private static Image bghalo= new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/Graphic%20Assets%20CS/halloween.png ");
    private static Image bglight= new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/Graphic%20Assets%20CS/light.png");
    private static Image pause= new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/Pause.png");
    private static Image star= new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/star.png");


    public GameMenu() {
        obstacles = new Obstacle[]{new SquareObstacles(200, 200, 1), new LineCircleObstacle(200, -100, 1), new MajorCircleObstacle(200, -400, 1), new DiskObstacle(200, -750, 1), new WindmillObstacles(150, -1100, 1, 1)};
        p = new Player(200, 500);
        s = new Star[]{new Star(200, 200), new Star(200, -150), new Star(200, -400), new Star(200, -750), new Star(200, -1100)};
        c = new ColorChanger[]{new ColorChanger(200, 50), new ColorChanger(200, -250), new ColorChanger(200, -550), new ColorChanger(200, -900), new ColorChanger(200, -1250)};
        savefile = new SaveState(p, s, obstacles, c, gameMode);
    }

    public void StartGame(Stage stage)
    {
        st=stage;
        Load(filename);
        newGame();
        gameMode=1;
        StartnewGame();
    }

    public void ReStartGame(Stage stage)
    {
        st=stage;
        Load(filename);
        newGame();
        gameMode=1;
        StartnewGame();
    }

    public void Revive(Stage stage)
    {
        if(p.getStars()>=10) {
            st = stage;
            Load(filename);
            p.revive();
            if(p.getyPos()>=700)
            {
                p.setPos(200, 500);
            }
            for(int i=0;i<5;i++)
            {
                while (obstacles[i].checkX() && obstacles[i].checkY(p.getyPos()) && obstacles[i].checkColor(p.getColor())) {
                    p.setPos(200, (rand.nextInt(50)*10)+100);
                }
            }
            gameMode = 1;
            StartnewGame();
        }
    }

    public void Resume(Stage stage)
    {
        st=stage;
        Load(filename);
        gameMode=1;
        StartnewGame();
    }

    public void playerTypeChange(int i) {
        p.changeType(i);
    }

    public int getPlayerType() {
        return p.getType();
    }

    public void bgChange(int x)
    {
        bgType=x;
        if(bgType==1)
        {
            for(int i=0;i<5;i++)
            {
                s[i].lightMode();
            }
            pause = new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/lightPause.png");
            star = new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/colorStar.png");
        }
        else
        {
            for(int i=0;i<5;i++)
            {
                s[i].darkMode();
            }
            star = new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/star.png");
            pause = new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/Pause.png");
        }
    }

    private static void Save(String name)
    {
        try {
            FileOutputStream file = new FileOutputStream(name);
            ObjectOutputStream out = new ObjectOutputStream(file);

            savefile.set(p, s, obstacles, c, gameMode);
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

    private static void Load(String name)
    {
        try
        {
            // Reading the object from a file
            FileInputStream file = new FileInputStream(name);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            savefile = (SaveState) in.readObject();

            p = savefile.getP();
            for(int i=0;i< savefile.getObsX().length;i++)
            {
                obstacles[i].setPos(savefile.getObsX()[i], savefile.getObsY()[i]);
                s[i].setPos(savefile.getStarLocx()[i], savefile.getStarLocy()[i]);
                c[i].setPos(savefile.getColorChangerx()[i], savefile.getColorChangery()[i]);
            }

            gameMode = savefile.getGameMode();

            in.close();
            file.close();

            System.out.println("Object has been deserialized ");
            System.out.println("a = " + p.getStars());
            System.out.println("b = " + gameMode);
        }

        catch(IOException ex)
        {
            System.out.println("IOException is caught");
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
        }
    }

    private void StartnewGame()
    {
        VBox root = new VBox();
        Canvas canvas = new Canvas(400, 700);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        new AnimationTimer() {

            long lastTick = 0;

            @Override
            public void handle(long now) {
                if (lastTick == 0) {
                    lastTick = now;
                    tick(gc);
                    return;
                }
                if (now - lastTick > 1000000000 / speed) {
                    lastTick = now;
                    tick(gc);
                }
                if(gameMode==0) {
                    Save(filename);
                    this.stop();
                    Parent goPar = null;
                    try {
                        goPar = FXMLLoader.load(getClass().getResource("/sample/gameover.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene goScene = new Scene(goPar);

                    st.setScene(goScene);
                    st.show();
                }
            }
        }.start();

        Scene scene = new Scene(root, 400, 700);

        //control
        scene.addEventHandler(MouseEvent.MOUSE_CLICKED, key -> {
            //Pause
            if(key.getX()>=350&&key.getY()<=50)
            {
                Save(filename);
                gameMode=2;
                Parent rootpause = null;
                try {
                    rootpause = FXMLLoader.load(getClass().getResource("/sample/pausemenu.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Scene pausescene = new Scene(rootpause, 400, 700);

                st.setScene(pausescene);
                st.show();
            }
        });

        //Keyboard Controls
        scene.addEventFilter(KeyEvent.KEY_PRESSED, key -> {
            //Jump
            if (key.getCode() == KeyCode.SPACE && gameMode==1 && Drag==0) {
                p.moveY(-10);
                move = 10;
            }

            //Restart
            else if (key.getCode() == KeyCode.N && gameMode==1) {
                gameMode = 1;
                newGame();
                p.setPos(200, 400);
            }

            //Save
            else if(key.getCode() == KeyCode.S && gameMode==1)
            {
                Save(filename);
            }

            //Load
            else if(key.getCode() == KeyCode.L && gameMode==1)
            {
                Load(filename);
            }

            if(Drag==1) {
                if (key.getCode() == KeyCode.W && gameMode == 1) {
                    p.drag(5);
                    moveFrame(-2);
                }
                if (key.getCode() == KeyCode.A && gameMode == 1) {
                    p.moveX(-5);
                } else if (key.getCode() == KeyCode.D && gameMode == 1) {
                    p.moveX(5);
                }
            }

        });

        st.setTitle("Game");
        st.setScene(scene);
        st.show();
    }


    public static void tick(GraphicsContext gc)
    {
        tickcount++;

        if(gameMode==0)
        {
            Save(filename);
        }
        if(gameMode==1) {
            if (p.getyPos() > 700) {
                gameMode = 0;
            }

            if(p.getStars()>=15) {
                HardMode=1;

            }

            for(int i=0;i<5;i++)
            {
                obstacles[i].animate();
            }

            if(Drag==0) {
                p.gravity();
                if (p.getyPos() <= 500 && move > 0) {
                    moveFrame(-2);
                    move -= 1;
                }
                if (p.getyPos() <= 200) {
                    moveFrame(-5);
                }
            }
            else
            {
                p.drag(-5);
                if (p.getyPos() <= 100) {
                    p.drag(-10);
                }
            }



            for(int i=0;i<5;i++) {
                if (s[i].checkPos(p.getyPos())) {
                    p.addStar();
                    newStar(i);
                }

                if (c[i].checkPos(p.getyPos())) {
                    p.changeColor(rand.nextInt(4));
                    newColorChange(i);
                }

                if (obstacles[i].getyPos() > 1550) {
                    newObs(i);
                }
            }

            for(int i=0;i<5;i++)
            {
                if (obstacles[i].checkX()) {
                    if (obstacles[i].checkY(p.getyPos())) {
                        if (obstacles[i].checkColor(p.getColor())) {
                            //gameMode = 0;
                        }
                    }
                }
            }
            //Background
            if(bgType==0)
                gc.drawImage(bgnorm, 0, 0, 400, 700);
            else if(bgType==1)
                gc.drawImage(bglight, 0, 0, 400, 700);
            else if(bgType==2)
                gc.drawImage(bghalo, 0, 0, 400, 700);
            else
                gc.drawImage(bgretro, 0, 0, 400, 700);

            //Pause
            gc.drawImage(pause, 350, 10, 40, 40);


            //Star Icon
            gc.drawImage(star, 10, 20, 40, 40);

            p.draw(gc);

            for(int i=0;i<5;i++)
            {
                obstacles[i].draw(gc);
                s[i].draw(gc);
                c[i].draw(gc);
            }

            //Score
            if(bgType!=1)
                gc.setFill(Color.WHITE);
            else
                gc.setFill(Color.ORANGE);
            gc.setFont(new Font("Avenir Next", 45));
            gc.fillText("" + (p.getStars()), 60, 60);

        }

    }

    public static void makeHard() {
        for(int i=0;i<5;i++)
        {
            obstacles[i].makeHard();
        }
    }

    public void makeDrag() {
        Drag=1;
    }

    public void makeNormal() {
        Drag=0;
    }

    public void makeHarder() {
        if(HardMode==0) {
            HardMode = 1;
            makeHard();
        }
    }

    public void makeEasier() {
        if(HardMode==1) {
            HardMode = 0;
            makeHard();
        }
    }

    public int getScore() {
        return p.getStars();
    }

    public int getHard() {
        return HardMode;
    }

    public int getDrag() {
        return Drag;
    }

    public static void newStar(int i)
    {
        s[i].setPos(200, -10000);
    }

    public static void newObs(int i)
    {
        if(i==4) {
            obstacles[4].setPos(150, -100);
            s[4].setPos(200, -100);
            c[4].setPos(200, -250);
        }
        else if(i==1) {
            obstacles[1].setPos(200, -100);
            s[1].setPos(200, -150);
            c[1].setPos(200, -200);
        }
        else{
            obstacles[i].setPos(200, -100);
            s[i].setPos(200, -100);
            c[i].setPos(200, -250);
        }
    }

    public static void newColorChange(int i)
    {
        c[i].setPos(200, -20000);
    }

    public static void newGame()
    {
        HardMode=0;
        int num = p.getStars();
        p = new Player(200, 500);
        for(int i=0;i<num;i++)
            p.addStar();
        p.moveY(5);
        obstacles = new Obstacle[]{new SquareObstacles(200, 200, 1), new LineCircleObstacle(200, -100, 1), new MajorCircleObstacle(200, -400, 1), new DiskObstacle(200, -750, 1), new WindmillObstacles(150, -1100, 1, 1)};
        s = new Star[]{new Star(200, 200), new Star(200, -150), new Star(200, -400), new Star(200, -750), new Star(200, -1100)};
        c = new ColorChanger[]{new ColorChanger(200, 50), new ColorChanger(200, -250), new ColorChanger(200, -550), new ColorChanger(200, -900), new ColorChanger(200, -1250)};

    }

    public static void moveFrame(int move) {
        for (int i = 0; i<5; i++) {
            c[i].moveY(-move);
            s[i].moveY(-move);
            obstacles[i].moveY(-move);
        }
    }
}
