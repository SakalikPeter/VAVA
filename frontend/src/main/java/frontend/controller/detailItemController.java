package frontend.controller;

import frontend.App;
import frontend.manager.ItemManager;
import frontend.manager.SceneManager;
import frontend.model.Item;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class detailItemController {
    private SceneManager sceneManager = App.getSceneManager();
    private ItemManager itemManager = App.getItemManager();

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

    public void initialize() {
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
}

