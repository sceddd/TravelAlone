package com.example.myapplication.model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.locationUI.FavFragment;
import com.example.myapplication.locationUI.HomeFragment;
import com.example.myapplication.locationUI.LocationView;
import com.example.myapplication.locationUI.MoreFragment;

public class MyViewPageAdapter extends FragmentStateAdapter {
    Fragment fragment;
    public MyViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:
                fragment = new LocationView();
                break;
            case 2:
                fragment = new FavFragment();
                break;
            case 3:
                fragment = new MoreFragment();
                break;
            default:
                fragment = new HomeFragment();
                break;
        }
        return fragment;
    }
    public Fragment getFragment() {
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
