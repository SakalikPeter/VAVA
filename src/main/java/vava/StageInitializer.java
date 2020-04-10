package vava;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import vava.Main.StageReadyEvent;

import java.io.IOException;

@Component
public class StageInitializer implements ApplicationListener<StageReadyEvent> {
    @Value("classpath:/home.fxml")
    private Resource home;
    private String appTitle;
    private ApplicationContext appContext;

    public StageInitializer(@Value("${spring.application.ui.title}") String appTitle, ApplicationContext appContext) {
        this.appTitle = appTitle;
        this.appContext = appContext;
    }

    @Override
    public void onApplicationEvent(StageReadyEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(home.getURL());
//            loader.setControllerFactory(aClass -> appContext.getBean(aClass));
            Parent parent = loader.load();

            Stage stage = event.getStage();
            stage.setScene(new Scene(parent, 1100, 800));
            stage.setTitle(appTitle);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
