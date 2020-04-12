package vava;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import managers.CollectionManager;
import managers.ItemManager;
import managers.SceneManager;
import managers.UserManager;
import models.Collection;
import models.User;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;


public class Main extends Application {
    private ConfigurableApplicationContext applicationContext;
    private static UserManager userManager = new UserManager();
    private static CollectionManager collectionManager = new CollectionManager();
    private static ItemManager itemManager = new ItemManager();
    private static SceneManager sceneManager = new SceneManager();
    private static User user;
    private static Collection actCollection;

    @Override
    public void start(Stage stage) {
        applicationContext.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void init() {
        applicationContext = new SpringApplicationBuilder(VavaApplication.class).run();
    }

    @Override
    public void stop() {
        applicationContext.close();
        Platform.exit();
    }

    static class StageReadyEvent extends ApplicationEvent {
        public StageReadyEvent(Stage stage) {
            super(stage);
        }

        public Stage getStage() {
            return ((Stage) getSource());
        }
    }

    public static SceneManager getSceneManager() {
        return sceneManager;
    }

    public static UserManager getUserManager() {
        return userManager;
    }

    public static CollectionManager getCollectionManager() {
        return collectionManager;
    }

    public static ItemManager getItemManager() {
        return itemManager;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        Main.user = user;
    }

    public static Collection getActCollection() {
        return actCollection;
    }

    public static void setActCollection(Collection actCollection) {
        Main.actCollection = actCollection;
    }
}
