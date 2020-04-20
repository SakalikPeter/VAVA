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
import java.util.ResourceBundle;

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

    public void addCollectionButtons(MainController controller, VBox container, ArrayList<Collection> collections) {
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
    }

    public boolean showCollection(ArrayList<Item> items, VBox container) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(App.getLanguage());

        if(items.size() > 0) {
            TableView<Item> table = new TableView<Item>();
            TableColumn<Item, String> nameCol = new TableColumn<>(resourceBundle.getString("ItemController.createNameL"));
            TableColumn<Item, String> authorCol = new TableColumn<>(resourceBundle.getString("ItemController.createAuthorL"));
            TableColumn<Item, Integer> yearCol = new TableColumn<>(resourceBundle.getString("ItemController.createYearL"));
            table.getColumns().add(nameCol);
            table.getColumns().add(authorCol);
            table.getColumns().add(yearCol);

            nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
            authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
            yearCol.setCellValueFactory(new PropertyValueFactory<>("productionYear"));
            table.getItems().addAll(items);
            table.getStylesheets().add("style.css");

            container.getChildren().clear();
            container.getChildren().add(table);

            table.setOnMouseClicked((MouseEvent event) -> {
                if (table.getSelectionModel().getSelectedItem() != null) {
                    App.setActItem(table.getSelectionModel().getSelectedItem());
                    System.out.println(App.getActItem().getName());
                }
            });
            return true;
        }
        else {
            Button button = new Button(resourceBundle.getString("MainController.addItemB"));
            button.setPrefWidth(160);
            button.setPrefHeight(45);
            button.getStylesheets().add("style.css");
            button.getStyleClass().add("addItemB");
            button.setOnAction(actionEvent -> changeScene("createItem.fxml", actionEvent));
            VBox.setMargin(button, new Insets(200, 0,0,0));
            Label label = new Label();
            label.getStylesheets().add("style.css");
            label.getStyleClass().add("itsEmptyLabel");
            label.setText(resourceBundle.getString("MainController.emptyNoticeL"));
            VBox.setMargin(label, new Insets(20, 0,0,0));
            container.getChildren().clear();
            container.getChildren().add(button);
            container.getChildren().add(label);
            return false;
        }
    }

    public void showDialog(String info) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText(null);
        alert.setContentText(info);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("style.css");
        dialogPane.getStyleClass().add("dialog");

        alert.showAndWait();
    }

    public void showWarning(String warning) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning Dialog");
        alert.setHeaderText(null);
        alert.setContentText(warning);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add("style.css");
        dialogPane.getStyleClass().add("dialog");

        alert.showAndWait();
    }
}
