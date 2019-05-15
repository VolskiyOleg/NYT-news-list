package com.volskyioleh.nytnewsapp.di.components;

import com.volskyioleh.nytnewsapp.di.CommonScope;
import com.volskyioleh.nytnewsapp.di.modules.AppApiModule;
import com.volskyioleh.nytnewsapp.di.modules.CommonModule;
import com.volskyioleh.nytnewsapp.presenter.favorite.FavoriteListActivity;
import com.volskyioleh.nytnewsapp.presenter.most_emailes.MostEmailedFragment;
import com.volskyioleh.nytnewsapp.presenter.most_shared.MostSharedFragment;
import com.volskyioleh.nytnewsapp.presenter.most_viewed.MostViewedFragment;

import dagger.Component;


@CommonScope
@Component(modules = {CommonModule.class, AppApiModule.class},
        dependencies = {ApiComponent.class})
public interface CommonComponent {

    void inject(MostEmailedFragment fragment);
    void inject(MostSharedFragment fragment);
    void inject(MostViewedFragment fragment);
    void inject(FavoriteListActivity activity);

}
