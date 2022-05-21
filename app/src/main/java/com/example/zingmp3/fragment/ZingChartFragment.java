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
import com.example.zingmp3.adapter.ZingChartAdapter;
import com.example.zingmp3.constant.URL;
import com.example.zingmp3.model.Xone;
import com.example.zingmp3.model.ZingChart;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ZingChartFragment extends Fragment {
    View view;
    RecyclerView rcvZingChart;
    private List<ZingChart> listZingChart;
    private ZingChartAdapter zingChartAdapter;
    private int stt = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_zing_chart, container, false);
        rcvZingChart = view.findViewById(R.id.rcvZingChart);

        loadZingChart();
        return view;
    }

    public void loadZingChart() {
        listZingChart = new ArrayList<>();
        zingChartAdapter = new ZingChartAdapter(getActivity());
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL.ZING_CHART_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONObject rtChart = data.getJSONObject("RTChart");
                    JSONArray parentItems = rtChart.getJSONArray("items");
                    for (int i = 0; i < parentItems.length(); i++) {
                        if(i < 10){
                            JSONObject jsonObject = parentItems.getJSONObject(i);
                            stt++;
                            String encodeId = jsonObject.getString("encodeId");
                            String title = jsonObject.getString("title");
                            String artistsNames = jsonObject.getString("artistsNames");
                            String thumbnail = jsonObject.getString("thumbnailM");
                            listZingChart.add(new ZingChart(stt, encodeId, title, artistsNames, thumbnail));
                            set();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Load zing chart failed", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonObjectRequest);
    }

    public void set() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rcvZingChart.setLayoutManager(linearLayoutManager);
        zingChartAdapter.setData(listZingChart);
        rcvZingChart.setAdapter(zingChartAdapter);
    }

}
