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
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

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
    public Button activUserB;
    public Button addItemB;
    public Button showItemB;
    public Button editItemB;
    public Button removeItemButt;
    public Pane bottomButtonPanel;

    private CollectionManager collectionManager = App.getCollectionManager();
    private SceneManager sceneManager = App.getSceneManager();
    private ItemManager itemManager = App.getItemManager();
    private String lang = App.getLanguage();
    private ResourceBundle resourceBundle = ResourceBundle.getBundle(lang);

    // inicializacia textu a jazyka
    public void initialize() throws SQLException {
        settingsB.setText(resourceBundle.getString("MainController.settingsB"));
        myCollectionsB.setText(resourceBundle.getString("MainController.myCollectionsB"));
        newCollectionB.setText(resourceBundle.getString("MainController.newCollectionB"));
        removeCollectionB.setText(resourceBundle.getString("MainController.removeCollectionB"));
        changeCollectionB.setText(resourceBundle.getString("MainController.changeCollectionB"));
        activUserB.setText(App.getActivUser().getUserName());

        addItemB.setText(resourceBundle.getString("MainController.addItemB"));
        showItemB.setText(resourceBundle.getString("MainController.showItemB"));
        editItemB.setText(resourceBundle.getString("MainController.editItemB"));
        removeItemButt.setText(resourceBundle.getString("MainController.removeItemButt"));

        bottomButtonPanel.setVisible(false);
        removeCollectionB.setVisible(false);
        changeCollectionB.setVisible(false);
        getAllCollections();
    }

    // ziskat vsetky kolekcie pouzivatela
    public void getAllCollections() throws SQLException {
        bottomButtonPanel.setVisible(false);
        removeCollectionB.setVisible(false);
        changeCollectionB.setVisible(false);
        mainContent.setCenter(collContainer);
        newCollectionB.getStyleClass().clear();
        newCollectionB.getStyleClass().add("colButton");

        User user = App.getActivUser();
        collectionNameLabel.setText(resourceBundle.getString("MainController.myCollectionsB"));
        collectionNameLabel.setTextFill(Paint.valueOf("#788CDE"));
        collectionNameLabel.setVisible(true);
        myCollectionsB.getStyleClass().add("activeBcoll");
        ArrayList<Collection> collections = collectionManager.getAllCollections(user.getId());
        sceneManager.addCollectionButtons(this, collContainer, collections);
    }

    // ziskat kolekciu
    public void getCollection(Collection collection) throws SQLException {
        App.setCollection(collection);

        collectionNameLabel.setVisible(true);
        collectionNameLabel.setText(collection.getName());

        ArrayList<Item> items = itemManager.getAllItems(collection.getId());

        boolean status = sceneManager.showCollection(items, collContainer);

        if(status) {
            bottomButtonPanel.setVisible(true);
        }
        removeCollectionB.setVisible(true);
        changeCollectionB.setVisible(true);
    }

    // prepnut sa na vytvorenie kolekciu
    public void newCollectionWindow(ActionEvent actionEvent) {
        removeCollectionB.setVisible(false);
        changeCollectionB.setVisible(false);

        myCollectionsB.getStyleClass().clear();
        myCollectionsB.getStyleClass().add("colButton");

        collectionNameLabel.setText(resourceBundle.getString("CollectionController.addCollectionL"));
        collectionNameLabel.setTextFill(Paint.valueOf("#4AA079"));
        collectionNameLabel.setVisible(true);
        newCollectionB.getStyleClass().add("activeBnewColl");
        sceneManager.setContent("createCollection.fxml", mainContent);
    }

    // prepnut sa na nastavenia
    public void settings(ActionEvent actionEvent) {
        sceneManager.changeScene("settings.fxml", actionEvent);
    }

    // odstranit kolekciu a navrat na domovsku stranku
    public void removeCollection(ActionEvent actionEvent) {
        collectionManager.removeCollection();
        sceneManager.changeScene("Home.fxml", actionEvent);
    }

    // prepnut sa na stranku pridat prvok do kolekcie
    public void addItem(ActionEvent actionEvent) {
        sceneManager.changeScene("createItem.fxml", actionEvent);
    }

    // prepnut sa na stranku zobrazit prvok z kolekcie
    public void showItem(ActionEvent actionEvent) {
        sceneManager.changeScene("itemDetail.fxml", actionEvent);
    }

    // prepnut sa na stranku upravit prvok z kolekcie
    public void editItem(ActionEvent actionEvent) {
        sceneManager.changeScene("updateItem.fxml", actionEvent);
    }

    // prepnut sa na stranku vymazat prvok z kolekcie
    public void removeItem(ActionEvent actionEvent) throws SQLException {
        itemManager.removeItem(App.getActItem().getId());
        sceneManager.showDialog(resourceBundle.getString("ItemController.info3"));
        getCollection(App.getCollection());
    }

    // prepnut sa na stranku upravit kolekciu
    public void editCollection(ActionEvent actionEvent) {
        sceneManager.changeScene("updateCollection.fxml", actionEvent);
    }
}
