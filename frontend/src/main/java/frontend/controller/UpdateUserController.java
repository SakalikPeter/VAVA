package frontend.controller;

import frontend.App;
import frontend.manager.SceneManager;
import frontend.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.ResourceBundle;

public class UpdateUserController {
    public Button backB;
    public Label updateUserL;
    public Label settUserL;
    public Button updateB;
    public TextField nameTF;
    public Label updateNewPassL;
    public PasswordField newPasswordTF;

    private SceneManager sceneManager = App.getSceneManager();
    private User user = App.getActivUser();


    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(App.getLanguage());
        nameTF.setText(user.getUserName());
        updateUserL.setText(resourceBundle.getString("UpdateUser.updateUserL"));
        settUserL.setText(resourceBundle.getString("UpdateUser.settUserL"));
        updateB.setText(resourceBundle.getString("UpdateUser.updateB"));
        updateNewPassL.setText(resourceBundle.getString("UpdateUser.updateNewPassL"));
    }

    public void backHome(ActionEvent actionEvent) {
        sceneManager.changeScene("settings.fxml", actionEvent);
    }

    public void update(ActionEvent actionEvent) {
        String name = nameTF.getText();
        String newPassword = newPasswordTF.getText();

        App.getUserManager().updateUser(name, newPassword);

        ResourceBundle resourceBundle = ResourceBundle.getBundle(App.getLanguage());
        sceneManager.showDialog(resourceBundle.getString("UpdateUser.info"));
        sceneManager.changeScene("settings.fxml", actionEvent);
    }
}
