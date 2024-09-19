package com.entrolabs.ecommerceapp;

public class Review {
    String comment, date, reviewerName, reviewerEmail;
    int rating;

    public Review(String comment, String date, String reviewerName, String reviewerEmail, int rating) {
        this.comment = comment;
        this.date = date;
        this.reviewerName = reviewerName;
        this.reviewerEmail = reviewerEmail;
        this.rating = rating;
    }

    public Review() {
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
