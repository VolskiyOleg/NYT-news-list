package com.volskyioleh.nytnewsapp.db.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class ArticleModel {
    @PrimaryKey
    private long id;
    private String url;
    private String title;
    private String subTitle;
    private String imageUrl;
    private int type; // 0 - emailed, 1- shared,  2- viewed

    public ArticleModel(long id, String url, String title, String subTitle, String imageUrl, int type, String author, Long date, boolean isFavorite) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.subTitle = subTitle;
        this.imageUrl = imageUrl;
        this.type = type;
        this.author = author;
        this.date = date;
        this.isFavorite = isFavorite;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }

    private String author;
    private Long date;
    private boolean isFavorite;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
