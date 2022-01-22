package com.example.mymovie.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataResponse {

    @SerializedName("page")
    private int page;

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;

    @SerializedName("results")
    private ArrayList<DataRequest> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public ArrayList<DataRequest> getResults() {
        return results;
    }

    public void setResults(ArrayList<DataRequest> results) {
        this.results = results;
    }
}
