package com.volskyioleh.nytnewsapp.di.modules;


import android.content.Context;

import com.volskyioleh.nytnewsapp.api.ServerCommunicator;
import com.volskyioleh.nytnewsapp.db.dao.ArticlesDao;
import com.volskyioleh.nytnewsapp.di.CommonScope;
import com.volskyioleh.nytnewsapp.model.Article;
import com.volskyioleh.nytnewsapp.model.Response;

import java.util.List;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

@Module
public class AppApiModule {

    @Provides
    @CommonScope
    public AppApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(AppApiService.class);
    }

    @Provides
    @CommonScope
    public ServerCommunicator provideServerCommunicator(AppApiService appApiService,
                                                        Context context) {
        return new ServerCommunicator(appApiService, context);
    }

    public interface AppApiService {

        @GET("emailed/30.json")
        Observable<Response> getMostEmailedArticles(
                @Query("api-key") String key
        );


        @GET("shared/30.json")
        Observable<Response> getMostSharedArticles(
                @Query("api-key") String key
        );

        @GET("viewed/30.json")
        Observable<Response> getMostViewediArticles(
                @Query("api-key") String key
        );

    }
}