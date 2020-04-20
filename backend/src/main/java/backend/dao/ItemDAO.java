package backend.dao;

import backend.model.Item;

import java.util.ArrayList;

public interface ItemDAO {
    int insert(Item item);
    ArrayList<Item> select(int collectionId);
    int delete(int id);
}
