package frontend;

import frontend.manager.*;
import frontend.model.Item;
import frontend.model.User;
import frontend.model.Collection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

public class App extends Application {
    private static SceneManager sceneManager = new SceneManager();
    private static CollectionManager collectionManager = new CollectionManager();
    private static UserManager userManager = new UserManager();
    private static ItemManager itemManager = new ItemManager();
    private static PdfManager pdfManager = new PdfManager();

    private static User activUser;
    private static Collection collection;
    private static Item actItem;

    private static String language = "SK";

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        stage.setTitle("Collection Tracker");
        stage.setScene(new Scene(root, 1100, 800));
        stage.show();
    }

    public static Item getActItem() {
        return actItem;
    }

    public static void setActItem(Item actItem) {
        App.actItem = actItem;
    }

    public static ItemManager getItemManager() {
        return itemManager;
    }

    public static Collection getCollection() {
        return collection;
    }

    public static void setCollection(Collection collection) {
        App.collection = collection;
    }

    public static SceneManager getSceneManager() {
        return sceneManager;
    }

    public static CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public static UserManager getUserManager() {
        return userManager;
    }

    public static User getActivUser() {
        return activUser;
    }

    public static void setActivUser(User activUser) {
        App.activUser = activUser;
    }

    public static PdfManager getPdfManager() {
        return pdfManager;
    }

    public static void setPdfManager(PdfManager pdfManager) {
        App.pdfManager = pdfManager;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        App.language = language;
    }
}
