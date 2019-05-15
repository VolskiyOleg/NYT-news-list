package com.volskyioleh.nytnewsapp.presenter.most_emailes;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.volskyioleh.nytnewsapp.App;
import com.volskyioleh.nytnewsapp.db.ArticlesDB;
import com.volskyioleh.nytnewsapp.db.entities.ArticleModel;
import com.volskyioleh.nytnewsapp.model.Article;
import com.volskyioleh.nytnewsapp.model.Response;
import com.volskyioleh.nytnewsapp.presenter.base.BasePresenterAbs;
import com.volskyioleh.nytnewsapp.presenter.base.BasePresenter;
import com.volskyioleh.nytnewsapp.api.ServerCommunicator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import rx.Completable;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action;
import rx.schedulers.Schedulers;

public class MostEmailedPresenter extends BasePresenterAbs<MosetEmailedContract.View>
        implements MosetEmailedContract.Presenter {

    public MostEmailedPresenter(ServerCommunicator serverCommunicator) {
        super(serverCommunicator);
    }

    @Override
    public void onDestroyView() {

    }

    @Override
    public void getMostEmailedArticlesList() {

        getServerCommunicator().buildGetArticlesObservable()
                .subscribe(response -> {
                            insertArticles(response);
                           getView().showList(getAllItems(0));
                        },
                        Throwable::printStackTrace);
    }

    private void insertArticles(Response response) {
        List<ArticleModel> articleModels = new ArrayList<>();
        for (int i = 0; i < response.results.size(); i++) {
            Article article = response.results.get(i);
            articleModels.add(new ArticleModel(article.id, article.url, article.title, article.abstractText, article.media.get(0).mediaMetadata.get(article.media.get(0).mediaMetadata.size() - 1).url, 0, article.byline, getDateMillis(article.published_date), false));
        }

        Completable.fromAction(() ->ArticlesDB.getDatabase(App.getAppContext()).articlesDao().insert(articleModels)) .subscribeOn(Schedulers.io())
                .subscribe();
    }

    private long getDateMillis(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date mDate = sdf.parse(date);
            return mDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public LiveData<List<ArticleModel>> getAllItems(int type) {
        return ArticlesDB.getDatabase(App.getAppContext()).articlesDao().getAllByType(type);
    }

    @Override
    public void addToFavorite(long id){
        Completable.fromAction(() ->ArticlesDB.getDatabase(App.getAppContext()).articlesDao().setFavoriteArticle(id, true)) .subscribeOn(Schedulers.io())
                .subscribe();
    }
}
