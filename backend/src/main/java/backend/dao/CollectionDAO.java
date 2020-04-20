package backend.dao;

import backend.model.Collection;
import java.util.ArrayList;

public interface CollectionDAO {
    int insert(Collection collection);
    ArrayList<Collection> select(int userId);
    int delete(int id);
    int update(Collection collection);
}
