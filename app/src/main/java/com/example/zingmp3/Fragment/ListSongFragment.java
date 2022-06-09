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

import com.example.zingmp3.Activity.PlaySong;
import com.example.zingmp3.Adapter.ListSongAdapter;
import com.example.zingmp3.R;

public class ListSongFragment extends Fragment {
    private View view;
    private RecyclerView rcvListSong;
    private ListSongAdapter listSongAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_listsong, container, false);
        rcvListSong = view.findViewById(R.id.rcvListSong);
        rcvListSong.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(PlaySong.listSongFromZingChart.size() > 0){
            listSongAdapter = new ListSongAdapter(getActivity(), PlaySong.listSongFromZingChart);
            rcvListSong.setAdapter(listSongAdapter);
        }
        return view;
    }
}
