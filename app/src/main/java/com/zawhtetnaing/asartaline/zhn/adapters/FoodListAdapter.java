package com.zawhtetnaing.asartaline.zhn.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zawhtetnaing.asartaline.zhn.R;
import com.zawhtetnaing.asartaline.zhn.data.vos.WarDeeVO;
import com.zawhtetnaing.asartaline.zhn.delegates.FoodDelegate;
import com.zawhtetnaing.asartaline.zhn.viewholders.BaseViewHolder;
import com.zawhtetnaing.asartaline.zhn.viewholders.FoodViewHolder;
import com.zawhtetnaing.asartaline.zhn.viewholders.OfferViewHolder;

import java.util.ArrayList;
import java.util.List;

public class FoodListAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VT_OFFER = 1000;
    public static final int VT_FOOD_LIST = 2000;

    private FoodDelegate mFoodDelegate;

    private List<WarDeeVO> mFoodList;

    public FoodListAdapter(FoodDelegate foodDelegate) {
        this.mFoodDelegate = foodDelegate;
        mFoodList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VT_OFFER) {
            View view = inflater.inflate(R.layout.view_holder_offer, parent, false);
            return new OfferViewHolder(view);
        } else if (viewType == VT_FOOD_LIST) {
            View view = inflater.inflate(R.layout.view_holder_food, parent, false);
            return new FoodViewHolder(view, mFoodDelegate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.bindData(mFoodList.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return VT_OFFER;
        } else {
            return VT_FOOD_LIST;
        }
    }

    @Override
    public int getItemCount() {
        return mFoodList.size();
    }

    public void setmFoodList(List<WarDeeVO> mFoodList) {
        this.mFoodList = mFoodList;
        notifyDataSetChanged();
    }
}
