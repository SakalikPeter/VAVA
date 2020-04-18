package frontend.manager;

import frontend.App;
import frontend.controller.MainController;
import frontend.model.Collection;
import frontend.model.Item;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SceneManager {

    Logger logger = LoggerFactory.getLogger(SceneManager.class);

    public void changeScene(String name, ActionEvent actionEvent){
        Parent root;
        String path = "/" + name;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage) ((Node)(actionEvent.getSource())).getScene().getWindow();
            stage.setTitle("Collection tracker");
            stage.setScene(new Scene(root, 1100, 800));
            stage.show();
            logger.info("Successfuly changed scene: " + name);
        }
        catch (IOException e) {
            logger.error("IOException exception");
        }
    }

    public void setContent(String name, BorderPane mainContent) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/" + name));
            logger.info("Successfuly setted content: " + name);
        } catch (IOException e) {
            logger.error("IOException exception");
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
                    logger.error("SQL exception");
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

            Button createItem = new Button("pridať");
            createItem.getStyleClass().add("itemButt");
            createItem.getStylesheets().add("style.css");
            container.getChildren().add(createItem);
            createItem.setOnAction(actionEvent -> changeScene("createItem.fxml", actionEvent));

            table.setOnMouseClicked((MouseEvent event) -> {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    App.setActItem(table.getSelectionModel().getSelectedItem());
                    System.out.println(App.getActItem().getName());
                }
            });


            Button showItemB = new Button("zobraziť");
            showItemB.getStyleClass().add("itemButt");
            showItemB.getStylesheets().add("style.css");
            container.getChildren().add(showItemB);
            showItemB.setOnAction(actionEvent -> changeScene("itemDetail.fxml", actionEvent));

            Button updateItem = new Button("upraviť");
            updateItem.getStyleClass().add("itemButt");
            updateItem.getStylesheets().add("style.css");
            container.getChildren().add(updateItem);
            updateItem.setOnAction(actionEvent -> changeScene("updateItem.fxml", actionEvent));

            Button deleteItem = new Button("odstrániť");
            deleteItem.getStyleClass().add("itemButt");
            deleteItem.getStylesheets().add("style.css");
            container.getChildren().add(deleteItem);
            deleteItem.setOnAction(actionEvent -> {
                App.getItemManager().removeItem(App.getActItem().getId());
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
