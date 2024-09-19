package com.entrolabs.ecommerceapp;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product {
    private int id;
    private String title;
    private String description;
    private String category;
    private double price;
    @SerializedName("discountPercentage")
    private double discount;
    private double rating;
    private int stock;
    private String brand;
    private List<String> images;
    private String thumbnail;
    private String warrantyInformation;
    private String shippingInformation;
    private String availabilityStatus;
    private List<Review> reviews;


    public Product(int id, String title, String description, String category, double price, double discount, double rating, int stock, String brand, List<String> images, String thumbnail, String warrantyInformation, String shippingInformation, String availabilityStatus, List<Review> reviews) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.discount = discount;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.images = images;
        this.thumbnail = thumbnail;
        this.warrantyInformation = warrantyInformation;
        this.shippingInformation = shippingInformation;
        this.availabilityStatus = availabilityStatus;
        this.reviews = reviews;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getWarrantyInformation() {
        return warrantyInformation;
    }

    public void setWarrantyInformation(String warrantyInformation) {
        this.warrantyInformation = warrantyInformation;
    }

    public String getShippingInformation() {
        return shippingInformation;
    }

    public void setShippingInformation(String shippingInformation) {
        this.shippingInformation = shippingInformation;
    }

    public String getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(String availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }
}
/*
    public class Review {
        String comment, date, reviewerName, reviewerEmail;

        public Review(String comment, String date, String reviewerName, String reviewerEmail) {
            this.comment = comment;
            this.date = date;
            this.reviewerName = reviewerName;
            this.reviewerEmail = reviewerEmail;
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


}
*/
