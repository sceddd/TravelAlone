package com.example.myapplication.model;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.locationUI.FavFragment;
import com.example.myapplication.locationUI.GiftFragment;
import com.example.myapplication.locationUI.HomeFragment;
import com.example.myapplication.locationUI.LocationView;
import com.example.myapplication.locationUI.MoreFragment;

public class MyViewPageAdapter extends FragmentStateAdapter {

    public MyViewPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
<<<<<<< HEAD
            case 0:
//                return new HomeFragment();
                return new LocationView();
=======
>>>>>>> fa36ceea32abdb651d00a93f531d33267da86f88
            case 1:
                return new LocationView();
            case 2:
                return new GiftFragment();
            case 3:
                return new MoreFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
