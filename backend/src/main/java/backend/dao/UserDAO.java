package backend.dao;

import backend.model.User;

import java.util.ArrayList;

public interface UserDAO {

    int insert(User user);
    User select(String name, String password);
    int delete(int id);
    int update(User user);
}
