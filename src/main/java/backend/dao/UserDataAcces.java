package backend.dao;

import backend.model.User;
import backend.model.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;

@Repository
public class UserDataAcces implements UserDAO {
    @Autowired
    private JdbcTemplate template;

    @Override
    public int insert(User user) {
        String query = "insert into vava.user(user_name, password) VALUES(?,?)";

        template.update(query, user.getUserName(), user.getPassword());
        return 1;
    }

    @Override
    public User select(String name, String password) {
        String query = "select * from vava.user where user_name = ? AND password = ?";

        return template.queryForObject(query, new UserRowMapper(), name, password);
    }
}
