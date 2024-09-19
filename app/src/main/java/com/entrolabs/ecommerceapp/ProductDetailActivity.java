package com.entrolabs.ecommerceapp;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailActivity extends AppCompatActivity {
    private ImageView ivProductImage;
    private TextView tvProductTitle, tvProductDescription, tvProductPrice, tvProductRating, tvProductBrand, tvShippingInfo, tvWarrantyInfo, tvStockAvailability;
    private RecyclerView rvReviews;
    private GetDataService service;
    private int productId;
    private List<Review> reviewList = new ArrayList<>();
   private ReviewAdapter reviewAdapter;

   Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        ivProductImage = findViewById(R.id.ivProductImage);
        tvProductTitle = findViewById(R.id.tvProductTitle);
        tvProductDescription = findViewById(R.id.tvProductDescription);
        tvProductPrice = findViewById(R.id.tvProductPrice);
        tvProductRating = findViewById(R.id.tvProductRating);
        tvProductBrand = findViewById(R.id.tvProductBrand);
        tvShippingInfo = findViewById(R.id.tvShippingInfo);
        tvWarrantyInfo = findViewById(R.id.tvWarrantyInfo);
        tvStockAvailability = findViewById(R.id.tvStockAvailability);
        rvReviews = findViewById(R.id.rvReviews);

        productId = getIntent().getIntExtra("product_id", -1);

        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        loadProductDetails();
    }

    private void loadProductDetails() {
        Call<Product> call = service.getProductById(productId);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Product product = response.body();
                    displayProductDetails(product);
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void displayProductDetails(Product product) {
        tvProductTitle.setText(product.getTitle());
        tvProductDescription.setText(product.getDescription());
        tvProductPrice.setText("$" + product.getPrice());
        tvProductRating.setText("Rating: " + product.getRating());
        tvProductBrand.setText("Brand: " + product.getBrand());
        tvShippingInfo.setText("Shipping Info: " + product.getShippingInformation());
        tvWarrantyInfo.setText("Warranty: " + product.getWarrantyInformation());
        tvStockAvailability.setText("Availability: " + product.getAvailabilityStatus());

        Glide.with(this)
                .load(product.getThumbnail())
                .into(ivProductImage);

        reviewList = product.getReviews();
        rvReviews.setLayoutManager(new LinearLayoutManager(this));
        reviewAdapter = new ReviewAdapter(this, reviewList);
        rvReviews.setAdapter(reviewAdapter);


    }
}