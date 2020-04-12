package managers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Collection;
import models.Item;
import vava.CollectionController;

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
                try {
                    collectionController.getCollection(collection);
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

    public void showCollection(ArrayList<Item> items, VBox container) {
        TableView<Item> table = new TableView<Item>();
        TableColumn<Item, String> column = new TableColumn<>();
        table.getColumns().add(column);

        column.setCellValueFactory(new PropertyValueFactory<>("brand"));    //toto je docasne bude tam nazov predmetu + mozno dalsie informacie o predmete
        for (Item i : items) {
            table.getItems().add(i);
        }

        container.getChildren().clear();
        container.getChildren().add(table);
    }
}
