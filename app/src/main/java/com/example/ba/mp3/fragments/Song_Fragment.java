package com.example.ba.mp3.fragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.telecom.ConnectionService;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ba.mp3.R;
import com.example.ba.mp3.adapters.ItemsongAdapter;
import com.example.ba.mp3.item.ItemSong;
import com.example.ba.mp3.manager.ItemSongManager;
import com.example.ba.mp3.service.MyService;

import java.util.List;

public class Song_Fragment extends Fragment implements ItemsongAdapter.IItemsongAdapter {
    private RecyclerView rc;
    private ItemsongAdapter adapter;
    private ItemSongManager manager;
    private List<ItemSong> allSongs;
    private MyService service;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_songs, container, false);

        init(view);
        initService();
        return view;
    }

    public void init(View view) {
        rc = view.findViewById(R.id.rc_list_song);
        adapter = new ItemsongAdapter(this);
        manager = new ItemSongManager(this.getContext());
        rc.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        adapter.notifyDataSetChanged();
        rc.setAdapter(adapter);
        manager.queryToAllSong();
//        allSongs = manager.getAllSongs();

    }

    public void initService() {
        Intent intentService = new Intent();
        intentService.setClass(this.getContext(), MyService.class);
        this.getActivity().startService(intentService);
        ServiceConnection conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                MyService.MyBinder binder = (MyService.MyBinder) iBinder;
                service = binder.getService();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {

            }
        };
        Intent intent=new Intent();
        intent.setClass(this.getContext(),MyService.class);
//        bindService(intent,conn,this.getContext().);
        this.getActivity().bindService(intent,conn, Context.BIND_AUTO_CREATE);
    }

    @Override
    public int getCount() {

//        return allSongs.size();
        if(service==null){
            return 0;
        }
        else {
            return service.getAllsong().size();
        }

    }

    @Override
    public ItemSong getItem(int position) {

//        return allSongs.get(position);
        return service.getAllsong().get(position);
    }

    @Override
    public void onClickItem(int position) {
        Log.d("test onclick", "click on itemview");
        service.play(position);
        Log.d("test","data: sub"+service.getAllsong().get(position).getData());

    }
}
