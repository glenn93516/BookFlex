package com.ssafy.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BookDto {
    @ApiModelProperty(value = "isbn")
    private long book_isbn;
    @ApiModelProperty(value = "제목")
    private String book_title;
    @ApiModelProperty(value = "저자")
    private String book_author;
    @ApiModelProperty(value = "출판사")
    private String book_publisher;
    @ApiModelProperty(value = "목차")
    private String book_contents;
    @ApiModelProperty(value = "출간일")
    private String book_date;
    @ApiModelProperty(value = "표지 이미지")
    private String book_cover;
    @ApiModelProperty(value = "도서 설명")
    private String book_description;

    public BookDto() {}

    public BookDto(long book_isbn, String book_title, String book_author, String book_publisher, String book_contents, String book_date, String book_cover, String book_description) {
        this.book_isbn = book_isbn;
        this.book_title = book_title;
        this.book_author = book_author;
        this.book_publisher = book_publisher;
        this.book_contents = book_contents;
        this.book_date = book_date;
        this.book_cover = book_cover;
        this.book_description = book_description;
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

    public String getBook_description() {
        return book_description;
    }

    public void setBook_description(String book_description) {
        this.book_description = book_description;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BookDto{");
        sb.append("book_isbn=").append(book_isbn);
        sb.append(", book_title='").append(book_title).append('\'');
        sb.append(", book_author='").append(book_author).append('\'');
        sb.append(", book_publisher='").append(book_publisher).append('\'');
        sb.append(", book_contents='").append(book_contents).append('\'');
        sb.append(", book_date='").append(book_date).append('\'');
        sb.append(", book_cover='").append(book_cover).append('\'');
        sb.append(", book_description='").append(book_description).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
