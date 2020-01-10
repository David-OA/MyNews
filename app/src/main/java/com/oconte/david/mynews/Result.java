package com.oconte.david.mynews;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {
    @SerializedName("articles")
    @Expose
    public List<Article> articles;
}
