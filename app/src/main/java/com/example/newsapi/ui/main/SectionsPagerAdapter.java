package com.example.newsapi.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.newsapi.Models.Country;
import com.example.newsapi.R;

import java.util.ArrayList;


public class SectionsPagerAdapter extends FragmentPagerAdapter {


    ArrayList<Country> countries;

    public SectionsPagerAdapter( ArrayList<Country> countries, FragmentManager fm) {
        super(fm);
        this.countries = countries;
    }

    @Override
    public Fragment getItem(int position) {

        return PlaceholderFragment.newInstance(countries.get(position).getCode());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return  countries.get(position).getName();
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return countries.size();
    }
}