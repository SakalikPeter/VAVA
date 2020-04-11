package vava;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import managers.CollectionManager;
import managers.ItemManager;
import managers.UserManager;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;

import java.sql.SQLException;

public class Main extends Application {
    private ConfigurableApplicationContext applicationContext;
    static UserManager um = new UserManager();
    static CollectionManager cm = new CollectionManager();
    static ItemManager im = new ItemManager();

    @Override
    public void start(Stage stage) throws SQLException {
        im.select();
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
}
