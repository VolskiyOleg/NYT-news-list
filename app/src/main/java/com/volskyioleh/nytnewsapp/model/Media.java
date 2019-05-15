package com.volskyioleh.nytnewsapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Media {
    @SerializedName("type")
    public String type;

    @SerializedName("subtype")
    public String subtype;

    @SerializedName("caption")
    public String caption;

    @SerializedName("copyright")
    public String copyright;

    @SerializedName("approved_for_syndication")
    public int approvedForSyndication;

    @SerializedName("media-metadata")
    public List<MediaMetadata> mediaMetadata;
}
