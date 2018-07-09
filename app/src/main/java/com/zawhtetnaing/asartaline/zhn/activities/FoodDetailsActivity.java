package com.zawhtetnaing.asartaline.zhn.activities;

import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zawhtetnaing.asartaline.zhn.R;
import com.zawhtetnaing.asartaline.zhn.data.models.FoodModel;
import com.zawhtetnaing.asartaline.zhn.data.vos.WarDeeVO;
import com.zawhtetnaing.asartaline.zhn.utils.FoodConstants;
import com.zawhtetnaing.asartaline.zhn.utils.GlideApp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodDetailsActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.iv_foodDetails_backdrop)
    ImageView ivFoodDetailsBackDrop;

    @BindView(R.id.tv_food_details_food_name)
    TextView tvFoodDetailsFoodName;

    @BindView(R.id.tv_food_details_price)
    TextView tvfoodDetailsPrice;

    @BindView(R.id.tv_food_taste_description)
    TextView tvFoodTasteDescription;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    @BindView(R.id.vp_empty)
    RelativeLayout vpEmpty;

    private WarDeeVO mFoodItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);
        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);

        String warDeeIdExtra = getIntent().getStringExtra(FoodConstants.WARDEE_ID_EXTRA);
        mFoodItem = FoodModel.getObjInstance().getWarDeeById(warDeeIdExtra);
        if (mFoodItem == null) {
            coordinatorLayout.setVisibility(View.GONE);
            vpEmpty.setVisibility(View.VISIBLE);
        } else {
            bindData(mFoodItem);
            vpEmpty.setVisibility(View.GONE);
        }

    }

    public void bindData(WarDeeVO foodItem) {
        GlideApp.with(ivFoodDetailsBackDrop.getContext())
                .load(foodItem.getImages().get(0))
                .into(ivFoodDetailsBackDrop);

        tvFoodDetailsFoodName.setText(foodItem.getName());

        tvfoodDetailsPrice.setText(foodItem.getPriceRangeMin() + " - " + foodItem.getPriceRangeMax());

        tvFoodTasteDescription.setText(foodItem.getGeneralTaste()
                .get(0)
                .getTasteDesc());
    }
}
