package com.mobile.androidchallengedagger;

import android.app.Application;

import com.mobile.androidchallengedagger.di.ApiModule;
import com.mobile.androidchallengedagger.di.AppComponent;
import com.mobile.androidchallengedagger.di.DaggerAppComponent;
import com.mobile.androidchallengedagger.di.NetworkModule;


public class App extends Application {
    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .apiModule(new ApiModule())
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}

