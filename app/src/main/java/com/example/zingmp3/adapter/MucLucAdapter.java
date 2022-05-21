package com.example.zingmp3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingmp3.R;
import com.example.zingmp3.model.MucLuc;

import java.util.List;

public class MucLucAdapter extends RecyclerView.Adapter<MucLucAdapter.MucLucViewHolder> {
    private Context context;
    private List<MucLuc> mListMucLuc;

    public MucLucAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<MucLuc> mListMucLuc){
        this.mListMucLuc = mListMucLuc;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MucLucViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.muc_luc_item, parent, false);
        return new MucLucViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MucLucViewHolder holder, int position) {
        MucLuc mucLuc = mListMucLuc.get(position);
        if(mucLuc == null){
            return;
        }
        holder.ivMucLuc.setImageResource(mucLuc.getMlImage());
        holder.tvMucLuc.setText(mucLuc.getMlName());
    }

    @Override
    public int getItemCount() {
        if(mListMucLuc != null){
            return mListMucLuc.size();
        }
        return 0;
    }

    public class MucLucViewHolder extends RecyclerView.ViewHolder {
        ImageView ivMucLuc;
        TextView tvMucLuc;
        public MucLucViewHolder(@NonNull View itemView) {
            super(itemView);
            ivMucLuc = itemView.findViewById(R.id.ivMucLuc);
            tvMucLuc = itemView.findViewById(R.id.tvMucLuc);
        }
    }
}
