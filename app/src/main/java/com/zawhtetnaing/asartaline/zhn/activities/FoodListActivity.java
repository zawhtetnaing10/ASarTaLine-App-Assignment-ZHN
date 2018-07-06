package com.zawhtetnaing.asartaline.zhn.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;

import com.zawhtetnaing.asartaline.zhn.R;
import com.zawhtetnaing.asartaline.zhn.adapters.FoodListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListActivity extends AppCompatActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_food_list)
    RecyclerView rvFoodList;

    @BindView(R.id.et_search_restaurants)
    EditText etSearchRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);
        ButterKnife.bind(this);

        toolbar.setTitle("");
        setSupportActionBar(toolbar);


        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        FoodListAdapter adapter = new FoodListAdapter();
        rvFoodList.setAdapter(adapter);
        rvFoodList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));
    }
}
