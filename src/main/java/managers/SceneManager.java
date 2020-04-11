package managers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneManager {

    public void change_scene(String name, ActionEvent actionEvent){
        Parent root;
        String path = "/" + name;
        try {
            root = FXMLLoader.load(getClass().getResource(path));
            Stage stage = (Stage) ((Node)(actionEvent.getSource())).getScene().getWindow();
            stage.setTitle("Collection tracker");
            stage.setScene(new Scene(root, 1100, 800));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
