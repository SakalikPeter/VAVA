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

    public void getCollection(Collection collection) throws SQLException {
        App.setCollection(collection);

        collectionNameLabel.setVisible(true);
        collectionNameLabel.setText(collection.getName());

        ArrayList<Item> items = itemManager.getAllItems(collection.getId());

        sceneManager.showCollection(items, collContainer);
        removeCollectionB.setVisible(true);
        changeCollectionB.setVisible(true);
        bottomButtonPanel.setVisible(true);
    }

    public void newCollectionWindow(ActionEvent actionEvent) {
        myCollectionsB.getStyleClass().clear();
        myCollectionsB.getStyleClass().add("colButton");

        collectionNameLabel.setText(resourceBundle.getString("CollectionController.addCollectionL"));
        collectionNameLabel.setTextFill(Paint.valueOf("#4AA079"));
        collectionNameLabel.setVisible(true);
        newCollectionB.getStyleClass().add("activeBnewColl");
        sceneManager.setContent("createCollection.fxml", mainContent);
    }

    public void settings(ActionEvent actionEvent) {
        sceneManager.changeScene("settings.fxml", actionEvent);
    }

    public void removeCollection(ActionEvent actionEvent) {
        collectionManager.removeCollection();

    }

    public void addItem(ActionEvent actionEvent) {
        sceneManager.changeScene("createItem.fxml", actionEvent);
    }

    public void showItem(ActionEvent actionEvent) {
        sceneManager.changeScene("itemDetail.fxml", actionEvent);
    }

    public void editItem(ActionEvent actionEvent) {
        sceneManager.changeScene("updateItem.fxml", actionEvent);
    }

    public void removeItem(ActionEvent actionEvent) {
        itemManager.removeItem(App.getActItem().getId());
    }
}
