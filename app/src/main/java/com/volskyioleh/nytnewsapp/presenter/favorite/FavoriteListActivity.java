package com.volskyioleh.nytnewsapp.presenter.favorite;

import android.arch.lifecycle.LiveData;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

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
import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FavoriteListActivity extends AppCompatActivity implements FavoriteContract.View {


    @Inject
    FavoriteContract.Presenter mPresenter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerViewFavorite)
    RecyclerView recyclerView;
    private ArticlesAdapter mAdapter;

    private List<ArticleModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_list);
        ButterKnife.bind(this);
        modelList = new ArrayList<>();
        onInjectDependencies(App.get(this).getApiComponent());
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        mPresenter.attachPresenter(this);
        mPresenter.getFavoriteList();
        mAdapter = new ArticlesAdapter(new ArrayList<>(), this, null);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(mAdapter);

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
    public void showList(LiveData<List<ArticleModel>> articles) {
        articles.observe(this, entries -> {
            mAdapter.setmArticles(entries);
            modelList = entries;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    @Override
    public void handleInternetDisabled() {

    }
}
