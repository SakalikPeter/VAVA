package backend.dao;

import backend.model.Item;
import backend.model.ItemRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

/*Data access layer triedy Item*/
@Repository
public class ItemDataAcces implements ItemDAO {
    @Autowired
    JdbcTemplate template;
    Logger itemDAOLogger = LoggerFactory.getLogger(ItemDataAcces.class);

    // vlozit prvok do DB
    @Override
    public int insert(Item item) {
        String query = "insert into vava.item(collection_id_fk, name, acquirement_date, production_year, author, " +
                "genre, brand, acquirement_location, dimensions, origin_country, price, value, note) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        template.update(query, item.getCollectionId(), item.getName(), item.getAcquirementDate(), item.getProductionYear(),
                item.getAuthor(), item.getGenre(), item.getBrand(), item.getAcquirementLocation(), item.getDimensions(), item.getOriginCountry(),
                item.getPrice(), item.getValue(), item.getNote());

        itemDAOLogger.info("Successfuly inserted item: " + item.getName());

        return 1;
    }

    // vybrat prvky z DB podla id kolekcie
    @Override
    public ArrayList<Item> select(int collectionId) {
        String query = "select * from vava.item where collection_id_fk = ?";
        itemDAOLogger.info("Successfuly selected item with collection ID: " + collectionId);

        return (ArrayList<Item>) template.query(query, new ItemRowMapper(), collectionId);
    }

    // vymazat prvok z DB podla jeho id
    @Override
    public int delete(int id) {
        String query = "delete from vava.item where element_id = ?";
        template.update(query, id);
        itemDAOLogger.info("Successfuly deleted item with ID: " + id);

        return 1;
    }
}
