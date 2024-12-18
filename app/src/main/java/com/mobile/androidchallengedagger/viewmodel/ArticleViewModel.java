package com.mobile.androidchallengedagger.viewmodel;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.mobile.androidchallengedagger.Utils.SharedPreferences;
import com.mobile.androidchallengedagger.Utils.Util;
import com.mobile.androidchallengedagger.data.model.Article;
import com.mobile.androidchallengedagger.data.model.Articles;
import com.mobile.androidchallengedagger.data.ApiService;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;


public class ArticleViewModel extends ViewModel {
    private static final String TAG = "ViewModel";

    private ApiService apiService;
    private MutableLiveData<List<Article>> articleList = new MutableLiveData<>();


   // @ViewModelInject
    @Inject
    public ArticleViewModel(ApiService apiService) {
        this.apiService = apiService;

    }

    public MutableLiveData<List<Article>> getArticleList() {
        return articleList;
    }

    public void fetchData(Context mContext) {

        if (Util.isOnline(mContext)) {
            apiService.fetchData()
                    .subscribeOn(Schedulers.io()) // Perform network request on a background thread
                    .observeOn(AndroidSchedulers.mainThread()) // Observe results on the main thread
                    .subscribe(new Observer<Response<Articles>>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(Response<Articles> response) {

                            if (response.code() == 200) {

                                int statusCode = response.code();

                                Articles responseData = response.body();

                                articleList.postValue(responseData.getArticles());
                                SharedPreferences.saveList(responseData.getArticles(), "articles");
                            } else {
                                // Error response, handle it accordingly
                                // You can access the error status code and error body if needed
                                int errorCode = response.code();
                                String errorBody = response.errorBody() != null ? response.errorBody().toString() : "";

                                Log.e("ErrorResponse", "Error Code: " + errorCode);
                                Log.e("ErrorResponse", "Error Body: " + errorBody);
                                // Handle the error in your UI
                                //          view.showError("Error: " + errorCode);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {

                            Toast.makeText(mContext, "Please try after sometime", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onComplete() {

                        }
                    });
        }else{
            articleList.postValue(SharedPreferences.getList("articles"));
        }
    }
}
