package com.example.ba.mp3;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.ba.mp3.fragments.Main_Fragment;
import com.example.ba.mp3.manager.ArtistManager;

public class MainActivity extends AppCompatActivity {
   // private FrameLayout content;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        new ArtistManager(this).querytoAllArtist();
    }

    private void init() {
        Main_Fragment main_fragment=new Main_Fragment();
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        transaction.add(R.id.content_main,main_fragment,Main_Fragment.class.getName());
        transaction.commit();

    }
}
