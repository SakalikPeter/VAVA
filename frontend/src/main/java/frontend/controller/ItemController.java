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
    public TextField updateItemName;
    public TextField updateItemAuthor;
    public TextField updateItemBrand;
    public TextField updateItemProdYear;
    public TextField updateItemOrigCountry;
    public TextField updateItemGenre;
    public TextField updateItemDimensions;
    public TextField updateItemPrice;
    public TextField updateItemValue;
    public TextField updateItemAcqLoc;
    public DatePicker updateItemAcqDate;
    public TextArea updateItemNote;
    public Button updateItemB;

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

    public void updateItem(ActionEvent actionEvent) {

        if (!updateItemName.getText().trim().isEmpty()) {
            App.getActItem().setName(updateItemName.getText());
        }
        if (!updateItemAuthor.getText().trim().isEmpty()) {
            App.getActItem().setAuthor(updateItemAuthor.getText());
        }
        if (!updateItemBrand.getText().trim().isEmpty()) {
            App.getActItem().setBrand(updateItemBrand.getText());
        }
        if (!updateItemProdYear.getText().trim().isEmpty()) {
            App.getActItem().setProductionYear(Integer.parseInt(updateItemProdYear.getText()));
        }
        if (!updateItemOrigCountry.getText().trim().isEmpty()) {
            App.getActItem().setOriginCountry(updateItemOrigCountry.getText());
        }
        if (!updateItemGenre.getText().trim().isEmpty()) {
            App.getActItem().setGenre(updateItemGenre.getText());
        }
        if (!updateItemDimensions.getText().trim().isEmpty()) {
            App.getActItem().setDimensions(updateItemDimensions.getText());
        }
        if (!updateItemPrice.getText().trim().isEmpty()) {
            App.getActItem().setPrice(Integer.parseInt(updateItemPrice.getText()));
        }
        if (!updateItemValue.getText().trim().isEmpty()) {
            App.getActItem().setValue(Integer.parseInt(updateItemValue.getText()));
        }
        if (!updateItemAcqLoc.getText().trim().isEmpty()) {
            App.getActItem().setAcquirementLocation(updateItemAcqLoc.getText());
        }
        if (updateItemAcqDate.getValue() != null) {
            App.getActItem().setAcquirementDate(Date.valueOf(updateItemAcqDate.getValue()));
        }
        if (!updateItemNote.getText().trim().isEmpty()) {
            App.getActItem().setNote(updateItemNote.getText());
        }

        itemManager.updateItem(App.getActItem());
    }
}
