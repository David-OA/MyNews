package com.oconte.david.mynews;

import com.oconte.david.mynews.Models.Result;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NYTService {

    @GET("/svc/topstories/v2/{section}.json?api-key=l0bvAgiwuO57HwCfWGjBaEMWqjUdAMoG")
    Call<Result> getFollowing(@Path("section") String section);

    @GET("/svc/mostpopular/v2/{section}.json?api-key=l0bvAgiwuO57HwCfWGjBaEMWqjUdAMoG")
    Call<Result> getMostPopular(@Path("section") String section);

    @GET("/svc/sports/v2/{section}.json?api-key=l0bvAgiwuO57HwCfWGjBaEMWqjUdAMoG")
    Call<Result> getSports(@Path("section") String section);

    @GET("/svc/search/v2/articlesearch.json?api-key=l0bvAgiwuO57HwCfWGjBaEMWqjUdAMoG")
    Call<Result> getSearchSection(
            @Query("Begin Date") String beginDate,
            @Query("End Date") String endDate,
            @Query("fq") String querySection,
            @Query("q") String queryTerm
            //@Query("page") int pageNumber,
    );

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
