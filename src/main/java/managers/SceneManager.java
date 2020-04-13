package managers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Collection;
import models.Item;
import vava.CollectionController;
import vava.Main;

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
            VBox.setMargin(button, new Insets(5, 0,0,0));
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
        if(items.size() > 0) {
            TableView<Item> table = new TableView<Item>();
            TableColumn<Item, String> column = new TableColumn<>();
            table.getColumns().add(column);

            column.setCellValueFactory(new PropertyValueFactory<>("name"));    //toto je docasne bude tam nazov predmetu + mozno dalsie informacie o predmete
            table.getItems().addAll(items);
            table.getStylesheets().add("style.css");

            container.getChildren().clear();
            container.getChildren().add(table);

            Button createItem = new Button("Pridaj");
            container.getChildren().add(createItem);
            createItem.setOnAction(actionEvent -> changeScene("createItem.fxml", actionEvent));

            table.setOnMouseClicked((MouseEvent event) -> {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    Main.setActItem(table.getSelectionModel().getSelectedItem());
                    System.out.println(Main.getActItem().getName());
                }
            });


            Button updateItem = new Button("Uprav");
            container.getChildren().add(updateItem);
            updateItem.setOnAction(actionEvent -> changeScene("updateItem.fxml", actionEvent));

            Button deleteItem = new Button("Odstran");
            container.getChildren().add(deleteItem);
            deleteItem.setOnAction(actionEvent -> {
                try {
                    Main.getItemManager().delete(Main.getActItem());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

        }
        else {
            Button button = new Button("pridať predmet");
            button.setPrefWidth(160);
            button.setPrefHeight(45);
            button.getStylesheets().add("style.css");
            button.getStyleClass().add("addItemB");
            button.setOnAction(actionEvent -> changeScene("createItem.fxml", actionEvent));
            VBox.setMargin(button, new Insets(200, 0,0,0));
            Label label = new Label();
            label.getStylesheets().add("style.css");
            label.getStyleClass().add("itsEmptyLabel");
            label.setText("Je tu smutno! Pridaj sem niečo.");
            VBox.setMargin(label, new Insets(20, 0,0,0));
            container.getChildren().clear();
            container.getChildren().add(button);
            container.getChildren().add(label);
        }


    }
}
