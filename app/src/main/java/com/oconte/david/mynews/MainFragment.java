package com.oconte.david.mynews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oconte.david.mynews.RecyclerView.NYTArticleAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainFragment extends Fragment implements NYTCalls.Callbacks {

    // FOR DESIGN
    @BindView(R.id.fragment_main_textview) TextView textView;
    @BindView(R.id.fragment_main_recycler_view) RecyclerView recyclerView;
    //@BindView(R.id.fragment_main_swipe_container) SwipeRefreshLayout swipeRefreshLayout;

    // FOR DATA
    //private Article articles;
    private NYTArticleAdapter adapter;
    private Result results;

    public MainFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        this.configureRecyclerView();
        this.executeHttpRequestWithRetrofit();
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
        // Reset list
        this.results = new Result();

        // Create adapter passing the list of articles
        this.adapter = new NYTArticleAdapter(); // je supprime this.result

        // Attach the adapter to the recyclerview to populate items
        this.recyclerView.setAdapter(this.adapter);

        // Set layout manager to position the items
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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

    @OnClick(R.id.fragment_main_button)
    public void submit(View view) {
        this.executeHttpRequestWithRetrofit(); //add WithRetrofit
    }

    // -----------------
    // HTTP REQUEST Retrofit Way and RxJAVA
    // -----------------
    private void executeHttpRequestWithRetrofit() {
        this.updateUIWhenStartingHTTPRequest();
        NYTCalls.fetchResult(this, "movies");

    }


    @Override
    public void onResponse(@Nullable Result results) {
        // When getting response, we update UI
        this.adapter.updateCallRetrofitNews(results);
    }

    @Override
    public void onFailure() {
        // When getting error, we update UI
        this.updateUIWhenStopingHTTPRequest("An error happened !");
    }


        /*@Override
        public void onPreExecute() {
            this.updateUIWhenStartingHTTPRequest();
        }

        @Override
        public void doInBackground() {}

        @Override
        public void onPostExecute(String json) {
            this.updateUIWhenStopingHTTPRequest(json);
        }*/

    // ------------------
    //  UPDATE UI
    // ------------------

    /*private void updateUI(List<Article> articles) {
        articles.addAll(articles);
        adapter.notifyDataSetChanged();
        //swipeRefreshLayout.setRefreshing(false);
    }*/


    private void updateUIWhenStartingHTTPRequest(){
        this.textView.setText("Downloading...");
    }

    private void updateUIWhenStopingHTTPRequest(String response){
        this.textView.setText(response);
    }

    // Update UI showing only name of articles
    private void updateUIWithListOfresults(List<Article> articles) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Article article : articles) {
            stringBuilder.append("-"+ article.getAbstract()+"\n");
        }
        updateUIWhenStopingHTTPRequest(stringBuilder.toString());
    }
}
