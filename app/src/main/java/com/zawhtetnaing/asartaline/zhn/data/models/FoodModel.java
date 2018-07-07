package com.zawhtetnaing.asartaline.zhn.data.models;

import com.zawhtetnaing.asartaline.zhn.data.vos.WarDeeVO;
import com.zawhtetnaing.asartaline.zhn.events.SuccessGetWarDeeEvent;
import com.zawhtetnaing.asartaline.zhn.network.FoodDataAgent;
import com.zawhtetnaing.asartaline.zhn.network.RetrofitDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

public class FoodModel {
    private static FoodModel objInstance;
    private FoodDataAgent mDataAgent;

    private Map<String, WarDeeVO> mFoodMap;

    private static String DUMMY_ACCESS_TOKEN = "b002c7e1a528b7cb460933fc2875e916";

    private FoodModel() {
        mDataAgent = RetrofitDataAgentImpl.getObjInstance();
        EventBus.getDefault().register(this);
        mFoodMap = new HashMap<>();
    }

    public static FoodModel getObjInstance() {
        if (objInstance == null) {
            objInstance = new FoodModel();
        }
        return objInstance;
    }

    public void loadNewsList() {
        mDataAgent.loadFoodList(DUMMY_ACCESS_TOKEN);
    }

    public WarDeeVO getWarDeeById(String warDeeId) {
       return mFoodMap.get(warDeeId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onSuccessGetWarDee(SuccessGetWarDeeEvent event) {
        for (WarDeeVO foodItem : event.getFoodList()) {
            mFoodMap.put(foodItem.getWarDeeId(), foodItem);
        }
    }
}
