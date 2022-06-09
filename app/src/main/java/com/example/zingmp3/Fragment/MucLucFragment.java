package com.example.zingmp3.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.zingmp3.R;
import com.example.zingmp3.Adapter.MucLucAdapter;
import com.example.zingmp3.Model.MucLuc;

import java.util.ArrayList;
import java.util.List;

public class MucLucFragment extends Fragment {
    View view;
    private RecyclerView rcvMucLuc;
    private List<MucLuc> mListMucLuc;
    private MucLucAdapter mucLucAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_mucluc, container, false);

        initUI();

        mucLucAdapter = new MucLucAdapter(getActivity());
        mucLucAdapter.setData(addMucLuc());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rcvMucLuc.setLayoutManager(linearLayoutManager);
        rcvMucLuc.setAdapter(mucLucAdapter);
        return view;
    }
    private void initUI() {
        rcvMucLuc = view.findViewById(R.id.rcvMucLuc);
    }

    public List<MucLuc> addMucLuc(){
        mListMucLuc = new ArrayList<>();
        mListMucLuc.add(new MucLuc("Nhạc Mới", R.drawable.icon_muc_luc_music));
        mListMucLuc.add(new MucLuc("Thể Loại", R.drawable.icon_mu_luc_cate));
        mListMucLuc.add(new MucLuc("Top 100", R.drawable.icon_muc_luc_top));
        mListMucLuc.add(new MucLuc("Podcast", R.drawable.icon_muc_luc_podcast));
        mListMucLuc.add(new MucLuc("VIP", R.drawable.icon_muc_luc_vip));
        mListMucLuc.add(new MucLuc("Top MV", R.drawable.icon_muc_luc_mv));
        mListMucLuc.add(new MucLuc("Sự Kiện", R.drawable.icon_muc_luc_event));
        return mListMucLuc;
    }
}
