package com.example.mvvmrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.example.mvvmrecyclerview.Adapters.PlaceAdapter;
import com.example.mvvmrecyclerview.Models.Place;
import com.example.mvvmrecyclerview.ViewModels.PlaceViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private PlaceAdapter mPlaceAdapter;
    private FloatingActionButton mFab;
    private ProgressBar mProgressBar;

    private PlaceViewModel mPlaceViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFab = findViewById(R.id.fab);
        mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerView = findViewById(R.id.recycler_view);

        mPlaceViewModel = ViewModelProviders.of(this).get(PlaceViewModel.class);

        // getting data
        mPlaceViewModel.init();

        // Observe changes to our Places list
        mPlaceViewModel.getPlaces().observe(this, new Observer<List<Place>>() {
            @Override
            public void onChanged(List<Place> placeList) {
                // notify adapter that data is changed
                mPlaceAdapter.notifyDataSetChanged();
            }
        });

        mPlaceViewModel.getisUpdating().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean){
                    showProgressBar();
                } else {
                    hideProgressBar();
                    mRecyclerView.smoothScrollToPosition(mPlaceViewModel.getPlaces().getValue().size()-1);
                }
            }
        });

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPlaceViewModel.addNewPlace(new Place("https://img.itinari.com/pages/images/original/0afe2310-ecf1-42ae-ba94-c6d9276d858b-istock-522473665.jpg?ch=DPR&dpr=1&w=1600&s=6b2e0ee23c973a0d07e3e44fb4bc1251","Osaka"));
            }
        });

        initRecyclerView();
/*
        ArrayList<Place> places = new ArrayList<>();

        places.add(new Place("https://blog.assets.traveltrivia.com/2019/03/Alpine-Lake.jpg","Switzerland"));
        places.add(new Place("https://blog.assets.traveltrivia.com/2019/03/Alpine-Lake.jpg","Switzerland"));
        places.add(new Place("https://blog.assets.traveltrivia.com/2019/03/Alpine-Lake.jpg","Switzerland"));

        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        placeAdapter = new PlaceAdapter(this,places);
        recyclerView.setAdapter(placeAdapter);

 */
    }

    private void initRecyclerView() {

        mPlaceAdapter = new PlaceAdapter(this,mPlaceViewModel.getPlaces().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mPlaceAdapter);
    }

    private void showProgressBar(){ mProgressBar.setVisibility(View.VISIBLE);}

    private void hideProgressBar() { mProgressBar.setVisibility(View.GONE);}
}