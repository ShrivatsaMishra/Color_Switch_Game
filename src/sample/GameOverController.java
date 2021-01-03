package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class GameOverController implements Initializable {

    static GameMenu gm = new GameMenu();
    @FXML
    Text Score;
    @FXML
    Button revivebutton;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Score.setText(gm.getScore()+"");

    }

    public void revive(ActionEvent actionEvent) throws IOException {

        if(gm.getScore()>=10) {
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            gm.Revive(window);
        }
        else
        {
            revivebutton.setText("Not Possible");
            revivebutton.setPadding(new Insets(8, 21, 8, 21));
        }
    }

    public void restart(ActionEvent actionEvent) throws IOException {

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
        gm.ReStartGame(window);
    }


    public void returnMenu(ActionEvent actionEvent) throws IOException {
        Parent settingParent = FXMLLoader.load(getClass().getResource("/sample/mainmenu.fxml"));
        Scene settingScene = new Scene(settingParent);

        Stage window = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(settingScene);
        window.show();
    }
}
