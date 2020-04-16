package backend.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.sql.Date;

public class Item {
    private int id;
    private int collectionId;
    private String name;
    private Date acquirementDate;
    private int productionYear;
    private String author;
    private String genre;
    private String brand;
    private String acquirementLocation;
    private String dimensions;
    private String originCountry;
    private int price;
    private int value;
    private String note;

    public Item(int id, int collectionId, String name, Date acquirementDate, int productionYear, String author, String genre,
                String brand, String acquirementLocation, String dimensions, String originCountry, int price,
                int value, String note) {
        this.id = id;
        this.collectionId = collectionId;
        this.name = name;
        this.acquirementDate = acquirementDate;
        this.productionYear = productionYear;
        this.author = author;
        this.genre = genre;
        this.brand = brand;
        this.acquirementLocation = acquirementLocation;
        this.dimensions = dimensions;
        this.originCountry = originCountry;
        this.price = price;
        this.value = value;
        this.note = note;
    }

    public Item(@JsonProperty("name") String name, @JsonProperty("collectionId")int collectionId, @JsonProperty("acquirementDate") Date acquirementDate, @JsonProperty("productionYear") int productionYear,
                @JsonProperty("author") String author, @JsonProperty("genre") String genre, @JsonProperty("brand") String brand, @JsonProperty("acquirementLocation") String acquirementLocation,
                @JsonProperty("dimensions") String dimensions, @JsonProperty("originCountry") String originCountry, @JsonProperty("price")int price, @JsonProperty("value")int value,
                @JsonProperty("note")String note) {
        this.name = name;
        this.collectionId = collectionId;
        this.acquirementDate = acquirementDate;
        this.productionYear = productionYear;
        this.author = author;
        this.genre = genre;
        this.brand = brand;
        this.acquirementLocation = acquirementLocation;
        this.dimensions = dimensions;
        this.originCountry = originCountry;
        this.price = price;
        this.value = value;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCollectionId() {
        return collectionId;
    }

    public void setCollectionId(int collectionId) {
        this.collectionId = collectionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getAcquirementDate() {
        return acquirementDate;
    }

    public void setAcquirementDate(Date acquirementDate) {
        this.acquirementDate = acquirementDate;
    }

    public int getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(int productionYear) {
        this.productionYear = productionYear;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAcquirementLocation() {
        return acquirementLocation;
    }

    public void setAcquirementLocation(String acquirementLocation) {
        this.acquirementLocation = acquirementLocation;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
