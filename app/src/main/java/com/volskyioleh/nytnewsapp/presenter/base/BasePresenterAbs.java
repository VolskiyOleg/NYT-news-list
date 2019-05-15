package com.volskyioleh.nytnewsapp.presenter.base;


import com.volskyioleh.nytnewsapp.api.ServerCommunicator;

import java.lang.ref.WeakReference;

public abstract class BasePresenterAbs<View extends BaseView>
        implements BasePresenter<View> {

    private WeakReference<View> mView;

    private ServerCommunicator serverCommunicator;


    public BasePresenterAbs(ServerCommunicator serverCommunicator) {
        this.serverCommunicator = serverCommunicator;
    }

    @Override
    public void attachPresenter(View v) {
        mView = new WeakReference<>(v);
    }

    public void detachPresenter() {
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }

    protected View getView() {
        return mView != null && mView.get() != null ? mView.get() : null;
    }


    public ServerCommunicator getServerCommunicator() {
        return serverCommunicator;
    }

    public void setServerCommunicator(ServerCommunicator serverCommunicator) {
        this.serverCommunicator = serverCommunicator;
    }

}
