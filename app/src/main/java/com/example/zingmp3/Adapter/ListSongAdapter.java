package com.example.zingmp3.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingmp3.Model.SongInPlaylist;
import com.example.zingmp3.R;

import java.util.List;

public class ListSongAdapter extends RecyclerView.Adapter<ListSongAdapter.ListSongViewHolder> {
    private Context context;
    private List<SongInPlaylist> songInPlaylists;

    public ListSongAdapter(Context context, List<SongInPlaylist> songInPlaylists){
        this.context = context;
        this.songInPlaylists = songInPlaylists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ListSongViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_song_item, parent, false);
        return new ListSongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListSongViewHolder holder, int position) {
        if(songInPlaylists == null){
            return;
        }
        final SongInPlaylist song = songInPlaylists.get(position);
        holder.tvListSongOrder.setText(song.getNumOrder() + "");
        holder.tvListSongName.setText(song.getTitle());
        holder.tvListSongArtist.setText(song.getArtistsNames());
    }

    @Override
    public int getItemCount() {
        if(songInPlaylists != null){
            return songInPlaylists.size();
        }
        return 0;
    }

    public class ListSongViewHolder extends RecyclerView.ViewHolder{
        private TextView tvListSongOrder, tvListSongName, tvListSongArtist;
        public ListSongViewHolder(@NonNull View itemView) {
            super(itemView);
            tvListSongOrder = itemView.findViewById(R.id.tvListSongOrder);
            tvListSongName = itemView.findViewById(R.id.tvListSongName);
            tvListSongArtist = itemView.findViewById(R.id.tvListSongArtist);
        }
    }
}
