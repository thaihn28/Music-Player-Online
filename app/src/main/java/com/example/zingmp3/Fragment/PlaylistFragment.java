package com.example.zingmp3.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.zingmp3.R;
import com.example.zingmp3.Adapter.PlayListAdapter;
import com.example.zingmp3.constant.Json;
import com.example.zingmp3.constant.URL;
import com.example.zingmp3.Model.PlayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//public class PlaylistFragment extends Fragment implements ICallBackJsonArray {
public class PlaylistFragment extends Fragment{

    private View view;
    private RecyclerView rcvPlayList;
    private List<PlayList> playLists;
    private PlayListAdapter playListAdapter;
    private JSONArray PARENTS_ITEMS;
    private Json json;
    JSONArray value;
    private RequestQueue queue;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_playlist, container, false);

        initUI();



        json = Json.getInstance(getActivity());
//        json.test(getActivity());
//        BlockingQueue<JSONArray> result = json.test(getActivity());
//        try {
//            value = result.take(); // this will block your thread
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }

        loadPlayList();

        return view;
    }

    private void initUI() {
        rcvPlayList = view.findViewById(R.id.rcvPlaylist);
    }

    public void loadPlayList() {
        playLists = new ArrayList<>();
        playListAdapter = new PlayListAdapter(getActivity());
        RequestQueue queue = Volley.newRequestQueue(getActivity());
//        json.loadJsonArray(getActivity(), new ICallBackJsonArray() {
//            @Override
//            public JSONArray returnJsonArray(JSONArray jsonArray) {
//                return null;
//            }
//        });

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL.HOME_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    PARENTS_ITEMS = data.getJSONArray("items");
                    JSONObject jsonObject = PARENTS_ITEMS.getJSONObject(4);
                    JSONArray childItems = jsonObject.getJSONArray("items");
                        for(int i = 0; i < childItems.length(); i++){
                            JSONObject items = childItems.getJSONObject(i);
                            String playListID = items.getString("encodeId");
                            String title = items.getString("title");
                            String thumbnail = items.getString("thumbnail");
                            playLists.add(new PlayList(playListID, title, thumbnail));
                            set();
                        }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Load playlist failed", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }
    public void set(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rcvPlayList.setLayoutManager(linearLayoutManager);
        playListAdapter.setData(playLists);
        rcvPlayList.setAdapter(playListAdapter);
    }
}
