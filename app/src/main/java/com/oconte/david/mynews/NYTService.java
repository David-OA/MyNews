package com.oconte.david.mynews;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface NYTService {

    @GET("/svc/topstories/v2/{section}.json?api-key=l0bvAgiwuO57HwCfWGjBaEMWqjUdAMoG")
    Call<Result> getFollowing(@Path("section") String section);

    //@GET("/svc/mostpopular/v2/{section}.json?api-key=l0bvAgiwuO57HwCfWGjBaEMWqjUdAMoG")
    //Call<Result> getFollowing(@Path(("section") String section);

    //@GET("/svc/surch/v2/{section}.json?api-key=l0bvAgiwuO57HwCfWGjBaEMWqjUdAMoG")
    //Call<Result> getFollowing(@Path(("section") String section);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.nytimes.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();
}
