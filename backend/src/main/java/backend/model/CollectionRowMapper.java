package backend.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CollectionRowMapper implements RowMapper<Collection> {
    @Override
    public Collection mapRow(ResultSet rs, int i) throws SQLException {
        return (new Collection(rs.getInt(1), rs.getInt(2),
                rs.getString(3), rs.getDate(4), rs.getInt(5)));
    }
}
