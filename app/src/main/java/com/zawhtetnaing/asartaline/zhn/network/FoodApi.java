package com.zawhtetnaing.asartaline.zhn.network;

import com.zawhtetnaing.asartaline.zhn.data.vos.WarDeeVO;
import com.zawhtetnaing.asartaline.zhn.network.responses.GetWarDeeResponse;
import com.zawhtetnaing.asartaline.zhn.utils.FoodConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FoodApi {
    @FormUrlEncoded
    @POST(FoodConstants.GET_WARDEE)
    Call<GetWarDeeResponse> loadFoodList(@Field(FoodConstants.PARAM_ACCESS_TOKEN) String accessToken);
}
