package frontend.controller;

import frontend.App;
import frontend.model.Collection;
import frontend.model.User;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class CollectionController {
    public Button createCollectionB;
    public TextField newCollectionTF;

    public void createCollection(ActionEvent actionEvent) {
        User user = App.getActivUser();
        String collectionName = newCollectionTF.getText();
        Collection collection = new Collection(user.getId(), collectionName, null, 0);
        App.getCollectionManager().createCollection(collection);
    }
}
