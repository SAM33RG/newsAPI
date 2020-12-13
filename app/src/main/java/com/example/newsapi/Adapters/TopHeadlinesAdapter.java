package com.example.newsapi.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapi.ArticlesActivity;
import com.example.newsapi.R;
import com.example.newsapi.retrofit.response.Articles;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

public class TopHeadlinesAdapter extends RecyclerView.Adapter<TopHeadlinesAdapter.ViewHolder> {
    ArrayList<Articles> articlesArrayList;
    Activity activity;
    FragmentManager fragmentManager;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view =inflater.inflate(R.layout.row_image_plus_headline, parent, false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(lp);
        TopHeadlinesAdapter.ViewHolder viewHolder = new TopHeadlinesAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles a = articlesArrayList.get(position);
        if(a!=null){
            holder.textView.setText(a.getTitle());
            if(a.getUrlToImage()!=null){
                Picasso.get()
                        .load(a.getUrlToImage())
                        .placeholder(R.drawable.loading)
                        .error(R.drawable.warning)
                        .resize(128, 72)
                        .centerCrop()
                        .into(holder.imageView);


            }
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(getActivity().getApplicationContext(), ArticlesActivity.class);
                    i.putExtra("article", (Serializable) a);
                    getActivity().startActivity(i);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return articlesArrayList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView textView;
        public View layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.textView);
            layout = itemView.findViewById(R.id.layout);
        }
    }

    public TopHeadlinesAdapter(ArrayList<Articles> articlesArrayList, Activity activity, FragmentManager fragmentManager) {
        this.articlesArrayList = articlesArrayList;
        this.activity = activity;
        this.fragmentManager = fragmentManager;
    }

    public ArrayList<Articles> getArticlesArrayList() {
        return articlesArrayList;
    }

    public void setArticlesArrayList(ArrayList<Articles> articlesArrayList) {
        this.articlesArrayList = articlesArrayList;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
