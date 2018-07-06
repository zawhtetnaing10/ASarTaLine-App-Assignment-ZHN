package com.zawhtetnaing.asartaline.zhn.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zawhtetnaing.asartaline.zhn.R;
import com.zawhtetnaing.asartaline.zhn.viewholders.BaseViewHolder;
import com.zawhtetnaing.asartaline.zhn.viewholders.FoodViewHolder;

public class FoodListAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder_food, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
