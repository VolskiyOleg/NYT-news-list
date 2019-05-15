package com.volskyioleh.nytnewsapp.model;

import com.google.gson.annotations.SerializedName;

public class MediaMetadata {
    @SerializedName("url")
    public String url;

    @SerializedName("format")
    public String format;

    @SerializedName("height")
    public int height;

    @SerializedName("width")
    public int width;
}
