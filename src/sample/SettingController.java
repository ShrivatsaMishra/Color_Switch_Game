package sample;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import sample.GameMenu;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SettingController implements Initializable {

    static GameMenu menu = new GameMenu();

    @FXML
    public AnchorPane anchorRootSet;
    public StackPane parentContainerSet;
    public ImageView dragImg;
    public ImageView hardImg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void dragMode(ActionEvent actionEvent) {
        int ch = menu.getDrag();
        if(ch==0) {
            menu.makeDrag();
            dragImg.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/drag.png"));
        }
        else
        {
            menu.makeNormal();
            dragImg.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/noDrag.png"));
        }
    }

    public void makeHard(ActionEvent actionEvent) {
        int ch = menu.getHard();
        if(ch==0) {
            menu.makeHarder();
            hardImg.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/hard.png"));
        }
        else
        {
            menu.makeEasier();
            hardImg.setImage(new Image("file:///C:/Users/shriv/IdeaProjects/Color_Switch/src/sample/Icons/noHard.png"));
        }
    }

    public void theme0(javafx.event.ActionEvent actionEvent) throws IOException {
        menu.bgChange(0);
    }

    public void theme1(javafx.event.ActionEvent actionEvent) throws IOException {
        menu.bgChange(1);
    }

    public void theme2(javafx.event.ActionEvent actionEvent) throws IOException {
        menu.bgChange(2);
    }

    public void theme3(javafx.event.ActionEvent actionEvent) throws IOException {
        menu.bgChange(3);
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
}
