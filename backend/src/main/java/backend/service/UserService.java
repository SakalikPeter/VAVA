package backend.service;

import backend.dao.UserDAO;
import backend.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*Servis layer triedy User*/
@Service
public class UserService {
    @Autowired
    private UserDAO userDAO;

    // pridat pouzivatela do DB
    public int addUser(User user) {
        userDAO.insert(user);
        return 1;
    }

    // ziskat pouzivatela
    public User getUser(String name, String password) {
        return userDAO.select(name, password);
    }

    // odstranit pouzivatela
    public int removeUser(int id) {
        userDAO.delete(id);
        return 1;
    }

    // upravit pouzivatela
    public int updateUser(User user) {
        userDAO.update(user);
        return 1;
    }
}
