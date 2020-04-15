package backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class Collection {
    private int id;
    private int userId;
    private String name;
    private Date creationDate;
    private int size;

    public Collection(int id, int userId, String name, Date creationDate, int size) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.creationDate = creationDate;
        this.size = size;
    }

    public Collection(@JsonProperty("userId") int userId, @JsonProperty("name") String name, @JsonProperty("creationDate") Date creationDate, @JsonProperty("size") int size) {
        this.userId = userId;
        this.name = name;
        this.creationDate = creationDate;
        this.size = size;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
