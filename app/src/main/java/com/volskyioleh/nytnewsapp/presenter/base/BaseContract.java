package com.volskyioleh.nytnewsapp.presenter.base;

public interface BaseContract {

        interface View extends BaseView {
//            @Override
//            void showCommonLoader();
//
//            @Override
//            void hideCommonLoader();
        }

        interface Presenter<V extends View> extends BasePresenter<V> {
            void onDestroyView();
        }

}
