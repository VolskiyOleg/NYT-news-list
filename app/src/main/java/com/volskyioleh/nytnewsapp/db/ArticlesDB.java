package com.volskyioleh.nytnewsapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.volskyioleh.nytnewsapp.db.dao.ArticlesDao;
import com.volskyioleh.nytnewsapp.db.entities.ArticleModel;

@Database(entities = { ArticleModel.class}, version = ArticlesDB.VERSION)
public abstract class ArticlesDB extends RoomDatabase {

    static final int VERSION = 1;
    private static ArticlesDB mInstance;
    public static ArticlesDB getDatabase(Context context) {
        if (mInstance == null) {
            mInstance = Room.databaseBuilder(context.getApplicationContext(), ArticlesDB.class, "articles_db")
                    .build();
        }
        return mInstance;
    }

        public static void destroyInstances() {
        mInstance = null;
    }

    public abstract ArticlesDao articlesDao();

}
//    private static ItemsDB mInstance;


