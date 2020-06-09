package com.example.mvvmrecyclerview.ViewModels;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mvvmrecyclerview.Models.Place;
import com.example.mvvmrecyclerview.Repositories.PlaceRepository;

import java.util.List;

public class PlaceViewModel extends ViewModel {

    private MutableLiveData<List<Place>> mPlacesList;
    private PlaceRepository mRepo;
    private MutableLiveData<Boolean> mIsUpdating = new MutableLiveData<>();

    public void init(){
        if(mPlacesList != null){
            return;
        }
        mRepo = PlaceRepository.getInstance();
        mPlacesList = mRepo.getPlaces();
    }

    public void addNewPlace(final Place newPlace){
        Log.d("xlr8","Is updating to true");
        mIsUpdating.setValue(true);

        new AsyncTask<Void,Void,Void>(){

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                List<Place> currentPlaces = mPlacesList.getValue();
                currentPlaces.add(newPlace);
                mPlacesList.postValue(currentPlaces);
                Log.d("xlr8","Added new Place");

                mIsUpdating.setValue(false);
            }

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    Log.d("xlr8","Sleeping Thread");
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    Log.d("xlr8","Error: "+e.getMessage());
                    e.printStackTrace();
                }
                return null;
            }
        }.execute();


    }



    public LiveData<List<Place>> getPlaces(){
        return mPlacesList;
    }

    public LiveData<Boolean> getisUpdating(){return mIsUpdating;}
}
