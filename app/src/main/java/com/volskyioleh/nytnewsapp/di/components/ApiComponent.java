package com.volskyioleh.nytnewsapp.di.components;

import android.content.Context;

import com.volskyioleh.nytnewsapp.di.modules.ApiModule;
import com.volskyioleh.nytnewsapp.di.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;


@Singleton
@Component(modules = {AppModule.class, ApiModule.class})
public interface ApiComponent {

    // required for AppApiModule within CommonComponent
    Retrofit retrofit();

    Context context();

}
