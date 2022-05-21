package com.example.zingmp3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingmp3.R;
import com.example.zingmp3.model.SongInPlaylist;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SongInPlaylistAdapter extends RecyclerView.Adapter<SongInPlaylistAdapter.SongInPlaylistViewHolder> {
    private Context mContext;
    private List<SongInPlaylist> listSongInPlaylist;

    public SongInPlaylistAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<SongInPlaylist> listSongInPlaylist){
        this.listSongInPlaylist = listSongInPlaylist;
    }

    @NonNull
    @Override
    public SongInPlaylistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_list_song_item, parent, false);
        return new SongInPlaylistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SongInPlaylistViewHolder holder, int position) {
        SongInPlaylist songInPlaylist = listSongInPlaylist.get(position);
        if(songInPlaylist == null){
            return;
        }

        int index = position + 1;
        holder.tvSTTPlayListSong.setText(index + "");
        Picasso.get().load(songInPlaylist.getThumbnailM()).into(holder.ivThumbnailM);
        holder.tvTitlePlaylistSong.setText(songInPlaylist.getTitle());
        holder.tvArtistNamePlayListSong.setText(songInPlaylist.getArtistsNames());

        holder.ivThumbnailM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "" + songInPlaylist.getEncodeID() + " " + songInPlaylist.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listSongInPlaylist != null){
            return listSongInPlaylist.size();
        }
        return 0;
    }

    public class SongInPlaylistViewHolder extends RecyclerView.ViewHolder {
        TextView tvSTTPlayListSong, tvTitlePlaylistSong, tvArtistNamePlayListSong;
        ImageView ivThumbnailM;

        public SongInPlaylistViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSTTPlayListSong = itemView.findViewById(R.id.tvSTTPlayListSong);
            tvTitlePlaylistSong = itemView.findViewById(R.id.tvTitlePlaylistSong);
            tvArtistNamePlayListSong = itemView.findViewById(R.id.tvArtistNamePlayListSong);
            ivThumbnailM = itemView.findViewById(R.id.ivThumbnailM);
        }
    }
}
