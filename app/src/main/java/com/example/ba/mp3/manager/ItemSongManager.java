package com.example.ba.mp3.manager;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;
import android.util.Log;

import com.example.ba.mp3.item.ItemSong;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemSongManager {
    private Context context;
    private List<ItemSong> allSongs=new ArrayList<>();
    private static final  String TAG=ItemSongManager.class.getSimpleName();

    public ItemSongManager(Context context) {
        this.context = context;
    }

    public void queryToAllSong() {
        Cursor c = context.getContentResolver().
                query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null, null, null, null
                );
        if(c==null){
            return;
        }
        String[] columns = c.getColumnNames();
        for (String column : columns) {
            Log.d(TAG, "getAllListAudio column: " + column);
        }
        c.moveToFirst();
        int indexId = c.getColumnIndex("_id");
        int indexData = c.getColumnIndex("_data");
//        int indexDisplayName = c.getColumnIndex("_display_name");
        int indexDisplayName = c.getColumnIndex("title");
        int indexMineType = c.getColumnIndex("mime_type");
        int indexDateAdd = c.getColumnIndex("date_added");
        int indexAlbumId = c.getColumnIndex("album_id");
        int indexDuration = c.getColumnIndex("duration");
        int indexArtist =c.getColumnIndex("artist");

        while (!c.isAfterLast()){
            long id = c.getLong(indexId);
            String data = c.getString(indexData);
            String displayName = c.getString(indexDisplayName);
            String artist =c.getString(indexArtist);

            //String mineType = c.getString(indexMineType);
            long dateAdd = c.getLong(indexDateAdd); //giay
            long albumId = c.getLong(indexAlbumId);
            long duration = c.getLong(indexDuration);
            ItemSong itemSong = new ItemSong();
            itemSong.setId(id);
            itemSong.setPath(data);
            itemSong.setDisplayName(displayName);
            itemSong.setArtis(artist);
            itemSong.setData(data);
         //   itemSong.setMineType(mineType);
            itemSong.setDateCreated(
                    new Date(dateAdd*1000)
            );
            itemSong.setAlbumId(albumId);
            itemSong.setDuration(duration);
            allSongs.add(itemSong);
            c.moveToNext();

        }
        c.close();

    }

    public List<ItemSong> getAllSongs() {
        return allSongs;
    }
}
