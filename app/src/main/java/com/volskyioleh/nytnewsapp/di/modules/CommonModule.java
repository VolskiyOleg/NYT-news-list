package com.volskyioleh.nytnewsapp.di.modules;

import com.volskyioleh.nytnewsapp.api.ServerCommunicator;
import com.volskyioleh.nytnewsapp.di.CommonScope;
import com.volskyioleh.nytnewsapp.presenter.favorite.FavoriteContract;
import com.volskyioleh.nytnewsapp.presenter.favorite.FavoritePresenter;
import com.volskyioleh.nytnewsapp.presenter.most_emailes.MosetEmailedContract;
import com.volskyioleh.nytnewsapp.presenter.most_emailes.MostEmailedPresenter;
import com.volskyioleh.nytnewsapp.presenter.most_shared.MostSharedContract;
import com.volskyioleh.nytnewsapp.presenter.most_shared.MostSharedPresenter;
import com.volskyioleh.nytnewsapp.presenter.most_viewed.MostViewedContract;
import com.volskyioleh.nytnewsapp.presenter.most_viewed.MostViewedPresenter;

import dagger.Module;
import dagger.Provides;

@Module
public class CommonModule {

    @Provides
    @CommonScope
    public MosetEmailedContract.Presenter provideSplashPresenter(
            ServerCommunicator serverCommunicator) {
        return new MostEmailedPresenter(serverCommunicator);
    }

    @Provides
    @CommonScope
    public MostSharedContract.Presenter provideSharedPresenter(
            ServerCommunicator serverCommunicator) {
        return new MostSharedPresenter(serverCommunicator);
    }

    @Provides
    @CommonScope
    public MostViewedContract.Presenter provideViewedPresenter(
            ServerCommunicator serverCommunicator) {
        return new MostViewedPresenter(serverCommunicator);
    }  @Provides


    @CommonScope
    public FavoriteContract.Presenter provideFavoritePresenter(
            ServerCommunicator serverCommunicator) {
        return new FavoritePresenter(serverCommunicator);
    }

}
