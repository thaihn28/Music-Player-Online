package com.example.zingmp3.constant;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.zingmp3.interfaces.ICallBackJsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Json {
    private static Json getInstance;
    private String url = "https://music-player-pink.vercel.app/api/home?page=1";
    private ICallBackJsonArray iCallBackJsonArray;
    public static RequestQueue testQueue;

    public static Json getInstance(Context context){
        if(getInstance == null){
//            getInstance = new Json(getInstance.iCallBackJsonArray);
            getInstance = new Json();
            testQueue = Volley.newRequestQueue(context);
        }
        return getInstance;
    }
//    public Json(ICallBackJsonArray iCallBackJsonArray) {
//        this.iCallBackJsonArray = iCallBackJsonArray;
//    }

    public void loadJsonArray(Context context, final ICallBackJsonArray iCallBackJsonArray) {
        RequestQueue queue = com.android.volley.toolbox.Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONArray parentsItem = data.getJSONArray("items");
//                    iCallBackJsonArray.returnJsonArray(parentsItem);
                    iCallBackJsonArray.returnJsonArray(parentsItem);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
    }
    public BlockingQueue<JSONArray> test(Context context) {
        final BlockingQueue<JSONArray> blockingQueue = new ArrayBlockingQueue<>(1);
        RequestQueue queue = com.android.volley.toolbox.Volley.newRequestQueue(context);
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONArray parentsItem = data.getJSONArray("items");
//                    iCallBackJsonArray.returnJsonArray(parentsItem);
                    blockingQueue.add(parentsItem);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
// Add the request to the RequestQueue.
        queue.add(jsonObjectRequest);
        return blockingQueue;
    }
}
