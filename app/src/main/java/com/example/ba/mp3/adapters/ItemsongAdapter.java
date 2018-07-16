package com.example.ba.mp3.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ba.mp3.R;
import com.example.ba.mp3.item.ItemSong;

import java.text.SimpleDateFormat;

public class ItemsongAdapter extends RecyclerView.Adapter<ItemsongAdapter.ItemSongViewHolder> {
    private IItemsongAdapter inter;
    private SimpleDateFormat formatDuration=new SimpleDateFormat("mm:ss");
    private SimpleDateFormat formatDate=new SimpleDateFormat("dd/MM/yyyy");
    private static final  String TAG = ItemsongAdapter.class.getSimpleName();

    public ItemsongAdapter(IItemsongAdapter inter) {
        this.inter = inter;
    }

    @NonNull
    @Override
    public ItemSongViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        return new ItemSongViewHolder(inflater.
                inflate(R.layout.item_song, viewGroup, false));
    }
    @Override
    public void onBindViewHolder(@NonNull ItemSongViewHolder holder, final int position) {
            ItemSong itemSong=inter.getItem(position);
            holder.tv_song.setText(itemSong.getDisplayName());
        CharSequence duration=formatDuration.format(itemSong.getDuration());
        holder.tv_duration.setText(""+duration);

        Log.d(TAG, "duration: "+duration);
        holder.tv_date.setText(formatDate.format(itemSong.getDateCreated()));
        holder.tv_artist.setText(itemSong.getArtis());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inter.onClickItem(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return inter.getCount();
    }
    public static class ItemSongViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_song;
        private TextView tv_artist;
        private TextView tv_duration;
        private TextView tv_date;

        public ItemSongViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_artist=itemView.findViewById(R.id.tv_artis);
            tv_song=itemView.findViewById(R.id.tv_name);
            tv_duration=itemView.findViewById(R.id.tv_duration);
            tv_date=itemView.findViewById(R.id.tv_date);
        }
    }
    public interface IItemsongAdapter{
        int getCount();
        ItemSong getItem(int position);
        void onClickItem(int position);
    }
}
