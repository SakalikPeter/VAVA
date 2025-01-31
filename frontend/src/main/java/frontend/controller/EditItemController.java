package frontend.controller;

import frontend.App;
import frontend.manager.ItemManager;
import frontend.model.Item;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.sql.Date;
import java.util.ResourceBundle;

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
    public Label createMandatoryL;

    private ItemManager itemManager = App.getItemManager();
    private Logger logger = LoggerFactory.getLogger(EditItemController.class);
    private ResourceBundle resourceBundle = ResourceBundle.getBundle(App.getLanguage());

    // inicializacia textu a jazyka
    public void initialize() {
        Item item = App.getActItem();

        updateItemB.setText(resourceBundle.getString("ItemController.editB"));
        createNameL.setText(resourceBundle.getString("ItemController.createNameL")+ " *");
        createAuthorL.setText(resourceBundle.getString("ItemController.createAuthorL")+ " *");
        createBrandL.setText(resourceBundle.getString("ItemController.createBrandL"));
        createYearL.setText(resourceBundle.getString("ItemController.createYearL")+ " *");
        createCountryL.setText(resourceBundle.getString("ItemController.createCountryL"));
        createGenreL.setText(resourceBundle.getString("ItemController.createGenreL"));
        createDimensionsL.setText(resourceBundle.getString("ItemController.createDimensionsL"));
        createPriceL.setText(resourceBundle.getString("ItemController.createPriceL")+ " *");
        createValueL.setText(resourceBundle.getString("ItemController.createValueL")+ " *");
        createAqPlaceL.setText(resourceBundle.getString("ItemController.createAqPlaceL"));
        createDateL.setText(resourceBundle.getString("ItemController.createDateL")+ " *");
        createNoteL.setText(resourceBundle.getString("ItemController.createNoteL"));
        createMandatoryL.setText(resourceBundle.getString("ItemController.createMandatoryL"));

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

    // upravenie prvku
    public void updateItem(ActionEvent actionEvent) {
        try {
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
            App.getSceneManager().showDialog(resourceBundle.getString("ItemController.info2"));

        } catch(Exception e) {
            logger.error("Incorrect integer value");
            App.getSceneManager().showWarning(resourceBundle.getString("ItemController.warning"));
        }

    }

    // navrat na domovsku stranku
    public void backHome(ActionEvent actionEvent) {
        App.getSceneManager().changeScene("Home.fxml", actionEvent);
    }
}
