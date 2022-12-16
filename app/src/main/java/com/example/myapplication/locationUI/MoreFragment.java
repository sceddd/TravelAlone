package com.example.myapplication.locationUI;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;


public class MoreFragment extends Fragment {
    private View view;
//    TextView textView1, textView2,textView3,textView4,textView5;
    private View fragmentView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_more, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.fragmentView = view;
        int[] tvID = {R.id.tv_nppt, R.id.tv_sceddd, R.id.tv_twangermany, R.id.tv_video, R.id.tv_report};

        for (int id : tvID) {
            View eventView = view.findViewById(id);
            eventView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    accessWeb(v);
                }
            });
        }


    }

    public void accessWeb(View view) {
        int id = view.getId();
        switch (id){
            case R.id.tv_nppt:
                Toast.makeText(getContext(), "tv_nppt", Toast.LENGTH_SHORT).show();
                accessTo("https://github.com/npptGithub");
                break;
            case R.id.tv_sceddd:
                Toast.makeText(getContext(), "tv_sceddd", Toast.LENGTH_SHORT).show();
                accessTo("https://github.com/sceddd");
                break;

            case R.id.tv_twangermany:
                Toast.makeText(getContext(), "tv_twangermany", Toast.LENGTH_SHORT).show();
                accessTo("https://github.com/TwanGermany");
                break;

            case R.id.tv_video:
                Toast.makeText(getContext(), "tv_video", Toast.LENGTH_SHORT).show();
                accessTo("https://www.youtube.com/watch?v=sfVy3suWFj8");
                break;

            case R.id.tv_report:
                Toast.makeText(getContext(),"tv_report", Toast.LENGTH_SHORT).show();
                accessTo("https://docs.google.com/document/d/1jR_GFyIzwAfttfcxkXzjV44zWsEilJe9/edit");
                break;
        }
    }

    public void accessTo(String link){
        Intent intent = new Intent(getContext(), WebPage.class);
        intent.putExtra("LINK", link);
        startActivity(intent);
    }
}