package com.entrolabs.ecommerceapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryProductsActivity extends AppCompatActivity {
    private RecyclerView rvCategoryProducts;
    private ProductAdapter productAdapter;
    private List<Product> categoryProductList = new ArrayList<>();
    private GetDataService service;
    private String categorySlug;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_products);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        categorySlug = getIntent().getStringExtra("category_slug");

        rvCategoryProducts = findViewById(R.id.rvCategoryProducts);
        rvCategoryProducts.setLayoutManager(new GridLayoutManager(this, 2));
        productAdapter = new ProductAdapter(this, categoryProductList, productId -> {
            // Handle product click
            Intent intent = new Intent(CategoryProductsActivity.this, ProductDetailActivity.class);
            intent.putExtra("product_id", productId);
            startActivity(intent);
        });
        rvCategoryProducts.setAdapter(productAdapter);

        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        loadCategoryProducts();

    }

    private void loadCategoryProducts() {
        Call<ProductResponse> call = service.getProductsByCategory(categorySlug);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    categoryProductList.addAll(response.body().getProducts());
                    productAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }


}