package backend.service;

import backend.dao.CollectionDAO;
import backend.model.Collection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CollectionService {
    @Autowired
    private CollectionDAO collectionDAO;

    public int addCollection(Collection collection) {
        collectionDAO.insert(collection);
        return 1;
    }

    public ArrayList<Collection> getCollections(int userId) {
        return collectionDAO.select(userId);
    }

    public int removeCollection(int id) {
        collectionDAO.delete(id);
        return 1;
    }

    public int updateCollection(Collection collection) {
        collectionDAO.update(collection);
        return 1;
    }
}
