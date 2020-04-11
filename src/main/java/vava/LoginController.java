package vava;

import managers.UserManager;
import models.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;


public class LoginController {

    public static UserManager um = new UserManager();
    public static String name;
    public static String password;

    public PasswordField loginTFPassword;
    public TextField loginTFUserName;
    public Button loginBLogIn;

    public void loginBLogIn(ActionEvent actionEvent) throws SQLException {
        name = loginTFUserName.getText();
        password = loginTFPassword.getText();

        User user = um.select(name, password);

    }
}
