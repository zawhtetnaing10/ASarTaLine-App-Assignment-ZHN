package com.zawhtetnaing.asartaline.zhn.network;

import android.util.EventLog;

import com.zawhtetnaing.asartaline.zhn.events.ApiErrorEvent;
import com.zawhtetnaing.asartaline.zhn.events.SuccessGetWarDeeEvent;
import com.zawhtetnaing.asartaline.zhn.network.responses.GetWarDeeResponse;
import com.zawhtetnaing.asartaline.zhn.utils.FoodConstants;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDataAgentImpl implements FoodDataAgent {

    private static RetrofitDataAgentImpl objInstance;

    private FoodApi mTheApi;

    private RetrofitDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FoodConstants.API_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        mTheApi = retrofit.create(FoodApi.class);
    }

    public static RetrofitDataAgentImpl getObjInstance() {
        if (objInstance == null) {
            objInstance = new RetrofitDataAgentImpl();
        }
        return objInstance;
    }


    @Override
    public void loadFoodList(final String accessToken) {
        Call<GetWarDeeResponse> callFoodList = mTheApi.loadFoodList(accessToken);
        callFoodList.enqueue(new Callback<GetWarDeeResponse>() {
            @Override
            public void onResponse(Call<GetWarDeeResponse> call, Response<GetWarDeeResponse> response) {
                GetWarDeeResponse warDeeResponse = response.body();
                if (warDeeResponse != null && warDeeResponse.isResponseOk()) {
                    SuccessGetWarDeeEvent event = new SuccessGetWarDeeEvent(warDeeResponse.getAstlWarDee());
                    EventBus.getDefault().post(event);
                } else {
                    if (warDeeResponse == null) {
                        ApiErrorEvent event = new ApiErrorEvent("There is no response from network call");
                        EventBus.getDefault().post(event);
                    } else {
                        ApiErrorEvent event = new ApiErrorEvent(warDeeResponse.getMessage());
                        EventBus.getDefault().post(event);
                    }
                }
            }

            @Override
            public void onFailure(Call<GetWarDeeResponse> call, Throwable t) {
                ApiErrorEvent event = new ApiErrorEvent(t.getMessage());
                EventBus.getDefault().post(event);
            }
        });
    }
}
