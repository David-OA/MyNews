package com.oconte.david.mynews;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oconte.david.mynews.Calls.NYTCalls;
import com.oconte.david.mynews.Calls.NYTCallsMostPopular;
import com.oconte.david.mynews.Calls.NYTCallsSports;
import com.oconte.david.mynews.Models.Article;
import com.oconte.david.mynews.Models.Result;
import com.oconte.david.mynews.RecyclerView.NYTArticleAdapter;
import com.oconte.david.mynews.WebView.ItemClickSupport;
import com.oconte.david.mynews.WebView.WebViewActivity;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainFragment extends Fragment implements NYTCalls.Callbacks {

    // FOR DESIGN
    //@BindView(R.id.fragment_main_textview) TextView textView;
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;
    //@BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    // FOR DATA
    private NYTArticleAdapter adapter;

    Context context;

    Result result;



    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView();

        this.executeHttpRequestWithRetrofit();

        this.configureOnClickRecyclerView();

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    // -----------------
    // CONFIGURATION
    // -----------------

    // Configure RecyclerView, Adapter, LayoutManager
    private void configureRecyclerView() {

        // Create adapter passing the list of articles
        this.adapter = new NYTArticleAdapter();

        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);

        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    // -----------------
    // ACTION
    // -----------------

    private void configureOnClickRecyclerView(){
        ItemClickSupport.addTo(recyclerView, R.layout.activity_web_view)
                .setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                    @Override
                    public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                        result.articles.get(position);


                        Intent intent = new Intent(getContext(),WebViewActivity.class);
                        intent.putExtra("url", Article.class);

                        startActivity(intent);
                    }
                });
    }




    /*private void configureSwipeRefreshLayout(){
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                executeHttpRequestWithRetrofit();
            }
        });
    }*/

    // -----------------
    // ACTIONS
    // -----------------

    //@OnClick(R.id.fragment_main_button)
    //public void submit(View view) {
    //    this.executeHttpRequestWithRetrofit(); //add WithRetrofit
    //}

    //private void configureClickOnTabLayout() {
    //   if ()
    //}







    // -----------------
    // HTTP REQUEST Retrofit
    // -----------------
    private void executeHttpRequestWithRetrofit() {
        //this.updateUIWhenStartingHTTPRequest();
        NYTCalls.getTopStories(this, "movies");
    }

    private void executeHttpRequestWithRetrofitSports() {
        NYTCallsSports.getSports(this, "movies");
    }

    private void executeHttpRequestWithRetrofitMostPopular() {
        NYTCallsMostPopular.getMostPopular(this, "movies");
    }


    @Override
    public void onResponse(@Nullable Result results) {
        this.result = results;
        this.adapter.updateCallRetrofitNews(results);
    }

    @Override
    public void onFailure() {
        // When getting error, we update UI
        //this.updateUIWhenStopingHTTPRequest("An error happened !");
    }

    // ------------------
    //  UPDATE UI
    // ------------------

    /*private void updateUI(List<Article> articles) {
        articles.addAll(articles);
        adapter.notifyDataSetChanged();
        //swipeRefreshLayout.setRefreshing(false);
    }*/

    /*private void updateUIWhenStartingHTTPRequest(){
        this.textView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        this.textView.setText(response);
    }*/
}
