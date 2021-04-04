package com.ssafy.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class ReviewDto {
    @ApiModelProperty(value = "책 isbn")
    private long book_isbn;
    @ApiModelProperty(value = "리뷰 내용")
    private String review_content;
    @ApiModelProperty(value = "리뷰 점수 (0~10 정수)")
    private int review_rating;

    public ReviewDto() {
    }

    public ReviewDto(long book_isbn, String review_content, int review_rating) {
        this.book_isbn = book_isbn;
        this.review_content = review_content;
        this.review_rating = review_rating;
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
                ", book_isbn=" + book_isbn +
                ", review_content='" + review_content + '\'' +
                ", review_rating=" + review_rating +
                '}';
    }
}
