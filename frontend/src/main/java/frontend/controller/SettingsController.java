package frontend.controller;

import frontend.App;
import frontend.manager.CollectionManager;
import frontend.manager.SceneManager;
import frontend.manager.UserManager;
import frontend.model.Collection;
import frontend.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.util.ResourceBundle;

public class SettingsController {
    public Button backB;
    public Label bigSettingsL;
    public Label settProfileL;
    public Label settUserL;
    public Label settLanguageL;
    public Button deleteProfileB;
    public Button changeProfileB;
    public Button signOffB;
    public Button changeLanguageB;
    public ChoiceBox<String> languagePicker;

    private SceneManager sceneManager = App.getSceneManager();
    private CollectionManager collectionManager = App.getCollectionManager();
    private UserManager userManager = App.getUserManager();
    private User user = App.getActivUser();
    private String lang = App.getLanguage();

    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(lang);
        languagePicker.getItems().add("slovenčina");
        languagePicker.getItems().add("english");

        if(lang.equals("SK"))
            languagePicker.setValue("slovenčina");
        else
            languagePicker.setValue("english");

        bigSettingsL.setText(resourceBundle.getString("SettingsController.bigSettingsL"));
        settProfileL.setText(resourceBundle.getString("SettingsController.settProfileL"));
        settUserL.setText(App.getActivUser().getUserName());
        settLanguageL.setText(resourceBundle.getString("SettingsController.settLanguageL"));
        deleteProfileB.setText(resourceBundle.getString("SettingsController.deleteProfileB"));
        changeProfileB.setText(resourceBundle.getString("SettingsController.changeProfileB"));
        signOffB.setText(resourceBundle.getString("SettingsController.signOffB"));
        changeLanguageB.setText(resourceBundle.getString("SettingsController.changeLanguageB"));
    }

    public void backHome(ActionEvent actionEvent) {
        sceneManager.changeScene("Home.fxml", actionEvent);
    }

    public void deleteProfile(ActionEvent actionEvent) {
        collectionManager.removeAllCollections(user);
        userManager.removeUser(user);

        sceneManager.changeScene("login.fxml", actionEvent);
    }

    public void editProfile(ActionEvent actionEvent) {
        sceneManager.changeScene("updateUser.fxml", actionEvent);
    }

    public void signOff(ActionEvent actionEvent) {
        App.setLanguage("SK");
        App.setActItem(null);
        App.setActivUser(null);
        App.setCollection(null);

        sceneManager.changeScene("login.fxml", actionEvent);
    }

    public void changeLanguage(ActionEvent actionEvent) {
        String language = languagePicker.getValue();

        if(language.equals("slovenčina"))
            App.setLanguage("SK");
        else
            App.setLanguage("EN");

        sceneManager.changeScene("settings.fxml", actionEvent);
    }
}
