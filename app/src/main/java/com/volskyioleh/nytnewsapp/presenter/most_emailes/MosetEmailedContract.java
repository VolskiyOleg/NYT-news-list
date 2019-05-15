/*
 * Copyright 2016, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.volskyioleh.nytnewsapp.presenter.most_emailes;

import android.arch.lifecycle.LiveData;
import android.support.v7.widget.RecyclerView;

import com.volskyioleh.nytnewsapp.db.entities.ArticleModel;
import com.volskyioleh.nytnewsapp.model.Article;
import com.volskyioleh.nytnewsapp.presenter.base.BaseContract;

import java.util.List;


public interface MosetEmailedContract {

    interface View extends BaseContract.View {
      void  showList(LiveData<List<ArticleModel>> articles);
    }

    interface Presenter extends BaseContract.Presenter<View> {
        void getMostEmailedArticlesList();
        void addToFavorite(long id);
    }
}
