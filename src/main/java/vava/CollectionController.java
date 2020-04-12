package vava;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import managers.CollectionManager;
import managers.SceneManager;
import models.Collection;
import models.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CollectionController {
    public VBox sideMenu;
    public Label collectionNameLabel;
    public ArrayList<Button> buttons;
    public Button settingsB;
    public BorderPane mainContent;
    public TextField newCollectionTF;
    public Button createCollectionB;
    public Button myCollectionsB;
    public Button newCollectionB;
    public VBox collContainer;

    private CollectionManager collectionManager = Main.getCollectionManager();
    private SceneManager sceneManager = Main.getSceneManager();


    public void getAllCollections() throws SQLException {
        User user = Main.getUser();
        collectionNameLabel.setText("moje kolekcie");
        collectionNameLabel.setVisible(true);
        ArrayList<Collection> collections = collectionManager.select(user.getId());
        buttons = sceneManager.addCollectionButtons(this, collContainer, collections);
    }

    public void getCollection(String collectionName) {
        collectionNameLabel.setVisible(true);
        collectionNameLabel.setText(collectionName);

    }

    public void newCollectionWindow(ActionEvent actionEvent) {
//        collectionNameLabel.setVisible(true);
//        collectionNameLabel.setText("nová kolekcia");
//        Parent root = null;
//        try {
//            root = FXMLLoader.load(getClass().getResource("/createCollection.fxml"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        mainContent.setCenter(root);

        sceneManager.changeScene("createCollection.fxml", actionEvent);
    }

    public void settings(ActionEvent actionEvent) {
        sceneManager.changeScene("settings.fxml", actionEvent);
    }

    public void createCollection(ActionEvent actionEvent) throws SQLException {
        User user = Main.getUser();
        String collectionName = newCollectionTF.getText();
        Collection collection = new Collection(user.getId(), collectionName, null, 0);
        collectionManager.insert(collection);
    }
}
