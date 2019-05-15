package com.volskyioleh.nytnewsapp.di.modules;

import android.content.Context;

import com.volskyioleh.nytnewsapp.App;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private App mApp;

    public AppModule(App app) {
        mApp = app;
    }

    @Provides
    @Singleton
    public App provideApp() {
        return mApp;
    }

    @Provides
    @Singleton
    public Context provideContext() {
        return mApp;
    }

}
