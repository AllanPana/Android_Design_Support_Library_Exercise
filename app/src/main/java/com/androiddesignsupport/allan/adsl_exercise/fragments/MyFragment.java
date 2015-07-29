package com.androiddesignsupport.allan.adsl_exercise.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.androiddesignsupport.allan.adsl_exercise.R;
import com.androiddesignsupport.allan.adsl_exercise.adapters.RecyclerViewAdapter;
import com.androiddesignsupport.allan.adsl_exercise.network.Api;
import com.androiddesignsupport.allan.adsl_exercise.pojos.Flower;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment extends Fragment {

    private final String END_POINT_URL  = "http://services.hanselandpetal.com";
    private List<Flower> flowerList;


    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.text_view_response_time)
    TextView textViewResponseTime;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;


    long totalResponseTime;


    public MyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.my_fragment, container, false);
        ButterKnife.bind(this, view);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        setResPonse();
        setUpSwipeRefresh();
        return view;
    }


    private void setUpSwipeRefresh() {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setResPonse();
            }
        });
    }

    /**
     *
     */
    public void setResPonse(){

        final long startTime = System.currentTimeMillis();
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(END_POINT_URL).build();
        Api flowerApi = restAdapter.create(Api.class);

        flowerApi.getFlowerData(new Callback<List<Flower>>() {
            @Override
            public void success(List<Flower> flowers, Response response) {
                flowerList = flowers;
                RecyclerViewAdapter adapter = new RecyclerViewAdapter(getActivity(),flowerList);
                recyclerView.setAdapter(adapter);

                totalResponseTime = System.currentTimeMillis() - startTime;
                textViewResponseTime.setText("Retrofit Response Time : " + totalResponseTime);
            }

            @Override
            public void failure(RetrofitError error) {

                Toast.makeText(getActivity(),error.toString(),Toast.LENGTH_LONG).show();
            }
        });

        swipeRefreshLayout.setRefreshing(false);
    }

}
