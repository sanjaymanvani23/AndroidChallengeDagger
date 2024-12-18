package com.mobile.androidchallengedagger.data;



import com.mobile.androidchallengedagger.data.model.Articles;


import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;

public interface ApiService {

    String BASE_URL="https://mocki.io/v1/";
    @GET("398641f3-1105-4e9e-b089-b3aa186d758b")
    Observable<Response<Articles>> fetchData();
}

