package vava;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import managers.ItemManager;
import managers.SceneManager;
import models.Collection;
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
}
