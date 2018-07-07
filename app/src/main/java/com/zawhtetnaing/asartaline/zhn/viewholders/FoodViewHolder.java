package com.zawhtetnaing.asartaline.zhn.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zawhtetnaing.asartaline.zhn.R;
import com.zawhtetnaing.asartaline.zhn.data.vos.WarDeeVO;
import com.zawhtetnaing.asartaline.zhn.delegates.FoodDelegate;
import com.zawhtetnaing.asartaline.zhn.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodViewHolder extends BaseViewHolder {

    @BindView(R.id.iv_food_image)
    ImageView ivFoodImage;

    @BindView(R.id.tv_food_name)
    TextView tvFoodName;

    @BindView(R.id.tv_food_taste)
    TextView tvFoodTaste;

    @BindView(R.id.tv_food_suited_for)
    TextView tvFoodSuitedFor;

    private FoodDelegate mFoodDelegate;
    private WarDeeVO foodItem;

    public FoodViewHolder(View itemView, FoodDelegate foodDelegate) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.mFoodDelegate = foodDelegate;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFoodDelegate.onTapFoodItem(foodItem);
            }
        });
    }

    @Override
    public void bindData(WarDeeVO foodItem) {
        this.foodItem = foodItem;

        GlideApp.with(ivFoodImage.getContext())
                .load(foodItem.getImages().get(1))
                .into(ivFoodImage);

        tvFoodName.setText(foodItem.getName());

        tvFoodTaste.setText(foodItem.getGeneralTaste()
                .get(0)
                .getTaste());

        tvFoodSuitedFor.setText(foodItem.getSuitedFor()
                .get(0)
                .getSuitedFor());
    }
}
