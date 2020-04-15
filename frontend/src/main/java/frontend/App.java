package frontend;

import frontend.manager.CollectionManager;
import frontend.manager.SceneManager;
import frontend.manager.UserManager;
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

    private static User activUser;
    private static Collection collection;

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/login.fxml"));
        stage.setTitle("Collection Tracker");
        stage.setScene(new Scene(root, 1100, 800));
        stage.show();
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
}
