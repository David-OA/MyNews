package com.oconte.david.mynews.RecyclerView;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oconte.david.mynews.Article;
import com.oconte.david.mynews.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NYTArticleViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.fragment_main_title) TextView textView;
    @BindView(R.id.fragment_main_image) ImageView imageView;

    public NYTArticleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNYTArticle (Article article) {
        this.textView.setText(article.getAbstract());
        //this.imageView.
    }
}
