package frontend.controller;

import frontend.App;
import frontend.manager.ItemManager;
import frontend.manager.SceneManager;
import frontend.model.Item;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ItemController {
    public Label createBigLabel;
    public Label createNameL;
    public Label createAuthorL;
    public Label createBrandL;
    public Label createYearL;
    public Label createCountryL;
    public Label createGenreL;
    public Label createDimensionsL;
    public Label createPriceL;
    public Label createValueL;
    public Label createAqPlaceL;
    public Label createDateL;
    public Label createNoteL;

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

    Logger logger = LoggerFactory.getLogger(ItemController.class);

    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(App.getLanguage());

        insertItem.setText(resourceBundle.getString("ItemController.inserItemB"));
        createBigLabel.setText(resourceBundle.getString("ItemController.createBigLabel"));
        createNameL.setText(resourceBundle.getString("ItemController.createNameL"));
        createAuthorL.setText(resourceBundle.getString("ItemController.createAuthorL"));
        createBrandL.setText(resourceBundle.getString("ItemController.createBrandL"));
        createYearL.setText(resourceBundle.getString("ItemController.createYearL"));
        createCountryL.setText(resourceBundle.getString("ItemController.createCountryL"));
        createGenreL.setText(resourceBundle.getString("ItemController.createGenreL"));
        createDimensionsL.setText(resourceBundle.getString("ItemController.createDimensionsL"));
        createPriceL.setText(resourceBundle.getString("ItemController.createPriceL"));
        createValueL.setText(resourceBundle.getString("ItemController.createValueL"));
        createAqPlaceL.setText(resourceBundle.getString("ItemController.createAqPlaceL"));
        createDateL.setText(resourceBundle.getString("ItemController.createDateL"));
        createNoteL.setText(resourceBundle.getString("ItemController.createNoteL"));
    }

    public void backHome(ActionEvent actionEvent) throws SQLException {
        sceneManager.changeScene("Home.fxml", actionEvent);
    }

    public void insertItem(ActionEvent actionEvent) {
        try {
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
        } catch (Exception e) {
            logger.error("Incorrect integer value");
        }
    }
}
