package com.example.newsapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapi.retrofit.response.Articles;
import com.squareup.picasso.Picasso;

public class ArticlesActivity extends AppCompatActivity {

    Articles article;
    TextView headline, source, author, date, time, description,content;
    ImageView mImageView;
    String dateTime, mDate, mTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_articles);
        headline = findViewById(R.id.articles_headline_textview);
        source = findViewById(R.id.articles_source_textview);
        author = findViewById(R.id.articles_author_textview);
        date = findViewById(R.id.articles_date_textview);
        time = findViewById(R.id.articles_time_textview);
        description = findViewById(R.id.articles_description_textview);
        content = findViewById(R.id.articles_content_textview);
        mImageView = findViewById(R.id.articles_imageview);
        article = (Articles) getIntent().getExtras().getSerializable("article");

        dateTime = article.getPublishedAt();

        headline.setText(article.getTitle());
        source.setText(article.getSource().getName());
        author.setText(article.getAuthor());
        description.setText(article.getDescription());
        content.setText(article.getContent());

        if(article.getUrlToImage()!=null){
            Picasso.get()
                    .load(article.getUrlToImage())
                    .placeholder(R.drawable.loading)
                    .error(R.drawable.warning).fit()
                    .into(mImageView);


        }



        mDate = new String();
        mTime = new String();
        boolean isDate =true;
        for(int i = 0 ; i<dateTime.length();i++){
            if(isDate){
                if(dateTime.charAt(i)!='T'){
                    mDate += dateTime.charAt(i);
                }else {
                    isDate =false;
                }
            }else {
                if(dateTime.charAt(i)!='Z'){
                    mTime += dateTime.charAt(i);
                }
            }
        }
        date.setText(mDate);
        time.setText(mTime);


    }
}