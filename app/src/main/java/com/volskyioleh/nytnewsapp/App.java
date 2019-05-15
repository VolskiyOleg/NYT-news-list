package com.volskyioleh.nytnewsapp;

import android.app.Application;
import android.content.Context;

import com.volskyioleh.nytnewsapp.api.ServerCommunicator;
import com.volskyioleh.nytnewsapp.di.components.ApiComponent;
import com.volskyioleh.nytnewsapp.di.components.DaggerApiComponent;
import com.volskyioleh.nytnewsapp.di.modules.ApiModule;
import com.volskyioleh.nytnewsapp.di.modules.AppModule;

public class App extends Application {
    private static Context context;
    private ApiComponent apiComponent;

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initSystemModules();
    }


    public static Context getAppContext() {
        return App.context;
    }
    private void initSystemModules() {
        App.context = getApplicationContext();
        apiComponent = DaggerApiComponent.builder()
                .appModule(new AppModule(this))
                .apiModule(new ApiModule(ServerCommunicator.API_URL))
                .build();
    }

    public ApiComponent getApiComponent() {
        return apiComponent;
    }
}
