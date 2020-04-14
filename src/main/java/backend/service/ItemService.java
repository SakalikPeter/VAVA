package backend.service;

import backend.dao.ItemDAO;
import backend.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemService {
    @Autowired
    ItemDAO itemDAO;

    public int addItem(Item item) {
        itemDAO.insert(item);
        return 1;
    }

    public ArrayList<Item> getItems(int collectionId) {
       ArrayList<Item> items = itemDAO.select(collectionId);
       return items;
    }

    public int removeItem(int id) {
        itemDAO.delete(id);
        return 1;
    }
}
