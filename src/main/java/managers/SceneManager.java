package managers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Collection;
import vava.CollectionController;

import java.io.IOException;
import java.util.ArrayList;

public class SceneManager {

    public void changeScene(String name, ActionEvent actionEvent){
        Parent root;
        String path = "/" + name;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage) ((Node)(actionEvent.getSource())).getScene().getWindow();
            stage.setTitle("Collection tracker");
            stage.setScene(new Scene(root, 1100, 800));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Button> addCollectionButtons(CollectionController collectionController, VBox container, ArrayList<Collection> collections) {
        ArrayList<Button> buttons = new ArrayList<>();
        for(Collection collection : collections) {
            Button button = new Button(collection.getName());
            button.setPrefWidth(360);
            button.setPrefHeight(45);
            button.getStylesheets().add("style.css");
            button.getStyleClass().add("colButton");
            container.setMargin(button, new Insets(5, 0,0,0));
            button.setOnAction(click -> {
                collectionController.getCollection(collection.getName());
            });
            buttons.add(button);
        }

        container.getChildren().clear();
        container.getChildren().addAll(buttons);
        return buttons;
    }
}
