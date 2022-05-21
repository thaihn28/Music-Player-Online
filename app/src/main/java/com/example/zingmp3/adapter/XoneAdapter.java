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
import com.example.zingmp3.model.Xone;
import com.squareup.picasso.Picasso;

import java.util.List;

public class XoneAdapter extends RecyclerView.Adapter<XoneAdapter.XoneViewHolder> {
    private Context context;
    private List<Xone> listXone;

    public XoneAdapter(Context context) {
        this.context = context;
    }

    public void setData(List<Xone> listXone){
        this.listXone = listXone;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public XoneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.xone_item, parent, false);
        return new XoneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull XoneViewHolder holder, int position) {
        Xone xone = listXone.get(position);
        if(xone == null){
            return;
        }
        Picasso.get().load(xone.getXoneThumb()).into(holder.ivXone);
        holder.tvXone.setText(xone.getXoneTitle());
    }

    @Override
    public int getItemCount() {
        if(listXone != null){
            return listXone.size();
        }
        return 0;
    }

    public class XoneViewHolder extends RecyclerView.ViewHolder {
        ImageView ivXone;
        TextView tvXone;
        public XoneViewHolder(@NonNull View itemView) {
            super(itemView);
            ivXone = itemView.findViewById(R.id.ivXone);
            tvXone = itemView.findViewById(R.id.tvXone);
        }
    }
}
