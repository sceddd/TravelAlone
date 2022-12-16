package com.example.myapplication.locationUI;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.myapplication.R;
import com.example.myapplication.model.MyViewPageAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.time.LocalDate;

public class MainActivity extends AppCompatActivity {
    private ViewPager2 viewPager2;
    private BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        customActionBar();

        setContentView(R.layout.activity_main);
        setupBotNav();
    }

    public void setupBotNav(){
        viewPager2 = findViewById(R.id.view_pager_2);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        MyViewPageAdapter myViewPageAdapter = new MyViewPageAdapter(this);
        viewPager2.setAdapter(myViewPageAdapter);

        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.bottom_home){
                viewPager2.setCurrentItem(0);
            }
            if (id == R.id.bottom_location){
                viewPager2.setCurrentItem(1);
            }
            if (id == R.id.bottom_favorite){
                viewPager2.setCurrentItem(2);
            }
            if (id == R.id.bottom_more){
                viewPager2.setCurrentItem(3);
            }
            return true;
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_favorite).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_location).setChecked(true);
                        break;
                    case 3:
                        bottomNavigationView.getMenu().findItem(R.id.bottom_more).setChecked(true);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.menu_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Type here to search");
        searchView.setOnClickListener(v->{
            bottomNavigationView.getMenu().findItem(R.id.bottom_location).setChecked(true);
            viewPager2.setCurrentItem(1);

        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }


    public void customActionBar(){
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Travel ALone");
        actionBar.setSubtitle("");
        actionBar.setIcon(R.drawable.ic_logo);

    }
}