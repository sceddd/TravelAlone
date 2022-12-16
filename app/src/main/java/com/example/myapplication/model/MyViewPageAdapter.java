package com.example.myapplication.model;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.locationUI.FavFragment;
import com.example.myapplication.locationUI.HomeFragment;
import com.example.myapplication.locationUI.LocationView;
import com.example.myapplication.locationUI.MoreFragment;

public class MyViewPageAdapter extends FragmentStateAdapter {
    LocationView fragment = new LocationView();
    public MyViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                return fragment;
            case 2:
                return new FavFragment();
            case 3:
                return new MoreFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public LocationView getFragment() {
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
