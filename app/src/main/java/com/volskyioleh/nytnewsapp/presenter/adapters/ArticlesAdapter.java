package com.volskyioleh.nytnewsapp.presenter.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.volskyioleh.nytnewsapp.R;
import com.volskyioleh.nytnewsapp.db.entities.ArticleModel;
import com.volskyioleh.nytnewsapp.presenter.DetailsWebViewActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ArticlesAdapter extends RecyclerView.Adapter<ArticlesAdapter.ArticlesHolder> {

    private final Context mContext;
    private List<ArticleModel> mArticles;
    private AdapterCallback mAdapterCallback;


    public ArticlesAdapter(List<ArticleModel> itemsList, Context context, AdapterCallback callback) {
        mArticles = itemsList;
        mContext = context;
        this.mAdapterCallback = callback;
    }

    @NonNull
    @Override
    public ArticlesHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.article_item, null);
        return new ArticlesHolder(view);
    }

    public void setmArticles(List<ArticleModel> itemsList) {
        mArticles = itemsList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ArticlesHolder articlesHolder, int position) {
        ArticleModel model = mArticles.get(position);
        articlesHolder.mTitleTv.setText(model.getTitle());
        articlesHolder.mSubTitleTv.setText(model.getSubTitle());
        articlesHolder.mByLineTv.setText(model.getAuthor());
        articlesHolder.mDateTv.setText(getDate(model.getDate()));
        if(model.isFavorite()){
            articlesHolder.favoriteAddBtn.setVisibility(View.GONE);
        }
        articlesHolder.favoriteAddBtn.setOnClickListener(v -> {
            mAdapterCallback.addToFavorite(position, model.getId());
            articlesHolder.favoriteAddBtn.setVisibility(View.GONE);
        });
        Glide.with(mContext)
                .load(mArticles.get(position).getImageUrl())
                .into(articlesHolder.mItemImage);

        articlesHolder.itemView.setOnClickListener(v -> {
            Intent intent= new Intent(mContext, DetailsWebViewActivity.class);
            intent.putExtra(DetailsWebViewActivity.URL_EXTRA,model.getUrl());
            mContext.startActivity(intent);

        });
    }

    public String getDate(long milliSeconds) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public int getItemCount() {
        return mArticles.size();
    }

    class ArticlesHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image)
        ImageView mItemImage;

        @BindView(R.id.titleTv)
        TextView mTitleTv;

        @BindView(R.id.subTitle)
        TextView mSubTitleTv;

        @BindView(R.id.dateTv)
        TextView mDateTv;

        @BindView(R.id.byLineTv)
        TextView mByLineTv;

        @BindView(R.id.favorite_add)
        TextView favoriteAddBtn;

        @BindView(R.id.content)
        FrameLayout parrentLayout;

        ArticlesHolder(View itemView) {
            super(itemView);
            //       mItemImage = itemView.findViewById(R.id.);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface AdapterCallback {
        void addToFavorite(int position, long id);
    }
}
