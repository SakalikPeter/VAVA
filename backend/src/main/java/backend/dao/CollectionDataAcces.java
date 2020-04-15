package backend.dao;

import backend.model.Collection;
import backend.model.CollectionRowMapper;
import backend.model.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class CollectionDataAcces implements CollectionDAO {
    @Autowired
    JdbcTemplate template;

    @Override
    public int insert(Collection collection) {
        String query = "insert into vava.collection(user_id_fk, name, size) VALUES(?,?,?)";

        template.update(query, collection.getUserId(), collection.getName(), collection.getSize());
        return 1;
    }

    @Override
    public ArrayList<Collection> select(int userId) {
        String query = "select * from vava.collection where user_id_fk = ?";

        return (ArrayList<Collection>) template.query(query, new CollectionRowMapper(), userId);
    }

    @Override
    public int delete(int id) {
        String query = "delete from vava.collection where collection_id = ?";

        template.update(query, id);
        return 1;
    }
}
