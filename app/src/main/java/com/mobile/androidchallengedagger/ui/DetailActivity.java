package com.mobile.androidchallengedagger.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.mobile.androidchallengedagger.R;
import com.mobile.androidchallengedagger.data.model.Article;

public class DetailActivity extends AppCompatActivity {


    TextView textViewTitle;
    TextView textViewDesc;

    ImageView mImageViewBack;

    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);

        textViewTitle = findViewById(R.id.textTitle);
        textViewDesc = findViewById(R.id.textDesc);

        mImageViewBack = findViewById(R.id.image_back);

        mImageView = findViewById(R.id.imageview);

        mImageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Article mArticle = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
            mArticle = getIntent().getExtras().getSerializable("article", Article.class);

        }else{
            mArticle = (Article) getIntent().getSerializableExtra("article");
        }

        textViewTitle.setText(mArticle.getTitle());
        textViewDesc.setText(mArticle.getDescription());

        Glide.with(this).load(mArticle.getUrlToImage()).into(mImageView);

    }

}