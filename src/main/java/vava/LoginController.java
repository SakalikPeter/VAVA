package vava;

import managers.SceneManager;
import managers.UserManager;
import models.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class LoginController {
    private static UserManager userManager = Main.getUserManager();

    public PasswordField loginTFPassword;
    public TextField loginTFUserName;
    public Button loginBLogIn;

    public void loginBLogIn(ActionEvent actionEvent) throws SQLException {
        String name = loginTFUserName.getText();
        String password = loginTFPassword.getText();

        User user = userManager.select(name, password);
        SceneManager sceneManager = Main.getSceneManager();
        sceneManager.change_scene("home.fxml", actionEvent);

        Main.setUser(user);
    }
}
