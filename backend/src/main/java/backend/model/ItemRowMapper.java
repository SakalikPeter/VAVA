package backend.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int i) throws SQLException {
        return (new Item(rs.getInt(1), rs.getInt(2), rs.getString(3),
                rs.getDate(4), rs.getInt(5), rs.getString(6),
                rs.getString(7), rs.getString(8), rs.getString(9),
                rs.getString(10), rs.getString(11), rs.getInt(12),
                rs.getInt(13), rs.getString(14)));
    }
}
