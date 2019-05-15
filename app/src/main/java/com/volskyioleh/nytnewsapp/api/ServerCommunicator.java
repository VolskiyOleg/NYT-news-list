package com.volskyioleh.nytnewsapp.api;

import android.content.Context;

import com.volskyioleh.nytnewsapp.App;
import com.volskyioleh.nytnewsapp.di.modules.AppApiModule;
import com.volskyioleh.nytnewsapp.model.Article;
import com.volskyioleh.nytnewsapp.model.Response;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ServerCommunicator {
    public static final String API_URL = "https://api.nytimes.com/svc/mostpopular/v2/";
    public static final String KEY = "OscffFQeaJg6Z23ouzwOvfl02RiNo0Ay";

    private Retrofit mRetrofit;
    private AppApiModule.AppApiService mService;
    private Context mContext;

    public ServerCommunicator(AppApiModule.AppApiService appApiService, Context context) {
        mService = appApiService;
        mContext = context;
    }

    private AppApiModule.AppApiService getService() {
        if (mService == null) {
            mService = getRetrofit().create(AppApiModule.AppApiService.class);
        }
        return mService;
    }

    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return mRetrofit;
    }


    public Observable<Response> buildGetArticlesObservable() {
        return    getService().getMostEmailedArticles(KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response> buildGetSharedArticlesObservable() {
        return    getService().getMostSharedArticles(KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<Response> buildGetViewedArticlesObservable() {
        return    getService().getMostViewediArticles(KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
