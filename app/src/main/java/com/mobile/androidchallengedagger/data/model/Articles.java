package com.mobile.androidchallengedagger.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class Articles {

@SerializedName("articles")
@Expose
private List<Article> articles;

public List<Article> getArticles() {
return articles;
}

public void setArticles(List<Article> articles) {
this.articles = articles;
}

}