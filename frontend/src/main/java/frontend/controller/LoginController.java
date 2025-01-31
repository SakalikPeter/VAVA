package frontend.controller;

import frontend.App;
import frontend.manager.CollectionManager;
import frontend.manager.SceneManager;
import frontend.manager.UserManager;
import frontend.model.Collection;
import frontend.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController {
    private static UserManager userManager = App.getUserManager();
    public Button newProfileB;
    private CollectionManager collectionManager = App.getCollectionManager();

    Logger logger = LoggerFactory.getLogger(LoginController.class);

    public PasswordField loginTFPassword;
    public TextField loginTFUserName;
    public Button loginBLogIn;
    public AnchorPane mainPane;

    // prihlasenie pouzivatela
    public void loginBLogIn(ActionEvent actionEvent) {
        String name = loginTFUserName.getText();
        String password = loginTFPassword.getText();

        User user = userManager.getUser(name, password);
        //skontrolovanie pouzivatela
        if(user == null) {
            logger.info("Incorrect user name or password");
        }
        else {
            SceneManager sceneManager = App.getSceneManager();
            App.setActivUser(user);
            sceneManager.changeScene("Home.fxml", actionEvent);
        }

    }

    public void createProfile(ActionEvent actionEvent) {
        String name = loginTFUserName.getText();
        String password = loginTFPassword.getText();
        User user = new User();

        user.setUserName(name);
        user.setPassword(password);

        userManager.createUser(user);
        App.getSceneManager().showDialog("Používateľ úspešne vytvorený.");
    }
}
