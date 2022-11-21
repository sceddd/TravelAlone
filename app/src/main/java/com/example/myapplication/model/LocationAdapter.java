package com.example.myapplication.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {
    private final LocationInterface locationInterface;
    protected Context context;
    protected ArrayList<Location> locations;

    public LocationAdapter(Context context,ArrayList<Location> locations,LocationInterface locationInterface) {
        this.context = context;
        this.locations = locations;
        this.locationInterface = locationInterface;
    }


    @NonNull
    @Override
    public LocationAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.location_view,parent,false);
        return new LocationAdapter.MyViewHolder(view,locationInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Location location = locations.get(position);
        holder.locName.setText(location.getLocName());
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView locName;
        public MyViewHolder(@NonNull View itemView, LocationInterface locationInterface) {
            super(itemView);
            locName = (TextView) itemView.findViewById(R.id.location_name);
            itemView.setOnClickListener(v->{
                if(locationInterface!= null){
                    int pos = getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){
                        locationInterface.onClickLocation(pos);
                    }
                }
            });
        }
    }
}
