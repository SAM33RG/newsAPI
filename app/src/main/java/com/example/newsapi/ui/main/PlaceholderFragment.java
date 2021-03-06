package com.example.newsapi.ui.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapi.Adapters.TopHeadlinesAdapter;
import com.example.newsapi.Network.NetworkUtil;
import com.example.newsapi.R;
import com.example.newsapi.Utils.Constants;
import com.example.newsapi.retrofit.response.TopHeadlinesResponse;

import java.util.ArrayList;
import java.util.Arrays;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private CompositeSubscription  mTopHeadlinesSubscription;
    private RecyclerView recyclerView;

    private String country ;

    public static PlaceholderFragment newInstance(String country) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ARG_SECTION_NUMBER, country);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTopHeadlinesSubscription = new CompositeSubscription();




    }

    private void handleResponseTopHeadlines(TopHeadlinesResponse topHeadlinesResponse) {
        Log.d(Constants.TAG,topHeadlinesResponse.toString());
        TopHeadlinesAdapter topHeadlinesAdapter = new TopHeadlinesAdapter(new ArrayList<>(Arrays.asList(topHeadlinesResponse.getArticles())),getActivity(),getActivity().getSupportFragmentManager());
        recyclerView.setAdapter(topHeadlinesAdapter);
        topHeadlinesAdapter.notifyDataSetChanged();

    }

    private void handleErrorTopHeadlines(Throwable throwable) {
        Log.e(Constants.TAG,throwable.toString());
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = root.findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        if (getArguments() != null) {
            country = getArguments().getString(ARG_SECTION_NUMBER);
        }


        if(country!=null){
            mTopHeadlinesSubscription.add(NetworkUtil.getRetrofit().topHeadlines(country, Constants.API_KEY)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribe(this::handleResponseTopHeadlines,this::handleErrorTopHeadlines));
        }


        return root;
    }
}