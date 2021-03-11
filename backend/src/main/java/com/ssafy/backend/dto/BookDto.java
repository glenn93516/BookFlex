package com.ssafy.backend.dto;

public class BookDto {
    private long book_isbn;
    private String book_title;
    private String book_author;
    private String book_publisher;
    private String book_contents;
    private String book_date;
    private String book_cover;

    public BookDto() {}

    public BookDto(long book_isbn, String book_title, String book_author, String book_publisher, String book_contents, String book_date, String book_cover) {
        this.book_isbn = book_isbn;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_contents = book_contents;
        this.book_date = book_date;
        this.book_cover = book_cover;
    }

    public long getBook_isbn() {
        return book_isbn;
    }

    public void setBook_isbn(long book_isbn) {
        this.book_isbn = book_isbn;
    }

    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public String getBook_contents() {
        return book_contents;
    }

    public void setBook_contents(String book_contents) {
        this.book_contents = book_contents;
    }

    public String getBook_date() {
        return book_date;
    }

    public void setBook_date(String book_date) {
        this.book_date = book_date;
    }

    public String getBook_cover() {
        return book_cover;
    }

    public void setBook_cover(String book_cover) {
        this.book_cover = book_cover;
    }

    @Override
    public String toString() {
        return "BookDto{" +
                "book_isbn=" + book_isbn +
                ", book_title='" + book_title + '\'' +
                ", book_author='" + book_author + '\'' +
                ", book_publisher='" + book_publisher + '\'' +
                ", book_contents='" + book_contents + '\'' +
                ", book_date='" + book_date + '\'' +
                ", book_cover='" + book_cover + '\'' +
                '}';
    }
}
