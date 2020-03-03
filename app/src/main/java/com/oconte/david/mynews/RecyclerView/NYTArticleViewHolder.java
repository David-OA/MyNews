package com.oconte.david.mynews.RecyclerView;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.oconte.david.mynews.Utils.ConfigureDate;
import com.oconte.david.mynews.Models.Article;
import com.oconte.david.mynews.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NYTArticleViewHolder extends RecyclerView.ViewHolder {

    // TextView
    @BindView(R.id.fragment_main_title) TextView textView;
    @BindView(R.id.fragment_main_section) TextView textView1;
    @BindView(R.id.fragment_main_date) TextView date;

    // ImageView
    @BindView(R.id.fragment_main_image) ImageView imageView;

    public NYTArticleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void updateWithNYTArticle (Article article) {
        this.textView.setText(article.getTitle());
        this.date.setText(ConfigureDate.convertDateFromAPIToDisplay(article.getPublishedDate()));
        String section = article.getSection();
        String subsection = article.getSubsection();
        if (subsection.length() <= 0) {
            this.textView1.setText(section);
        } else {
            this.textView1.setText(section + " > " + subsection);
        }
        Picasso.get()
                .load(getFirstUrl(article))
                .resize(60, 60)
                .into(this.imageView);
    }

    /**
     * It's for get url Images
     * @param article
     * @return
     */
    public String getFirstUrl(Article article) {
        if (article.getMultimedia() != null && article.getMultimedia().size() > 0) {
            String url = article.getMultimedia().get(0).getUrl();
            return url;
        }
        return null;
    }
}
