package com.volskyioleh.nytnewsapp.presenter.favorite;

import com.volskyioleh.nytnewsapp.App;
import com.volskyioleh.nytnewsapp.api.ServerCommunicator;
import com.volskyioleh.nytnewsapp.db.ArticlesDB;
import com.volskyioleh.nytnewsapp.presenter.base.BasePresenterAbs;

public class FavoritePresenter extends BasePresenterAbs<FavoriteContract.View>
        implements FavoriteContract.Presenter {

    public FavoritePresenter(ServerCommunicator serverCommunicator) {
        super(serverCommunicator);
    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void getFavoriteList() {
        getView().showList(ArticlesDB.getDatabase(App.getAppContext()).articlesDao().getFavoriteItems(true));
    }
}
