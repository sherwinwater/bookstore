package data;

import java.io.Serializable;

public class Book implements Serializable{
    
    private String id;
    private Double price;
    private String title;
    private String author;

    public Book() {
    }

    public Book(String id, Double price, String title, String author) {
        this.id = id;
        this.price = price;
        this.title = title;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
