package com.anpn.shop.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anpn.shop.MainActivity;
import com.anpn.shop.Product;
import com.anpn.shop.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private final LayoutInflater inflater;
    private final List<Product> products;

    public MyAdapter(MainActivity context, List<Product> products) {
        this.products = products;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.pictureView.setImageResource(product.getPicture());
        holder.nameView.setText(product.getName());
        holder.descriptionView.setText(product.getDescription());
        holder.priceView.setText(product.getPrice());

    }

    @Override
    public int getItemCount() {
        return products.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView pictureView;
        final TextView nameView, descriptionView, priceView;

        ViewHolder(View view) {
            super(view);
            pictureView = view.findViewById(R.id.pictureImage);
            nameView = view.findViewById(R.id.tvTitle);
            descriptionView = view.findViewById(R.id.tvDescription);
            priceView = view.findViewById(R.id.tvPrice);
        }
    }
}
