package com.mobile.androidchallengedagger.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.mobile.androidchallengedagger.App;
import com.mobile.androidchallengedagger.R;
import com.mobile.androidchallengedagger.Utils.NetworkChangeReceiver;
import com.mobile.androidchallengedagger.Utils.SharedPreferences;
import com.mobile.androidchallengedagger.data.model.Article;
import com.mobile.androidchallengedagger.ui.adapter.CustomAdapter;

import com.mobile.androidchallengedagger.ui.interfaces.OnItemClickListener;
import com.mobile.androidchallengedagger.viewmodel.ArticleViewModel;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {


    RecyclerView mRecyclerView;
    @Inject
    ArticleViewModel viewModel;

    NetworkChangeReceiver mNetworkChangeReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mNetworkChangeReceiver = new NetworkChangeReceiver();

        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);
        lbm.registerReceiver(mNetworkReceiver, new IntentFilter("network"));

        registerReceiver(mNetworkChangeReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        new SharedPreferences(this);

        mRecyclerView = findViewById(R.id.recyclerview);

        mRecyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        App.getAppComponent().inject(this);

        viewModel.getArticleList().observe(this, articles -> {
            CustomAdapter mCustomAdapter = new CustomAdapter(articles,getApplicationContext(),MainActivity.this);
            mRecyclerView.setAdapter(mCustomAdapter);
        });

        viewModel.fetchData(this);


    }

    @Override
    public void onItemClick(Article article) {
        Intent mIntent = new Intent(MainActivity.this,DetailActivity.class);
        mIntent.putExtra("article",article);
        startActivity(mIntent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mNetworkChangeReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mNetworkReceiver);
    }

    private BroadcastReceiver mNetworkReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            //String message = intent.getStringExtra("message");
            //Log.d("receiver", "Got message: " + message);
            viewModel.fetchData(context);
        }
    };

}