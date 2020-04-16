package frontend.controller;

import frontend.App;
import frontend.manager.ItemManager;
import frontend.manager.SceneManager;
import frontend.model.Item;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.Date;

public class ItemController {
    private SceneManager sceneManager = App.getSceneManager();
    private ItemManager itemManager = App.getItemManager();

    public TextField itemName;
    public TextField itemAuthor;
    public TextField itemBrand;
    public TextField itemProdYear;
    public TextField itemOrigCountry;
    public TextField itemGenre;
    public TextField itemDimension;
    public TextField itemPrice;
    public TextField itemValue;
    public TextField itemAcqLoc;
    public DatePicker itemAcqDate;
    public TextArea itemNote;
    public Button insertItem;
    public Button backB;

    public void backHome(ActionEvent actionEvent) {
        sceneManager.changeScene("Home.fxml", actionEvent);
    }

    public void insertItem(ActionEvent actionEvent) {
        String name = itemName.getText();
        String author = itemAuthor.getText();
        String brand = itemBrand.getText();
        int productionYear = Integer.parseInt(itemProdYear.getText());
        String originCountry = itemOrigCountry.getText();
        String genre = itemGenre.getText();
        String dimensions = itemDimension.getText();
        int price = Integer.parseInt(itemPrice.getText());
        int value = Integer.parseInt(itemValue.getText());
        String acquirementLocation = itemAcqLoc.getText();
        Date acquirementDate = Date.valueOf(itemAcqDate.getValue());
        String note = itemNote.getText();

        Item item = new Item(name, App.getCollection().getId(), acquirementDate, productionYear, author, genre,
                brand, acquirementLocation, dimensions, originCountry, price, value, note);

        itemManager.addItem(item);
    }
}
