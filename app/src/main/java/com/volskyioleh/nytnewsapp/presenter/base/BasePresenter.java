package com.volskyioleh.nytnewsapp.presenter.base;


public interface BasePresenter<View extends BaseView> {

    void detachPresenter();

    void attachPresenter(View v);
}
