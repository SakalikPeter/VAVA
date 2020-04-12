package vava;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import managers.SceneManager;

public class SettingsController {

    public Button backB;
    private SceneManager sceneManager = Main.getSceneManager();

    public void backHome(ActionEvent actionEvent) {
        sceneManager.changeScene("home.fxml", actionEvent);
    }
}
