package com.example.zingmp3.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.zingmp3.R;
import com.example.zingmp3.Adapter.BannerAdapter;
import com.example.zingmp3.constant.URL;
import com.example.zingmp3.Model.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import me.relex.circleindicator.CircleIndicator3;

public class BannerFragment extends Fragment {
    View view;
    private BannerAdapter bannerAdapter;
    private ViewPager2 viewPagerBanner;
    private CircleIndicator3 circleIndicator;
    private List<Banner> banners;
    private Runnable runnable;
    private Handler handler;
    private int currentItem;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_fragment_banner, container, false);
        initUI();
        banners = new ArrayList<>();
        bannerAdapter = new BannerAdapter(getActivity());
        loadBanner();
        sliderBanner();
        return view;
    }
    private void initUI() {
        viewPagerBanner = view.findViewById(R.id.view_pager_banner);
        circleIndicator = view.findViewById(R.id.indicatorDefault);
    }
    public void loadBanner() {
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL.HOME_URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONArray parentItems = data.getJSONArray("items");
                    for(int i = 0; i < parentItems.length(); i++){
                        JSONObject jsonObject = parentItems.getJSONObject(i);
                        JSONArray childItems = jsonObject.getJSONArray("items");
                        for(int j = 0; j < childItems.length(); j++){
                            JSONObject jsonItemObject = childItems.getJSONObject(j);
                            String banner = jsonItemObject.getString("banner");
                            banners.add(new Banner(banner));
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
                Toast.makeText(getActivity(), "Load banner failed", Toast.LENGTH_SHORT).show();
            }
        });
// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    public void set(){
        bannerAdapter.setData(banners);
        viewPagerBanner.setAdapter(bannerAdapter);
        circleIndicator.setViewPager(viewPagerBanner);
    }

    public void sliderBanner(){
            handler = new Handler();
            runnable = new Runnable() {
            @Override
            public void run() {
                currentItem = viewPagerBanner.getCurrentItem();
                if(currentItem >= 0){
                    currentItem++;
                    if(currentItem >= viewPagerBanner.getAdapter().getItemCount()){
                        currentItem = 0;
                    }
                    viewPagerBanner.setCurrentItem(currentItem, true);
                    handler.postDelayed(runnable, 5000);
                }
                }
            };
            handler.postDelayed(runnable, 10000);
    }
}
