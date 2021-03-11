package com.ssafy.backend.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class WishlistDto {
    @ApiModelProperty(value = "위시리스트 ID (PK)")
    private long wishlist_id;
    @ApiModelProperty(value = "위시리스트 등록일")
    private String wishlist_date;
    @ApiModelProperty(value = "유저 id")
    private long user_id;
    @ApiModelProperty(value = "책 isbn")
    private long book_isbn;

    public WishlistDto() {}

    public WishlistDto(long wishlist_id, String wishlist_date, long user_id, long book_isbn) {
        this.wishlist_id = wishlist_id;
        this.wishlist_date = wishlist_date;
        this.user_id = user_id;
        this.book_isbn = book_isbn;
    }

    public long getWishlist_id() {
        return wishlist_id;
    }

    public void setWishlist_id(long wishlist_id) {
        this.wishlist_id = wishlist_id;
    }

    public String getWishlist_date() {
        return wishlist_date;
    }

    public void setWishlist_date(String wishlist_date) {
        this.wishlist_date = wishlist_date;
    }

    public long getUser_id() {
        return user_id;
    }

    public void setUser_id(long user_id) {
        this.user_id = user_id;
    }

    public long getBook_isbn() {
        return book_isbn;
    }

    public void setBook_isbn(long book_isbn) {
        this.book_isbn = book_isbn;
    }

    @Override
    public String toString() {
        return "WishlistDto{" +
                "wishlist_id=" + wishlist_id +
                ", wishlist_date='" + wishlist_date + '\'' +
                ", user_id=" + user_id +
                ", book_isbn=" + book_isbn +
                '}';
    }
}
