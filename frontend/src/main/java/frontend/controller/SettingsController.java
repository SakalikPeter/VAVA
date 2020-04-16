package frontend.controller;

import frontend.App;
import frontend.manager.SceneManager;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class SettingsController {
    public Button backB;

    private SceneManager sceneManager = App.getSceneManager();

    public void backHome(ActionEvent actionEvent) {
        sceneManager.changeScene("Home.fxml", actionEvent);
    }
}
