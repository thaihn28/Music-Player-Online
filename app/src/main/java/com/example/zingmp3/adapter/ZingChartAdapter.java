package com.example.zingmp3.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingmp3.R;
import com.example.zingmp3.activity.PlaySong;
import com.example.zingmp3.model.ZingChart;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ZingChartAdapter extends RecyclerView.Adapter<ZingChartAdapter.ZingChartViewHolder> {
    private Context context;
    private List<ZingChart> listZingChart;

    public ZingChartAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<ZingChart> listZingChart){
        this.listZingChart = listZingChart;
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
        final ZingChart zingChart = listZingChart.get(position);
        if(zingChart == null){
            return;
        }
        holder.tvSTT.setText("" + zingChart.getZcSTT());

        if(zingChart.getZcSTT() == 1) {
            holder.tvSTT.setTextColor(Color.BLUE);
        }
        if(zingChart.getZcSTT() == 2) {
            holder.tvSTT.setTextColor(Color.GREEN);
        }
        if(zingChart.getZcSTT() == 3) {
            holder.tvSTT.setTextColor(Color.RED);
        }
        Picasso.get().load(zingChart.getZcThumb()).into(holder.ivThumbChart);
        holder.tvTitleChart.setText(zingChart.getZcTitle());
        holder.tvArtistChart.setText(zingChart.getZcArtist());

        holder.ivThumbChart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ArrayList<ZingChart> zingCharts = new ArrayList<>(listZingChart.size());
                zingCharts.addAll(listZingChart);
                Intent intent = new Intent(context, PlaySong.class);
                intent.putExtra("Song", zingChart);
                intent.putExtra("ListSong", zingCharts);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        if(listZingChart != null){
            return listZingChart.size();
        }
        return 0;
    }

    public class ZingChartViewHolder extends RecyclerView.ViewHolder {
        TextView tvSTT, tvTitleChart, tvArtistChart;
        ImageView ivThumbChart;
        public ZingChartViewHolder(@NonNull View itemView) {
            super(itemView);
            tvSTT = itemView.findViewById(R.id.tvSTT);
            tvTitleChart = itemView.findViewById(R.id.tvTitleChart);
            tvArtistChart = itemView.findViewById(R.id.tvArtistChart);
            ivThumbChart = itemView.findViewById(R.id.ivThumbChart);
        }
    }
}
