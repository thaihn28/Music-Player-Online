package com.example.zingmp3.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.zingmp3.R;
import com.example.zingmp3.model.Banner;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.BannerViewHolder> {
    private Context context;
    private List<Banner> listBanner;

    public BannerAdapter(Context context) {
        this.context = context;
    }
    public void setData(List<Banner> banners){
        this.listBanner = banners;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public BannerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_item, parent, false);
        view.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        return new BannerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BannerViewHolder holder, int position) {
        Banner banner = listBanner.get(position);
        if(banner == null){
            return;
        }
        Picasso.get().load(banner.getBanner()).into(holder.ivBanner);
    }

    @Override
    public int getItemCount() {
        if(listBanner != null){
            return listBanner.size();
        }
        return 0;
    }

    public class BannerViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBanner;
        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBanner = itemView.findViewById(R.id.ivBanner);
        }
    }
}

