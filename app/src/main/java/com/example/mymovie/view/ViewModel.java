package com.example.mymovie.view;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mymovie.model.DataRequest;
import com.example.mymovie.repository.DataRepository;

import java.util.ArrayList;

public class ViewModel extends AndroidViewModel {

    private DataRepository repository;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new DataRepository(application);
    }

    public LiveData<ArrayList<DataRequest>> getMovieTopRateList(String apiKey, String language, int page, String region) {
        return repository.getMovieTopRateListData(apiKey, language, page, region);
    }
}
