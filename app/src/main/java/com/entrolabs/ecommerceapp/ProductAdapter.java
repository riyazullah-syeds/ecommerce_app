package com.entrolabs.ecommerceapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;

    private OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(int productId);
    }

    public ProductAdapter(Context context, List<Product> productList, OnProductClickListener listener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }

    public void updateData(List<Product> products) {
        this.productList = products;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvProductTitle.setText(product.getTitle());
        holder.tvProductPrice.setText("$" + product.getPrice());
        holder.tvProductRating.setText("Rating: " + product.getRating());
        holder.tvProductDiscount.setText("Discount: " + product.getDiscount() + "%");

        // Load image using Glide
        Glide.with(context)
                .load(product.getThumbnail())
                .into(holder.ivProductImage);
        holder.itemView.setOnClickListener(v -> listener.onProductClick(product.getId()));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProductImage;
        TextView tvProductTitle, tvProductPrice, tvProductRating, tvProductDiscount;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProductImage = itemView.findViewById(R.id.ivProductImage);
            tvProductTitle = itemView.findViewById(R.id.tvProductTitle);
            tvProductPrice = itemView.findViewById(R.id.tvProductPrice);
            tvProductRating = itemView.findViewById(R.id.tvProductRating);
            tvProductDiscount = itemView.findViewById(R.id.tvProductDiscount);
        }
    }
}
