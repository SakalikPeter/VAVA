package vava;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import managers.CollectionManager;
import managers.SceneManager;
import managers.UserManager;
import models.Collection;
import models.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class LoginController {
    private static UserManager userManager = Main.getUserManager();
    private CollectionManager collectionManager = Main.getCollectionManager();

    public PasswordField loginTFPassword;
    public TextField loginTFUserName;
    public Button loginBLogIn;
    public AnchorPane mainPane;

    public void loginBLogIn(ActionEvent actionEvent) throws SQLException {
        String name = loginTFUserName.getText();
        String password = loginTFPassword.getText();

        User user = userManager.select(name, password);
        if(user == null) {
            System.out.println("nespravne meno alebo heslo!");
        }
        else {
            SceneManager sceneManager = Main.getSceneManager();
            sceneManager.changeScene("home.fxml", actionEvent);

            Main.setUser(user);
        }

    }
}
