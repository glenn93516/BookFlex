package com.ssafy.backend.dto;

public class ReviewDto {
    private long review_id;
    private long book_isbn;
    private String review_content;
    private int review_rating;

    public ReviewDto() {
    }

    public ReviewDto(long review_id, long book_isbn, String review_content, int review_rating) {
        this.review_id = review_id;
        this.book_isbn = book_isbn;
        this.review_content = review_content;
        this.review_rating = review_rating;
    }

    public long getReview_id() {
        return review_id;
    }

    public void setReview_id(long review_id) {
        this.review_id = review_id;
    }

    public long getBook_isbn() {
        return book_isbn;
    }

    public void setBook_isbn(long book_isbn) {
        this.book_isbn = book_isbn;
    }

    public String getReview_content() {
        return review_content;
    }

    public void setReview_content(String review_content) {
        this.review_content = review_content;
    }

    public int getReview_rating() {
        return review_rating;
    }

    public void setReview_rating(int review_rating) {
        this.review_rating = review_rating;
    }

    @Override
    public String toString() {
        return "ReviewDto{" +
                "review_id=" + review_id +
                ", book_isbn=" + book_isbn +
                ", review_content='" + review_content + '\'' +
                ", review_rating=" + review_rating +
                '}';
    }
}
