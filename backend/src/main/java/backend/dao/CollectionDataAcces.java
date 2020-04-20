package backend.dao;

import backend.model.Collection;
import backend.model.CollectionRowMapper;
import backend.model.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/*Data access layer triedy Collection*/
@Repository
public class CollectionDataAcces implements CollectionDAO {
    @Autowired
    JdbcTemplate template;
    Logger collectionDAOLogger = LoggerFactory.getLogger(CollectionDataAcces.class);

    // vlozit kolekciu do DB
    @Override
    public int insert(Collection collection) {
        String query = "insert into vava.collection(user_id_fk, name, size) VALUES(?,?,?)";
        template.update(query, collection.getUserId(), collection.getName(), collection.getSize());
        collectionDAOLogger.info("Successfuly inserted collection: " + collection.getName());

        return 1;
    }

    // vybrat array list kolekcii z DB podla id pouzivatela
    @Override
    public ArrayList<Collection> select(int userId) {
        String query = "select * from vava.collection where user_id_fk = ?";
        collectionDAOLogger.info("Successfuly selected collection with user ID: " + userId);

        return (ArrayList<Collection>) template.query(query, new CollectionRowMapper(), userId);
    }

    // vymazat kolekciu z DB podla jej id
    @Override
    public int delete(int id) {
        String query = "delete from vava.collection where collection_id = ?";
        template.update(query, id);
        collectionDAOLogger.info("Successfuly deleted collection with ID: " + id);

        return 1;
    }

    // upravit kolekciu v DB podla jej id
    @Override
    public int update(Collection collection) {
        String query = "update vava.collection set name = ? where collection_id = ?";
        template.update(query, collection.getName(), collection.getId());
        collectionDAOLogger.info("Succesfuly updated collection with id: " + collection.getId());

        return 1;
    }
}
