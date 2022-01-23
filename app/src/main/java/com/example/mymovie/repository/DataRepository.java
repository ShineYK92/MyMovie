package com.example.mymovie.repository;

import static com.example.mymovie.util.Constant.GENERAL_MESSAGE;
import static com.example.mymovie.util.Constant.GENERAL_PREFERENCE;
import static com.example.mymovie.util.Constant.GENERAL_VALUE;
import static com.example.mymovie.util.Utils.loggingError;
import static com.example.mymovie.util.Utils.loggingInfo;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mymovie.api.ApiClient;
import com.example.mymovie.api.ApiListener;
import com.example.mymovie.model.DataRequest;
import com.example.mymovie.model.DataResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

public class DataRepository {

    public static int numberPage = 0;
    public static int totalResults = 0;
    public static int totalPages = 0;

    private ArrayList<DataRequest> requests = new ArrayList<>();
    private MutableLiveData<ArrayList<DataRequest>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public DataRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<ArrayList<DataRequest>> getMovieTopRateListData(String apiKey, String language, int page, String region) {
        ApiListener apiService = ApiClient.getApiService();
        Call<DataResponse> call = apiService.getMovieTopRateList(apiKey, language, page, region);
        call.enqueue(new Callback<DataResponse>() {
            @Override
            public void onResponse(Call<DataResponse> call, Response<DataResponse> response) {
                Log.i("TAG", "Test 04");

                if (response.isSuccessful()) {
                    DataResponse dataResponse = response.body();

                    numberPage = dataResponse.getPage();
                    totalResults = dataResponse.getTotalResults();
                    totalPages = dataResponse.getTotalPages();

                    Log.i("TAG", "Test 02: " + numberPage);
                    Log.i("TAG", "Test 02: " + totalResults);
                    Log.i("TAG", "Test 02: " + totalPages);

                    mutableLiveData.setValue(response.body().getResults());
                } else {
                    getResponseStatus("getSliderMutableLiveData", response.code(), response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<DataResponse> call, Throwable t) {
                loggingError("getSliderMutableLiveData", t.getMessage());
            }
        });

        return mutableLiveData;
    }

    public void getResponseStatus(String tag, int code, String message) {
        saveResponsePreference(application, Integer.toString(code), message);
        loggingInfo(tag, "Response Code: " + code + " -- Message: " + message);
    }

    public static void saveResponsePreference(Context context, String value, String message) {
        SharedPreferences generalPreference = context.getSharedPreferences(GENERAL_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = generalPreference.edit();
        editor.putString(GENERAL_VALUE, value);
        editor.putString(GENERAL_MESSAGE, message);
        editor.commit();
    }

    public static String getResponseValuePreference(Context context) {
        SharedPreferences generalPreference = context.getSharedPreferences(GENERAL_PREFERENCE, Context.MODE_PRIVATE);
        return generalPreference.getString(GENERAL_VALUE, "");
    }

    public static String getResponseMessagePreference(Context context) {
        SharedPreferences generalPreference = context.getSharedPreferences(GENERAL_PREFERENCE, Context.MODE_PRIVATE);
        return generalPreference.getString(GENERAL_MESSAGE, "");
    }
}
