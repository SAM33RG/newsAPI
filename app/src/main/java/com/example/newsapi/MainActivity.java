package com.example.newsapi;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;
import com.example.newsapi.Models.Country;
import com.example.newsapi.R;
import com.example.newsapi.Utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newsapi.ui.main.SectionsPagerAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity  {
    ArrayList<Country> countries;
    ImageView searchButton;
    FloatingSearchView searchBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        searchButton = findViewById(R.id.main_search_button);
        searchBar = findViewById(R.id.main_searchbar);
        searchBar.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {

            }

            @Override
            public void onSearchAction(String currentQuery) {
                Log.d(Constants.TAG,currentQuery);
                Intent i = new Intent(getApplicationContext(), SearchActivity.class);
                i.putExtra("search", currentQuery);
                startActivity(i);
            }
        });
//        searchBar.setOnSearchActionListener(this);

//        searchBar.setVisibility(View.GONE);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                searchBar.setVisibility(View.VISIBLE);
            }
        });

        countries = new ArrayList<>();
        countries.add(new Country("USA","us"));
        countries.add(new Country("INDIA","in"));
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(countries, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }


}