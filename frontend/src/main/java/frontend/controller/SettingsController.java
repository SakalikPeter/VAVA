package frontend.controller;

import frontend.App;
import frontend.manager.CollectionManager;
import frontend.manager.SceneManager;
import frontend.manager.UserManager;
import frontend.model.Collection;
import frontend.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

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
    private ResourceBundle resourceBundle = ResourceBundle.getBundle(lang);

    // inicializacia textu a jazyka
    public void initialize() {

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

    // navrat na domovsku stranku
    public void backHome(ActionEvent actionEvent) {
        sceneManager.changeScene("Home.fxml", actionEvent);
    }

    // vymazat profil
    public void deleteProfile(ActionEvent actionEvent) {
        collectionManager.removeAllCollections(user);
        userManager.removeUser(user);

        sceneManager.showDialog(resourceBundle.getString("SettingsController.info"));
        sceneManager.changeScene("login.fxml", actionEvent);
    }

    // prepnut na stranku upravit profil
    public void editProfile(ActionEvent actionEvent) {
        sceneManager.changeScene("updateUser.fxml", actionEvent);
    }

    // odhlasit z aplikacie
    public void signOff(ActionEvent actionEvent) {
        App.setLanguage("SK");
        App.setActItem(null);
        App.setActivUser(null);
        App.setCollection(null);

        sceneManager.changeScene("login.fxml", actionEvent);
    }

    // nastavit jazyk
    public void changeLanguage(ActionEvent actionEvent) {
        String language = languagePicker.getValue();

        if(language.equals("slovenčina"))
            App.setLanguage("SK");
        else
            App.setLanguage("EN");

        sceneManager.changeScene("settings.fxml", actionEvent);
    }
}
