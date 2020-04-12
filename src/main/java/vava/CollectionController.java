package vava;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import managers.CollectionManager;
import managers.ItemManager;
import managers.SceneManager;
import models.Collection;
import models.Item;
import models.User;

import java.io.IOException;
import java.sql.ResultSet;
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
    public Button backB;

    private CollectionManager collectionManager = Main.getCollectionManager();
    private SceneManager sceneManager = Main.getSceneManager();
    private ItemManager itemManager = Main.getItemManager();


    public void getAllCollections() throws SQLException {
        User user = Main.getUser();
        collectionNameLabel.setText("moje kolekcie");
        collectionNameLabel.setVisible(true);
        ArrayList<Collection> collections = collectionManager.select(user.getId());
        buttons = sceneManager.addCollectionButtons(this, collContainer, collections);
    }

    public void getCollection(Collection collection) throws SQLException {
        collectionNameLabel.setVisible(true);
        collectionNameLabel.setText(collection.getName());

        ArrayList<Item> items = itemManager.select(collection);
        sceneManager.showCollection(items, collContainer);
    }

    public void newCollectionWindow(ActionEvent actionEvent) {
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

    public void backHome(ActionEvent actionEvent) {
        sceneManager.changeScene("home.fxml", actionEvent);
    }
}
