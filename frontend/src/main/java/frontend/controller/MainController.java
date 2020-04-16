package frontend.controller;

import frontend.App;
import frontend.manager.CollectionManager;
import frontend.manager.ItemManager;
import frontend.manager.SceneManager;
import frontend.model.Collection;
import frontend.model.Item;
import frontend.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.sql.SQLException;
import java.util.ArrayList;

public class MainController {
    public Button settingsB;
    public VBox sideMenu;
    public Button myCollectionsB;
    public Button newCollectionB;
    public BorderPane mainContent;
    public Label collectionNameLabel;
    public VBox collContainer;
    public AnchorPane main;
    public Button removeCollectionB;
    public Button changeCollectionB;

    private CollectionManager collectionManager = App.getCollectionManager();
    private SceneManager sceneManager = App.getSceneManager();
    private ItemManager itemManager = App.getItemManager();

    public void initialize() throws SQLException {
        removeCollectionB.setVisible(false);
        changeCollectionB.setVisible(false);
        getAllCollections();
    }

    public void getAllCollections() throws SQLException {
        removeCollectionB.setVisible(false);
        mainContent.setCenter(collContainer);
        newCollectionB.getStyleClass().clear();
        newCollectionB.getStyleClass().add("colButton");

        User user = App.getActivUser();
        collectionNameLabel.setText("moje kolekcie");
        collectionNameLabel.setTextFill(Paint.valueOf("#788CDE"));
        collectionNameLabel.setVisible(true);
        myCollectionsB.getStyleClass().add("activeBcoll");
        ArrayList<Collection> collections = collectionManager.getAllCollections(user.getId());
        sceneManager.addCollectionButtons(this, collContainer, collections);
    }

    public void getCollection(Collection collection) throws SQLException {
        App.setCollection(collection);

        collectionNameLabel.setVisible(true);
        collectionNameLabel.setText(collection.getName());

        ArrayList<Item> items = itemManager.getAllItems(collection.getId());

        sceneManager.showCollection(items, collContainer);
        removeCollectionB.setVisible(true);
    }

    public void newCollectionWindow(ActionEvent actionEvent) {
        myCollectionsB.getStyleClass().clear();
        myCollectionsB.getStyleClass().add("colButton");

        collectionNameLabel.setText("vytvoriť kolekciu");
        collectionNameLabel.setTextFill(Paint.valueOf("#4AA079"));
        collectionNameLabel.setVisible(true);
        newCollectionB.getStyleClass().add("activeBnewColl");
        sceneManager.setContent("createCollection.fxml", mainContent);
    }

    public void backHome(ActionEvent actionEvent) {
        sceneManager.changeScene("Home.fxml", actionEvent);
    }

    public void settings(ActionEvent actionEvent) {
        sceneManager.changeScene("settings.fxml", actionEvent);
    }

    public void removeCollection(ActionEvent actionEvent) {
        collectionManager.removeCollection();

    }
}
