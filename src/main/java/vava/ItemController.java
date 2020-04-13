package vava;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import managers.ItemManager;
import managers.SceneManager;
import models.Item;

import java.sql.Date;
import java.sql.SQLException;

public class ItemController {
    public Button backB;
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

    private SceneManager sceneManager = Main.getSceneManager();
    private ItemManager itemManager = Main.getItemManager();

    public void backHome(ActionEvent actionEvent) {
        sceneManager.changeScene("home.fxml", actionEvent);
    }

    public void insertItemB(ActionEvent actionEvent) throws SQLException {
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

        Item item = new Item(name, Main.getActCollection().getId(), acquirementDate, productionYear, author, genre,
                brand, acquirementLocation, dimensions, originCountry, price, value, note);

        itemManager.insert(item);
    }

    public void updateItem(ActionEvent actionEvent) throws SQLException {
        if (!updateItemName.getText().trim().isEmpty()) {
            Main.getActItem().setName(updateItemName.getText());
        }
        if (!updateItemAuthor.getText().trim().isEmpty()) {
            Main.getActItem().setAuthor(updateItemAuthor.getText());
        }
        if (!updateItemBrand.getText().trim().isEmpty()) {
            Main.getActItem().setBrand(updateItemBrand.getText());
        }
        if (!updateItemProdYear.getText().trim().isEmpty()) {
            Main.getActItem().setProductionYear(Integer.parseInt(updateItemProdYear.getText()));
        }
        if (!updateItemOrigCountry.getText().trim().isEmpty()) {
            Main.getActItem().setOriginCountry(updateItemOrigCountry.getText());
        }
        if (!updateItemGenre.getText().trim().isEmpty()) {
            Main.getActItem().setGenre(updateItemGenre.getText());
        }
        if (!updateItemDimensions.getText().trim().isEmpty()) {
            Main.getActItem().setDimensions(updateItemDimensions.getText());
        }
        if (!updateItemPrice.getText().trim().isEmpty()) {
            Main.getActItem().setPrice(Integer.parseInt(updateItemPrice.getText()));
        }
        if (!updateItemValue.getText().trim().isEmpty()) {
            Main.getActItem().setValue(Integer.parseInt(updateItemValue.getText()));
        }
        if (!updateItemAcqLoc.getText().trim().isEmpty()) {
            Main.getActItem().setAcquirementLocation(updateItemAcqLoc.getText());
        }
        if (updateItemAcqDate.getValue() != null) {
            Main.getActItem().setAcquirementDate(Date.valueOf(updateItemAcqDate.getValue()));
        }
        if (!updateItemNote.getText().trim().isEmpty()) {
            Main.getActItem().setNote(updateItemNote.getText());
        }

        itemManager.update(Main.getActItem());
    }
}
