package vava;

import javafx.scene.control.Button;
import managers.CollectionManager;
import models.Collection;
import models.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class CollectionController {
    public Button collection1;

    private CollectionManager collectionManager = Main.getCollectionManager();

    public void getAllCollections() throws SQLException {
        User user = Main.getUser();
        ArrayList<Collection> collections = collectionManager.select(user.getId());

        for (Collection collection : collections) {
            System.out.println(collection.getName());
        }
    }
}
