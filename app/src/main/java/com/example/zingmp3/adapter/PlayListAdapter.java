package com.example.zingmp3.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingmp3.R;
import com.example.zingmp3.activity.ListSongInPlaylist;
import com.example.zingmp3.model.PlayList;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.PlayListViewHolder> {
    private Context context;
    private List<PlayList> playLists;

    public PlayListAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<PlayList> playLists){
        this.playLists = playLists;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public PlayListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.play_list_item, parent, false);

        return new PlayListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PlayListViewHolder holder, int position) {
        PlayList playList = playLists.get(position);
        if(playList == null){
            return;
        }
        Picasso.get().load(playList.getPlayListThumb()).into(holder.ivPlayList);
        holder.tvPlayList.setText(playList.getPlayListTitle());

        holder.ivPlayList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ListSongInPlaylist.class);
                intent.putExtra("PlayList", playList);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        if(playLists != null){
            return playLists.size();
        }
        return 0;
    }

    public class PlayListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPlayList;
        TextView tvPlayList;
        public PlayListViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPlayList = itemView.findViewById(R.id.ivPlayList);
            tvPlayList = itemView.findViewById(R.id.tvPlayList);
        }
    }
}
