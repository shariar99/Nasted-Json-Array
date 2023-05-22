package com.example.nastedjsonarray;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class YourAdapter extends RecyclerView.Adapter<YourAdapter.ViewHolder> {
    private List<Topping> toppings;
    private List<YourResponseModel> yourResponseModelList;

    public YourAdapter(List<Topping> toppings) {
        this.toppings = toppings;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView batterTypeTextView;

        public ViewHolder(View itemView) {
            super(itemView);
            batterTypeTextView = itemView.findViewById(R.id.batterTypeTextView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.design, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Topping topping = toppings.get(position);
        holder.batterTypeTextView.setText(topping.getType());

    }

    @Override
    public int getItemCount() {
        return toppings.size();
    }
}
