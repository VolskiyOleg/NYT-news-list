package com.volskyioleh.nytnewsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Response {
    @SerializedName("status")
    public String status;

    @SerializedName("copyright")
    public String copyright;


        @SerializedName("num_results")
    public String  numResults;

    @SerializedName("results")
    public List<Article> results;
}
//"copyright": "Copyright (c) 2019 The New York Times Company.  All Rights Reserved.",
//        "num_results": 897,
//        "results":