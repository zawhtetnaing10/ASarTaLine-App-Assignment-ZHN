package com.zawhtetnaing.asartaline.zhn.events;

import com.zawhtetnaing.asartaline.zhn.data.vos.WarDeeVO;

import java.util.List;

public class SuccessGetWarDeeEvent {
    private List<WarDeeVO> foodList;

    public SuccessGetWarDeeEvent(List<WarDeeVO> foodList) {
        this.foodList = foodList;
    }

    public List<WarDeeVO> getFoodList() {
        return foodList;
    }
}
