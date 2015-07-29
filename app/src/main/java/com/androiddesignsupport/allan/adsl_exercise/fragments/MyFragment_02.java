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

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.androiddesignsupport.allan.adsl_exercise.R;
import com.androiddesignsupport.allan.adsl_exercise.adapters.RecyclerViewAdapter;
import com.androiddesignsupport.allan.adsl_exercise.adapters.RecyclerViewAdapter_02;
import com.androiddesignsupport.allan.adsl_exercise.network.GsonRequest;
import com.androiddesignsupport.allan.adsl_exercise.network.VolleySingleton;
import com.androiddesignsupport.allan.adsl_exercise.pojos.Flower;

import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyFragment_02 extends Fragment {


    private final String END_POINT_URL  = "http://services.hanselandpetal.com/feeds/flowers.json";
    private List<Flower> flowerList;

    @Bind(R.id.recycler_view)
    RecyclerView recyclerView;

    @Bind(R.id.text_view_response_time)
    TextView textViewResponseTime;

    @Bind(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefreshLayout;

    long totalResponseTime;


    public MyFragment_02() {
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


    private void setResPonse() {

        final long startTime = System.currentTimeMillis();

        GsonRequest <Flower[]> gsonRequest = new GsonRequest<>(END_POINT_URL, Flower[].class,

                new Response.Listener<Flower[]>() {
                    @Override
                    public void onResponse(Flower[] flowers) {
                        flowerList = Arrays.asList(flowers);
                        RecyclerViewAdapter_02 adapter = new RecyclerViewAdapter_02(getActivity(),flowerList);
                        recyclerView.setAdapter(adapter);

                        totalResponseTime = System.currentTimeMillis() - startTime;
                        textViewResponseTime.setText("Volley Response Time : " + totalResponseTime);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(),volleyError+"",Toast.LENGTH_LONG).show();
                    }
                });

        VolleySingleton.getVolleySingletonInstance().addRequestQueue(gsonRequest);
        swipeRefreshLayout.setRefreshing(false);
    }


}
