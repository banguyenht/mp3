package com.example.ba.mp3.manager;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.ba.mp3.item.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistManager {
    private Context context;
    private List<Artist> artistList=new ArrayList<>();
    private static final String TAG=ArtistManager.class.getSimpleName();

    public ArtistManager(Context context) {
        this.context = context;
    }

//    public void querytoAllArtist(){
//        Cursor c=context.getContentResolver().query(
//                MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
//                null,null,null,null);
    public void querytoAllArtist(){
        Cursor c=context.getContentResolver().query(
                MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI,
                null,null,null,null);

        String[] allcolums=c.getColumnNames();
        for (String column:allcolums) {
            Log.d(TAG,"all images columns: "+column);

        }
        c.moveToFirst();
        int indexArt=c.getColumnIndex("artist");
        while(!c.isAfterLast()){
            String item=c.getString(indexArt);
            Artist artist1=new Artist();
            artist1.setName(item);
            artistList.add(artist1);
            c.moveToNext();
        }
        c.close();
    }

}
