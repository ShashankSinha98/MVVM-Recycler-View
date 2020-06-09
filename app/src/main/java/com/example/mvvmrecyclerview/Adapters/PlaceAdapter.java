package com.example.mvvmrecyclerview.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvvmrecyclerview.Models.Place;
import com.example.mvvmrecyclerview.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.ViewHolder> {

    private List<Place> mPlaces = new ArrayList<>();
    private Context mContext;

    public PlaceAdapter(Context context, List<Place> placeList){
        this.mContext = context;
        this.mPlaces = placeList;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.placename.setText(mPlaces.get(position).getPlaceName());
        //Glide.with(mContext).load(mPlaces.get(position).getPlaceImage()).into(holder.placeimage);
        Picasso.with(mContext).load(mPlaces.get(position).getPlaceImage()).into(holder.placeimage);
    }

    @Override
    public int getItemCount() {
        return mPlaces.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView placename;
        CircleImageView placeimage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            placename = itemView.findViewById(R.id.place_name);
            placeimage = itemView.findViewById(R.id.place_img);
        }
    }
}
