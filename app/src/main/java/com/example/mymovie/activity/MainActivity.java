package com.example.mymovie.activity;

import static com.example.mymovie.util.Utils.languagePattern;
import static com.example.mymovie.util.Utils.loggingInfo;
import static com.example.mymovie.util.Utils.pageAuth;
import static com.example.mymovie.util.Utils.regionPattern;

import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.mymovie.R;
import com.example.mymovie.adapter.MovieAdapter;
import com.example.mymovie.base.BaseActivity;
import com.example.mymovie.model.DataRequest;
import com.example.mymovie.view.ViewModel;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefresh;
    private ViewModel model;
    private MovieAdapter adapter;

    int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupLayout();
        setupListener();

        getMovieTopRateList(apiKeyV3, getLanguageCode(), page, getCountryCode());
    }

    private void setupLayout() {
        swipeRefresh = findViewById(R.id.swipeRefresh);
        recyclerView = findViewById(R.id.recyclerView);

        model = ViewModelProviders.of(this).get(ViewModel.class);
    }

    private void setupListener() {
        swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        getMovieTopRateList(apiKeyV3, getLanguageCode(), page, getCountryCode());
    }

    private void getMovieTopRateList(String apiKey, String language, int page, String region) {
        swipeRefresh.setRefreshing(true);
        setupToast("Loading...");

        if (language.matches(languagePattern) && pageAuth(page) && region.matches(regionPattern)) {
            setupToast("Output Loading...");

            model.getMovieTopRateList(apiKey, language, page, region).observe(this, dataRequests -> {
                if (dataRequests == null) {
                    setupToast("No Output");
                } else {
                    swipeRefresh.setRefreshing(false);
                    setRecyclerView(dataRequests);
                }
            });
        } else {
            setupToast("Invalid Input");
        }
    }

    private void setRecyclerView(ArrayList<DataRequest> dataRequests) {
        adapter = new MovieAdapter(this, dataRequests);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}