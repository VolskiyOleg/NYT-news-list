package com.volskyioleh.nytnewsapp.presenter.most_viewed;


import android.arch.lifecycle.LiveData;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.volskyioleh.nytnewsapp.App;
import com.volskyioleh.nytnewsapp.R;
import com.volskyioleh.nytnewsapp.db.entities.ArticleModel;
import com.volskyioleh.nytnewsapp.di.components.ApiComponent;
import com.volskyioleh.nytnewsapp.di.components.DaggerCommonComponent;
import com.volskyioleh.nytnewsapp.di.modules.AppApiModule;
import com.volskyioleh.nytnewsapp.di.modules.CommonModule;
import com.volskyioleh.nytnewsapp.presenter.adapters.ArticlesAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MostViewedFragment extends Fragment  implements
        MostViewedContract.View, ArticlesAdapter.AdapterCallback {

    @Inject
    MostViewedContract.Presenter mPresenter;
    @BindView(R.id.recyclerViewViewed)
    RecyclerView recyclerView;
    private ArticlesAdapter mAdapter;
    private List<ArticleModel> modelList;

    public MostViewedFragment() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInjectDependencies(App.get(getContext()).getApiComponent());
    }

    public void onInjectDependencies(ApiComponent apiComponent) {
        //     super.onInjectDependencies(apiComponent);
        DaggerCommonComponent.builder()
                .apiComponent(apiComponent)
                .commonModule(new CommonModule())
                .appApiModule(new AppApiModule())
                .build().inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_most_viewed , container, false);
        ButterKnife.bind(this, view);
        mPresenter.attachPresenter(this);
        mPresenter.getMostViewedArticlesList();
        modelList = new ArrayList<>();
        mAdapter = new ArticlesAdapter(new ArrayList<>(), getContext(), this::addToFavorite);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(mAdapter);
        return view;
    }

    @Override
    public void showList(LiveData<List<ArticleModel>> articles) {
        articles.observe(this,entries -> {
            mAdapter.setmArticles(entries);
            modelList = entries;
        });
    }


    @Override
    public void addToFavorite(int position, long id) {
        mPresenter.addToFavorite(id);
        Toast.makeText(getContext(),"Article was added to favorite", Toast.LENGTH_LONG).show();
    }

    @Override
    public void handleInternetDisabled() {

    }
}
