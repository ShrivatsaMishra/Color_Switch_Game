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
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

public class PauseController implements Initializable {

    static GameMenu gm = new GameMenu();

    public Button resumebutton;
    public Button startagainbutton;
    public Button mainmenu;
    public AnchorPane anchorRootPause;
    public StackPane parentContainerPause;

    public void closeButtonAction(ActionEvent actionEvent) {
        Stage exitstage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        exitstage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void loadMenu(ActionEvent actionLoad) throws IOException {
        Parent loadParent = FXMLLoader.load(getClass().getResource("/sample/loadscreen.fxml"));
        Scene loadScene = anchorRootPause.getScene();

        loadParent.translateYProperty().set(loadScene.getHeight());

        parentContainerPause.getChildren().add(loadParent);

        Timeline timeline = new Timeline();

        KeyValue kv = new KeyValue(loadParent.translateYProperty(),0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1),kv);
        timeline.getKeyFrames().add(kf);

        timeline.setOnFinished(t -> {
            parentContainerPause.getChildren().remove(anchorRootPause);
        });
        timeline.play();
    }


    public void resume(ActionEvent actionEvent) {
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        gm.Resume(window);
    }


    public void returnMenu(ActionEvent actionEvent) throws IOException {
        Parent settingParent = FXMLLoader.load(getClass().getResource("/sample/mainmenu.fxml"));
        Scene settingScene = new Scene(settingParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(settingScene);
        window.show();
    }

    public void startAgain(ActionEvent actionEvent) {
        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        gm.ReStartGame(window);
    }
}
