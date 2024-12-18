package com.mobile.androidchallengedagger.di;



import com.mobile.androidchallengedagger.ui.MainActivity;

import com.mobile.androidchallengedagger.viewmodel.ArticleViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkModule.class, ApiModule.class})
public interface AppComponent {
    void inject(MainActivity mainActivity);

    void inject(ArticleViewModel viewmodel);

}
