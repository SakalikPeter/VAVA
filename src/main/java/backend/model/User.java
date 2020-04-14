package backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
    private int id;
    private String userName;
    private String password;

    public User(@JsonProperty("id") int id, @JsonProperty("name") String userName, @JsonProperty("password") String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
