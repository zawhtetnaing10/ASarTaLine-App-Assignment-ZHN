package com.zawhtetnaing.asartaline.zhn.activities;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.zawhtetnaing.asartaline.zhn.R;
import com.zawhtetnaing.asartaline.zhn.adapters.FoodListAdapter;
import com.zawhtetnaing.asartaline.zhn.data.models.FoodModel;
import com.zawhtetnaing.asartaline.zhn.data.vos.WarDeeVO;
import com.zawhtetnaing.asartaline.zhn.delegates.FoodDelegate;
import com.zawhtetnaing.asartaline.zhn.events.ApiErrorEvent;
import com.zawhtetnaing.asartaline.zhn.events.SuccessGetWarDeeEvent;
import com.zawhtetnaing.asartaline.zhn.utils.FoodConstants;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListActivity extends AppCompatActivity implements FoodDelegate {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.rv_food_list)
    RecyclerView rvFoodList;

    @BindView(R.id.et_search_restaurants)
    EditText etSearchRestaurants;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.vp_empty)
    RelativeLayout vpEmpty;

    private FoodListAdapter adapter;

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

        swipeRefreshLayout.setRefreshing(true);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                FoodModel.getObjInstance().loadNewsList();
            }
        });

        adapter = new FoodListAdapter(this);
        rvFoodList.setAdapter(adapter);
        rvFoodList.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false));

        FoodModel.getObjInstance().loadNewsList();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onSuccessGetWarDee(SuccessGetWarDeeEvent event) {
        adapter.setmFoodList(event.getFoodList());
        swipeRefreshLayout.setRefreshing(false);
        vpEmpty.setVisibility(View.GONE);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onFailureGetWarDee(ApiErrorEvent event) {
        swipeRefreshLayout.setRefreshing(false);
        vpEmpty.setVisibility(View.VISIBLE);
    }

    @Override
    public void onTapFoodItem(WarDeeVO foodItem) {
        Intent intent = new Intent(this, FoodDetailsActivity.class);
        intent.putExtra(FoodConstants.WARDEE_ID_EXTRA, foodItem.getWarDeeId());
        startActivity(intent);
    }
}
