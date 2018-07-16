package com.example.ba.mp3.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.ba.mp3.item.ItemSong;
import com.example.ba.mp3.manager.ItemSongManager;
import com.example.ba.mp3.manager.MediaManager;

import java.io.IOException;
import java.util.List;

public class MyService extends Service {
    private ItemSongManager itemSongManager;
    private MediaManager mediaManager;

    @Override
    public void onCreate() {
        super.onCreate();
        itemSongManager=new ItemSongManager(this);
        mediaManager=new MediaManager();
        itemSongManager.queryToAllSong();
    }
    public List<ItemSong> getAllsong(){
        return  itemSongManager.getAllSongs();
    }
    public void play(int position){
        try {
            mediaManager.setDataSource(itemSongManager.getAllSongs().get(position).getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaManager.play();
    }
    public void pause(){
        mediaManager.pause();
    }

    public int getCurrentPosition(){
        return mediaManager.getCurrentPosition();
    }
    public void stop(){
        mediaManager.stop();
    }
    public void release(){
        mediaManager.release();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder(this);
    }


    public static class MyBinder extends Binder{
        public MyService service;

        public MyBinder(MyService service) {

            this.service = service;
        }

        public MyService getService() {
            return service;
        }
    }
}
