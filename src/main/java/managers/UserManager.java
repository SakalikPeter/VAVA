package managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.util.ArrayList;

public class UserManager implements DataAccess{
    @Autowired
    private DataSource source;

    private NamedParameterJdbcTemplate template;

    public UserManager() {
        this.template = new NamedParameterJdbcTemplate(source);
    }

    @Override
    public void insert(String queryString) {
        final String sql = "insert into employee(user_mane, password) values(:user_mane,:password)";
        KeyHolder holder = new GeneratedKeyHolder();
        SqlParameterSource param = new MapSqlParameterSource()
                .addValue("user_mane", "Matejko")
                .addValue("password", "sadlo");
        template.update(sql,param, holder);
    }

    @Override
    public ArrayList select(String queryString) {
        return null;
    }

    @Override
    public void update(String queryString) {

    }

    @Override
    public void delete(String queryString) {

    }
}
