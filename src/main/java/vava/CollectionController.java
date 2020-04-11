package vava;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public Button click;
    public VBox sideMenu;
    public Label collectionNameLabel;
    public ArrayList<Button> buttons;
    public Button settingsB;
    public BorderPane mainContent;

    private CollectionManager collectionManager = Main.getCollectionManager();
    private SceneManager sceneManager = Main.getSceneManager();

    public void getAllCollections() throws SQLException {
        User user = Main.getUser();
        ArrayList<Collection> collections = collectionManager.select(user.getId());
        buttons = sceneManager.addCollectionButtons(this, sideMenu, collections);

    }

    public void getCollection(String collectionName) {
        collectionNameLabel.setVisible(true);
        collectionNameLabel.setText(collectionName);
    }

    //TODO toto by mal asi mat na starosti sceneManager
    public void newCollectionWindow() {
        collectionNameLabel.setVisible(true);
        collectionNameLabel.setText("nová kolekcia");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/createCollection.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainContent.setCenter(root);
    }

    //TODO toto by mal asi mat na starosti sceneManager
    public void settings(ActionEvent actionEvent) {
        collectionNameLabel.setVisible(true);
        collectionNameLabel.setText("nastavenia");
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/settings.fxml"));
        } catch (IOException e) {
                e.printStackTrace();
        }
        mainContent.setCenter(root);
    }
}
