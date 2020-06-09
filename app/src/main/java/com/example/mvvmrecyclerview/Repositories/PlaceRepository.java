package com.example.mvvmrecyclerview.Repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.mvvmrecyclerview.Models.Place;

import java.util.ArrayList;
import java.util.List;

/*
* Singleton class
* */
public class PlaceRepository {

    private static PlaceRepository instance;
    private ArrayList<Place> dataset = new ArrayList<>();

    public static PlaceRepository getInstance(){
        if(instance==null){
            instance = new PlaceRepository();
        }
        return instance;
    }

    // Pretend to get data from a webservice or online source
    public MutableLiveData<List<Place>> getPlaces(){
        setPlaces();

        MutableLiveData<List<Place>> data = new MutableLiveData<>();
        data.setValue(dataset);
        return data;
    }


    // Mimicing data from cloud
    private void setPlaces(){
        dataset.add(new Place("https://www.holidify.com/images/tooltipImages/HYDERABAD.jpg","Hyderabad"));
        dataset.add(new Place("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcTR3e8IcnJOr7OecsRMljVgdCghsZr7jQSFSUS-NcE1zmqTphmS&usqp=CAU","Paris"));
        dataset.add(new Place("https://media.voltron.voanews.com/Drupal/01live-166/styles/sourced/s3/2020-03/image1Empty%20NY%20street.jpeg?itok=HzcY0GAI","New York"));
        dataset.add(new Place("https://talktravelapp.com/wp-content/uploads/Tokyo-Japan.jpeg","Tokyo"));
        dataset.add(new Place("https://srcnaut.com/cdn-cgi/image/f=auto,fit=crop,g=0.5x0.5,w=2000,h=1125,q=90,d=1/upload/db/04/18/toronto-ontario.jpg","Ontario"));

    }

}
