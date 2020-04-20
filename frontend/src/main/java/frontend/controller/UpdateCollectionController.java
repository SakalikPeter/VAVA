package frontend.controller;

import frontend.App;
import frontend.manager.SceneManager;
import frontend.model.Collection;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ResourceBundle;

public class UpdateCollectionController {
    public Button backB;
    public Label updateCollectionL;
    public Label settUserL;
    public Button updateB;
    public TextField nameTF;

    private Collection collection = App.getCollection();
    private SceneManager sceneManager = App.getSceneManager();

    // inicializacia textu a jazyka
    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(App.getLanguage());
        nameTF.setText(collection.getName());
        updateCollectionL.setText(resourceBundle.getString("UpdateCollection.updateCollectionL"));
        settUserL.setText(resourceBundle.getString("UpdateUser.settUserL"));
        updateB.setText(resourceBundle.getString("UpdateUser.updateB"));
    }

    // navrat na domovsku stranku
    public void backHome(ActionEvent actionEvent) {
        sceneManager.changeScene("Home.fxml", actionEvent);
    }

    // vykonat upravy
    public void update(ActionEvent actionEvent) {
        String name = nameTF.getText();
        App.getCollectionManager().updateCollection(name);
        sceneManager.changeScene("Home.fxml", actionEvent);
    }
}
