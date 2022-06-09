package com.example.zingmp3.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingmp3.Model.SongInPlaylist;
import com.example.zingmp3.R;
import com.example.zingmp3.Activity.PlaySong;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ZingChartAdapter extends RecyclerView.Adapter<ZingChartAdapter.ZingChartViewHolder> {
    private Context context;
    private List<SongInPlaylist> songInPlaylists;

    public ZingChartAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<SongInPlaylist> songInPlaylists){
        this.songInPlaylists = songInPlaylists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ZingChartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zing_chart_item, parent, false);
        return new ZingChartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ZingChartViewHolder holder, int position) {
        final SongInPlaylist song = songInPlaylists.get(position);
        if(song == null){
            return;
        }
        holder.tvSTT.setText("" + song.getNumOrder());

        if(song.getNumOrder() == 1) {
            holder.tvSTT.setTextColor(Color.BLUE);
        }
        if(song.getNumOrder() == 2) {
            holder.tvSTT.setTextColor(Color.GREEN);
        }
        if(song.getNumOrder() == 3) {
            holder.tvSTT.setTextColor(Color.RED);
        }
        Picasso.get().load(song.getThumbnailM()).into(holder.ivThumbChart);
        holder.tvTitleChart.setText(song.getTitle());
        holder.tvArtistChart.setText(song.getArtistsNames());

        holder.layoutZingChartItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<SongInPlaylist> songInPlaylists1 = new ArrayList<>(songInPlaylists.size());
                songInPlaylists1.addAll(songInPlaylists);
                Intent intent = new Intent(context, PlaySong.class);
                intent.putExtra("Song", song);
                intent.putExtra("ListSong", songInPlaylists1);
                context.startActivity(intent);
            }
        });
    }

    public void onRelease(){
        context = null;
    }

    @Override
    public int getItemCount() {
        if(songInPlaylists != null){
            return songInPlaylists.size();
        }
        return 0;
    }

    public class ZingChartViewHolder extends RecyclerView.ViewHolder {
        LinearLayout layoutZingChartItem;
        TextView tvSTT, tvTitleChart, tvArtistChart;
        ImageView ivThumbChart;
        public ZingChartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSTT = itemView.findViewById(R.id.tvSTT);
            tvTitleChart = itemView.findViewById(R.id.tvTitleChart);
            tvArtistChart = itemView.findViewById(R.id.tvArtistChart);
            ivThumbChart = itemView.findViewById(R.id.ivThumbChart);
            layoutZingChartItem = itemView.findViewById(R.id.layoutZingChartItem);
        }
    }

}
