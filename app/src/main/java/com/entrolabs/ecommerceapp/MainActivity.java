package com.entrolabs.ecommerceapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategories;
    private CategoryAdapter categoryAdapter;
    private List<Category> categoryList = new ArrayList<>();
    private GetDataService service;

    private RecyclerView rvProducts;
    private ProductAdapter productAdapter;
    private List<Product> productList = new ArrayList<>();

    private EditText etSearch;
    private List<Product> allProducts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvCategories = findViewById(R.id.rvCategories);
        rvCategories.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        categoryAdapter = new CategoryAdapter(this, categoryList
                , categorySlug -> {
            // Handle category click
            Intent intent = new Intent(MainActivity.this, CategoryProductsActivity.class);
            intent.putExtra("category_slug", categorySlug);
            startActivity(intent);
        });
        rvCategories.setAdapter(categoryAdapter);
        service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        loadCategories();

        rvProducts = findViewById(R.id.rvProducts);
        rvProducts.setLayoutManager(new GridLayoutManager(this, 2));
        productAdapter = new ProductAdapter(this, productList, productId -> {
            // Handle product click
            Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
            intent.putExtra("product_id", productId);
            startActivity(intent);
        });
        rvProducts.setAdapter(productAdapter);

        loadProducts();

        etSearch = findViewById(R.id.etSearch);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchTerm = s.toString();
                if (searchTerm.length() >= 3) {
                    filterProducts(searchTerm);
                } else {
                    productAdapter.updateData(productList);
                }

            }
        });
    }

    private void loadAllProducts() {
        Call<ProductResponse> call = service.getAllProducts(200);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    allProducts.addAll(response.body().getProducts());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                // Handle failure
            }
        });
    }

    private void filterProducts(String searchTerm) {
        List<Product> filteredProducts = new ArrayList<>();
        for (Product product : allProducts) {
            if (product.getTitle().toLowerCase().contains(searchTerm.toLowerCase())) {
                filteredProducts.add(product);
            }
        }
        productAdapter.updateData(filteredProducts);
    }


    private void loadCategories() {
        Call<List<Category>> call = service.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                if (response.isSuccessful()) {
                    categoryList.addAll(response.body());
                    categoryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                // Handle failure
            }
        });

    }

    private void loadProducts() {
        Call<ProductResponse> call = service.getProducts(20);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    productList.addAll(response.body().getProducts());
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