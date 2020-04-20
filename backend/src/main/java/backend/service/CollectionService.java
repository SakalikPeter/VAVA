package backend.service;

import backend.dao.CollectionDAO;
import backend.model.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/*Servis layer triedy Collection*/
@Service
public class CollectionService {
    @Autowired
    private CollectionDAO collectionDAO;

    // pridat kolekciu
    public int addCollection(Collection collection) {
        collectionDAO.insert(collection);
        return 1;
    }

    // ziskat array list kolekcii pouzivatela
    public ArrayList<Collection> getCollections(int userId) {
        return collectionDAO.select(userId);
    }

    // odstranit kolekciu
    public int removeCollection(int id) {
        collectionDAO.delete(id);
        return 1;
    }

    // upravit kolekciu
    public int updateCollection(Collection collection) {
        collectionDAO.update(collection);
        return 1;
    }
}
