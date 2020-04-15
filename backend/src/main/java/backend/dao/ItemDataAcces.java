package backend.dao;

import backend.model.Item;
import backend.model.ItemRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ItemDataAcces implements ItemDAO {
    @Autowired
    JdbcTemplate template;

    @Override
    public int insert(Item item) {
        String query = "insert into vava.item(collection_id_fk, name, acquirement_date, production_year, author, " +
                "genre, brand, acquirement_location, dimensions, origin_country, price, value, note) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";

        template.update(query, item.getCollectionId(), item.getName(), item.getAcquirementDate(), item.getProductionYear(),
                item.getAuthor(), item.getGenre(), item.getBrand(), item.getAcquirementLocation(), item.getDimensions(), item.getOriginCountry(),
                item.getPrice(), item.getValue(), item.getNote());

        return 1;
    }

    @Override
    public ArrayList<Item> select(int collectionId) {
        String query = "select * from vava.item where collection_id_fk = ?";

        return (ArrayList<Item>) template.query(query, new ItemRowMapper(), collectionId);
    }

    @Override
    public int delete(int id) {
        String query = "delete from vava.item where element_id = ?";

        template.update(query, id);
        return 1;
    }

    @Override
    public int update(int id) {
        return 0;
    }
}
