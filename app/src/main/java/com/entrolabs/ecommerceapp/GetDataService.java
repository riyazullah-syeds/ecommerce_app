package com.entrolabs.ecommerceapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetDataService {
    @GET("products/categories")
    Call<List<Category>> getCategories();

    @GET("products")
    Call<ProductResponse> getProducts(@Query("limit") int limit);

    @GET("products")
    Call<ProductResponse> getAllProducts(@Query("limit") int limit);

    @GET("products/{product_id}")
    Call<Product> getProductById(@Path("product_id") int productId);

    @GET("products/category/{category}")
    Call<ProductResponse> getProductsByCategory(@Path("category") String category);
}
