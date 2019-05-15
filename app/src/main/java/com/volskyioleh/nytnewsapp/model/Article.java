package com.volskyioleh.nytnewsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Article {
    @SerializedName("url")
    public String url;

    @SerializedName("adx_keywords")
    public String adxKeywords;

    @SerializedName("subsection")
    public String subsection;

    @SerializedName("count_type")
    public String countType;

    @SerializedName("column")
    public String column;

    @SerializedName("eta_id")
    public long etaId;

    @SerializedName("section")
    public String section;

    @SerializedName("id")
    public long id;

    @SerializedName("asset_id")
    public long asset_id;

    @SerializedName("nytdsection")
    public String nytdsection;

    @SerializedName("byline")
    public String byline;

    @SerializedName("type")
    public String type;

    @SerializedName("title")
    public String title;

    @SerializedName("abstract")
    public String abstractText;

    @SerializedName("published_date")
    public String published_date;

    @SerializedName("source")
    public String source;

    @SerializedName("updated")
    public String updated;

    @SerializedName("des_facet")
    public List<String> des_facet;

//    @SerializedName("org_facet")
//    public String org_facet;

    @SerializedName("media")
    public List<Media> media;


}
