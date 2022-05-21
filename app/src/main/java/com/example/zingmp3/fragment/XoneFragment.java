package com.example.zingmp3.fragment;

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
import com.example.zingmp3.adapter.XoneAdapter;
import com.example.zingmp3.constant.Json;
import com.example.zingmp3.constant.URL;
import com.example.zingmp3.model.PlayList;
import com.example.zingmp3.model.Xone;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class XoneFragment extends Fragment {
    View view;
    private XoneAdapter xoneAdapter;
    private List<Xone> listXone;
    RecyclerView rcvXone;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_xone, container, false);
        rcvXone = view.findViewById(R.id.rcvXone);

        loadXone();
        return view;
    }

    public void loadXone() {
        listXone = new ArrayList<>();
        xoneAdapter = new XoneAdapter(getActivity());
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL.HOME_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONArray PARENTS_ITEMS = data.getJSONArray("items");
                    JSONObject jsonObject = PARENTS_ITEMS.getJSONObject(5);
                    JSONArray childItems = jsonObject.getJSONArray("items");
                    for(int i = 0; i < childItems.length(); i++){
                        JSONObject items = childItems.getJSONObject(i);
                        String encodeId = items.getString("encodeId");
                        String title = items.getString("title");
                        String thumbnail = items.getString("thumbnail");
                        listXone.add(new Xone(encodeId, title, thumbnail));
                        set();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Load Xone failed", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void set() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rcvXone.setLayoutManager(linearLayoutManager);
        xoneAdapter.setData(listXone);

        rcvXone.setAdapter(xoneAdapter);


    }
}
