package backend.dao;

import backend.model.User;
import backend.model.UserRowMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;

/*Data access layer triedy User*/
@Repository
public class UserDataAcces implements UserDAO {
    @Autowired
    private JdbcTemplate template;
    Logger userDAOLogger = LoggerFactory.getLogger(UserDataAcces.class);

    // vlozit pouzivatela do DB
    @Override
    public int insert(User user) {
        String query = "insert into vava.user(user_name, password) VALUES(?,?)";
        template.update(query, user.getUserName(), user.getPassword());
        userDAOLogger.info("Successfuly inserted user: " + user.getUserName());

        return 1;
    }

    // vybrat pouzivatela z DB podla pouzivatelskeho mena a hesla
    @Override
    public User select(String name, String password) {
        String query = "select * from vava.user where user_name = ? AND password = ?";
        userDAOLogger.info("Successfuly selected user: " + name);

        return template.queryForObject(query, new UserRowMapper(), name, password);
    }

    // vymazat pouzivatela z DB podla jeho id
    @Override
    public int delete(int id) {
        String query = "delete from vava.user where user_id = ?";
        template.update(query, id);
        userDAOLogger.info("Succesfully deleted user with id: " + id);

        return 1;
    }

    // upravit pouzivatela v DB podla jeho id
    @Override
    public int update(User user) {
        String query = "update vava.user SET user_name = ?, password = ? where user_id = ?";
        template.update(query, user.getUserName(), user.getPassword(), user.getId());
        userDAOLogger.info("Succesfully updated user with id: " + user.getId());

        return 1;
    }
}
