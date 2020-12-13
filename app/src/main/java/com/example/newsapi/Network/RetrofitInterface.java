package com.example.newsapi.Network;


import com.example.newsapi.retrofit.response.TopHeadlinesResponse;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface RetrofitInterface {
    @GET("top-headlines")
    Observable<TopHeadlinesResponse> topHeadlines(@Query("country") String country, @Query("apiKey") String apikey);


}
