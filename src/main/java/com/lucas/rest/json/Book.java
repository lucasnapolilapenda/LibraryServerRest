package com.lucas.rest.json;

public class Book implements Cloneable {

    private Integer id;
    private String title;
    private String description;
    private String isbn;
    private String author;
    private String publisher;

    int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getDescription() {
        return description;
    }

    void setDescription(String description) {
        this.description = description;
    }

    String getIsbn() {
        return isbn;
    }

    void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    String getAuthor() {
        return author;
    }

    void setAuthor(String author) {
        this.author = author;
    }

    String getPublisher() {
        return publisher;
    }

    void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Book clone() {
        Book b = new Book();
        b.setId(this.id);
        b.setTitle(this.title);
        b.setDescription(this.description);
        b.setIsbn(this.isbn);
        b.setAuthor(this.author);
        b.setPublisher(this.publisher);
        return b;
    }
}
