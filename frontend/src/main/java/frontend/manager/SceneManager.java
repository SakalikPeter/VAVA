package frontend.manager;

import frontend.controller.MainController;
import frontend.model.Collection;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
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

    public void setContent(String name, BorderPane mainContent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/" + name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        mainContent.setCenter(root);
    }

    public ArrayList<Button> addCollectionButtons(MainController controller, VBox container, ArrayList<Collection> collections) {
        ArrayList<Button> buttons = new ArrayList<>();
        for(Collection collection : collections) {
            Button button = new Button(collection.getName());
            button.setPrefWidth(460);
            button.setPrefHeight(45);
            button.getStylesheets().add("style.css");
            button.getStyleClass().add("colButton");
            VBox.setMargin(button, new Insets(5, 0,0,0));
            button.setOnAction(click -> {
                try {
                    controller.getCollection(collection);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
            buttons.add(button);
        }

        container.getChildren().clear();
        container.getChildren().addAll(buttons);
        return buttons;
    }
}
