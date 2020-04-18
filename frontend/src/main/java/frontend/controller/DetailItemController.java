package frontend.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import frontend.App;
import frontend.manager.ItemManager;
import frontend.manager.SceneManager;
import frontend.model.Item;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

public class DetailItemController {
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

    public Button createPDF;

    public Button backB;
    public Label nameL;
    public Label authorL;
    public Label brandL;
    public Label yearL;
    public Label countryL;
    public Label genreL;
    public Label sizeL;
    public Label priceL;
    public Label valueL;
    public Label placeL;
    public Label dateL;
    public TextArea noteL;
    public Label itemNameBig;
    public Button editItemB;
    public Button removeItemB;

    private SceneManager sceneManager = App.getSceneManager();
    private ItemManager itemManager = App.getItemManager();

    public void initialize() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle(App.getLanguage());

        editItemB.setText(resourceBundle.getString("MainController.editItemB"));
        removeItemB.setText(resourceBundle.getString("MainController.removeItemButt"));
        createPDF.setText(resourceBundle.getString("ItemController.createPDF"));
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
        showItem();
    }
    public void backHome(ActionEvent actionEvent) {
        sceneManager.changeScene("Home.fxml", actionEvent);
    }

    public void showItem() {
        Item item = App.getActItem();
        itemNameBig.setText(item.getName());
        nameL.setText(item.getName());
        authorL.setText(item.getAuthor());
        brandL.setText(item.getBrand());
        yearL.setText(Integer.toString(item.getProductionYear()));
        countryL.setText(item.getOriginCountry());
        genreL.setText(item.getGenre());
        sizeL.setText(item.getDimensions());
        priceL.setText(item.getPrice() + "€");
        valueL.setText(item.getValue() + "€");
        placeL.setText(item.getAcquirementLocation());

        System.out.println(item.getAcquirementDate());
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(item.getAcquirementDate());
        dateL.setText(date);

        noteL.setText(item.getNote());
    }

    public void createPDF(ActionEvent actionEvent) {
        App.getPdfManager().createPDF();
    }

    public void editItem(ActionEvent actionEvent) {
        sceneManager.changeScene("updateItem.fxml", actionEvent);
    }

    public void removeItem(ActionEvent actionEvent) {
        itemManager.removeItem(App.getActItem().getId());
    }
}

