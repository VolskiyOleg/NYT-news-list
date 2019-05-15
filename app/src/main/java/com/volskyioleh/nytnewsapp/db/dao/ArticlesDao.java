package com.volskyioleh.nytnewsapp.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.volskyioleh.nytnewsapp.db.entities.ArticleModel;
import com.volskyioleh.nytnewsapp.model.Article;

import java.util.List;

@Dao
public interface ArticlesDao {


    @Query("SELECT * FROM ArticleModel WHERE type = :type ORDER BY date DESC ")
    LiveData<List<ArticleModel>> getAllByType(int type);

    @Query("SELECT * FROM ArticleModel WHERE isFavorite = :status ORDER BY date DESC ")
    LiveData<List<ArticleModel>> getFavoriteItems(boolean status);
//
//        @Query("SELECT * FROM Product")
//        LiveData<List<Product>> findAll();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(List<ArticleModel> articleModels);

    @Query("UPDATE ArticleModel  SET isFavorite=:status WHERE id = :id")
    void setFavoriteArticle(long id, boolean status);

    @Delete
    int delete(ArticleModel articleModel);

}
