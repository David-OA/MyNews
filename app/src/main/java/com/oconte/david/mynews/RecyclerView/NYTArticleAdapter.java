package com.oconte.david.mynews.RecyclerView;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.oconte.david.mynews.R;
import com.oconte.david.mynews.Result;

public class NYTArticleAdapter extends RecyclerView.Adapter<NYTArticleViewHolder> {

    private Result results;


    public NYTArticleAdapter(Result results) {
        this.results = results;
    }

    @Override
    public NYTArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.fragment_main_item, parent, false );

        return new NYTArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NYTArticleViewHolder viewHolder, int position) {
        viewHolder.updateWithNYTArticle(this.results.articles.get(position));
    }

    @Override
    public int getItemCount() {
        return this.results.articles.size();
    }
}