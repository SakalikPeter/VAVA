package frontend.controller;

import frontend.App;
import frontend.model.Collection;
import frontend.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.ResourceBundle;

public class CollectionController {
    public Button createCollectionB;
    public TextField newCollectionTF;
    public Label collectionNameL;

    private String lang = App.getLanguage();

    // inicializacia textu a jazyka
    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(lang);

        createCollectionB.setText(resourceBundle.getString("CollectionController.createCollectionB"));
        collectionNameL.setText(resourceBundle.getString("CollectionController.collectionNameL"));
    }

    // vytvorit kolekciu
    public void createCollection(ActionEvent actionEvent) {
        User user = App.getActivUser();
        String collectionName = newCollectionTF.getText();
        Collection collection = new Collection(user.getId(), collectionName, null, 0);
        App.getCollectionManager().createCollection(collection);
        App.getSceneManager().changeScene("Home.fxml", actionEvent);
    }
}
