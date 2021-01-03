package sample;

import javafx.animation.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.*;
import java.net.URI;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public Button settings;
    public javafx.scene.image.ImageView image1;
    public javafx.scene.image.ImageView image2;
    public javafx.scene.image.ImageView image3;
    public Button playbutton;
    public Button closeButton;
    public Button startgamebutton;
    public ImageView playerIcon;
    public ImageView musicImage;
    public ImageView anmImage;

    public AnchorPane anchorRoot;
    public StackPane parentContainer;

    public AnchorPane anchorRootSet;
    public StackPane parentContainerSet;

    int animationplaying=0;
    static int MusicPlaying=0;
    RotateTransition rt1;
    RotateTransition rt2;
    RotateTransition rt3;
    static String musicpath = "src/Music_Assets/Amadeus_Legendary.mp3";
    static File musicFile = new File(musicpath);
    static URI musicURI = musicFile.toURI();
    static Media media = new Media(musicURI.toString());
    static MediaPlayer mediaPlayer = new MediaPlayer(media);

    static GameMenu menu = new GameMenu();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(MusicPlaying==0) {
            MusicPlaying = 1;
            mediaPlayer.setAutoPlay(true);
        }
        animationplaying = 1;
        rt1 = setRotate(image1, true, 360, 30);
        rt2 = setRotate(image2, true, 270, 25);
        rt3 = setRotate(image3, true, 180, 20);
        changeIcon();
    }

    public void openSettings(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent settingParent = FXMLLoader.load(getClass().getResource("/sample/settings.fxml"));
        Scene settingScene = anchorRoot.getScene();

        settingParent.translateYProperty().set(settingScene.getHeight());

        parentContainer.getChildren().add(settingParent);

        Timeline timeline = new Timeline();

        KeyValue kv = new KeyValue(settingParent.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1),kv);
        timeline.getKeyFrames().add(kf);

        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();

    }


    public void returnMenu(MouseEvent mouseEvent) throws IOException {
        Parent menuParent = FXMLLoader.load(getClass().getResource("/sample/mainmenu.fxml"));
        Scene menuScene = anchorRootSet.getScene();

        menuParent.translateYProperty().set(menuScene.getHeight());

        parentContainerSet.getChildren().add(menuParent);

        Timeline timeline = new Timeline();

        KeyValue kv = new KeyValue(menuParent.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1),kv);
        timeline.getKeyFrames().add(kf);

        timeline.setOnFinished(t -> {
            parentContainerSet.getChildren().remove(anchorRootSet);
        });
        timeline.play();
    }

    public void play(ActionEvent actionEvent) {
        if(animationplaying==0) {
            rt1 = setRotate(image1, true, 360, 30);
            rt2 = setRotate(image2, true, 270, 25);
            rt3 = setRotate(image3, true, 180, 20);
            anmImage.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/playanm.png"));
            animationplaying=1;
        }
        else {
            rt1.stop();
            rt2.stop();
            rt3.stop();
            anmImage.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/noPlay.png"));
            animationplaying=0;
        }

    }

    public void music(ActionEvent actionEvent) {
        if(MusicPlaying==0)
        {
            mediaPlayer.play();
            MusicPlaying=1;
            musicImage.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/music.png"));
        }
        else {
            mediaPlayer.pause();
            MusicPlaying=0;
            musicImage.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/noMusic.png"));
        }
    }

    public RotateTransition setRotate(ImageView image, boolean reverse, int angle, int duration){

        RotateTransition rt = new RotateTransition(Duration.seconds(duration), image);

        rt.setAutoReverse(reverse);
        rt.setByAngle(angle);
        rt.setDelay(Duration.seconds(0));
        rt.setRate(12);
        rt.setCycleCount(18);
        rt.play();
        return rt;

    }

    public void dragMode(ActionEvent actionEvent) {
        menu.makeDrag();
    }

    public void playerTypeLeft()
    {
        menu.playerTypeChange(-1);
        this.changeIcon();
    }

    public void playerTypeRight()
    {
        menu.playerTypeChange(1);
        this.changeIcon();
    }

    private void changeIcon() {
        int ch = menu.getPlayerType();
        if(ch==0)
        {
            playerIcon.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/Circle.png"));
        }
        else if(ch==1)
        {
            playerIcon.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/RoundRect.png"));
        }
        else if(ch==2)
        {
            playerIcon.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/Rect.png"));
        }
        else if(ch==3)
        {
            playerIcon.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/Triangle.png"));
        }
        else if(ch==4)
        {
            playerIcon.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/Home.png"));
        }
        else if(ch==5)
        {
            playerIcon.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/Punch.png"));
        }
        else if(ch==6)
        {
            playerIcon.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/Crown.png"));
        }
    }

    @FXML
    public void closeButtonAction(ActionEvent closeevent){
        Stage exitstage = (Stage)((Node)closeevent.getSource()).getScene().getWindow();
        exitstage.close();
    }


    public void loadMenu(ActionEvent actionLoad) throws IOException {
        Parent loadParent = FXMLLoader.load(getClass().getResource("/sample/loadscreen.fxml"));
        Scene loadScene = anchorRoot.getScene();

        loadParent.translateYProperty().set(loadScene.getHeight());

        parentContainer.getChildren().add(loadParent);

        Timeline timeline = new Timeline();

        KeyValue kv = new KeyValue(loadParent.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1),kv);
        timeline.getKeyFrames().add(kf);

        timeline.setOnFinished(t -> {
            parentContainer.getChildren().remove(anchorRoot);
        });
        timeline.play();
    }

    public void StartGame(ActionEvent actionLoad) throws IOException {
        Stage window = (Stage)((Node)actionLoad.getSource()).getScene().getWindow();
        menu.StartGame(window);
    }
}
