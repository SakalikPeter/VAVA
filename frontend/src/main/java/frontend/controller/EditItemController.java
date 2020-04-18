package frontend.controller;

import frontend.App;
import frontend.manager.ItemManager;
import frontend.model.Item;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EditItemController {
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
    public Button backB;
    public Label itemNameL;

    private ItemManager itemManager = App.getItemManager();

    public void initialize() {
        Item item = App.getActItem();

        itemNameL.setText(item.getName());
        updateItemName.setText(item.getName());
        updateItemAuthor.setText(item.getAuthor());
        updateItemBrand.setText(item.getBrand());
        updateItemProdYear.setText(Integer.toString(item.getProductionYear()));
        updateItemOrigCountry.setText(item.getOriginCountry());
        updateItemGenre.setText(item.getGenre());
        updateItemDimensions.setText(item.getDimensions());
        updateItemPrice.setText(Integer.toString(item.getPrice()));
        updateItemValue.setText(Integer.toString(item.getValue()));
        updateItemAcqLoc.setText(item.getAcquirementLocation());
        updateItemAcqDate.setValue(item.getAcquirementDate().toLocalDate());
        updateItemNote.setText(item.getNote());
    }
    public void updateItem(ActionEvent actionEvent) {
        String name = updateItemName.getText();
        String author = updateItemAuthor.getText();
        String brand = updateItemBrand.getText();
        int productionYear = Integer.parseInt(updateItemProdYear.getText());
        String originCountry = updateItemOrigCountry.getText();
        String genre = updateItemGenre.getText();
        String dimensions = updateItemDimensions.getText();
        int price = Integer.parseInt(updateItemPrice.getText());
        int value = Integer.parseInt(updateItemValue.getText());
        String acquirementLocation = updateItemAcqLoc.getText();
        Date acquirementDate = Date.valueOf(updateItemAcqDate.getValue());
        String note = updateItemNote.getText();

        Item newItem = new Item(name, App.getCollection().getId(), acquirementDate, productionYear, author, genre,
                brand, acquirementLocation, dimensions, originCountry, price, value, note);

        itemManager.updateItem(App.getActItem(), newItem);
    }

    public void backHome(ActionEvent actionEvent) {
        App.getSceneManager().changeScene("Home.fxml", actionEvent);
    }
}
