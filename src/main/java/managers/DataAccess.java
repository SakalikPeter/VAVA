package managers;

import java.util.ArrayList;

public interface DataAccess {
    void insert(String queryString);
    ArrayList select(String queryString);
    void update(String queryString);
    void delete(String queryString);
}
