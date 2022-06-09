package com.example.zingmp3.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.zingmp3.R;
import com.example.zingmp3.Adapter.SongInPlaylistAdapter;
import com.example.zingmp3.constant.URL;
import com.example.zingmp3.Model.PlayList;
import com.example.zingmp3.Model.SongInPlaylist;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListSongInPlaylist extends AppCompatActivity {
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private CoordinatorLayout coordinatorLayout;
    private Toolbar toolbar;
    private ImageView imgListSong;
    private RecyclerView rcvListSongInPlayList;
    private FloatingActionButton floatingActionPlay;
    private PlayList playList;
    private SongInPlaylistAdapter inPlayListAdapter;
    private List<SongInPlaylist> songInPlaylists;
    private int numOrder = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_song_in_playlist);
        inPlayListAdapter = new SongInPlaylistAdapter(this);
        songInPlaylists = new ArrayList<>();

        initUI();
        getDataFromIntent();
        setUI();
        loadPlayList();
    }

    public void loadPlayList() {
        RequestQueue queue = Volley.newRequestQueue(this);
        // Request a string response from the provided URL.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL.PLAY_LIST_URL + playList.getPlayListID(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject data = response.getJSONObject("data");
                    JSONObject song = data.getJSONObject("song");
                    JSONArray items = song.getJSONArray("items");
                    for (int i = 0; i < 10; i++) {
                        JSONObject jsonObject = items.getJSONObject(i);
                        numOrder++;
                        String songID = jsonObject.getString("encodeId");
                        String title = jsonObject.getString("title");
                        String thumbnailM = jsonObject.getString("thumbnailM");
                        String artistsNames = jsonObject.getString("artistsNames");
                        SongInPlaylist songInPlaylist = new SongInPlaylist(numOrder, songID, title, thumbnailM, artistsNames);
                        songInPlaylists.add(songInPlaylist);
                        setRCVListSong();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Failed", "Load failed!");
            }
        });
        queue.add(jsonObjectRequest);
    }

    private void setRCVListSong() {
        rcvListSongInPlayList.setLayoutManager(new LinearLayoutManager(this));
        inPlayListAdapter.setData(songInPlaylists);
        rcvListSongInPlayList.setAdapter(inPlayListAdapter);
    }

    private void getDataFromIntent() {
        Intent data = getIntent();
        if (data != null) {
            playList = (PlayList) data.getSerializableExtra("PlayList");
            if (playList != null) {
                setValueInView(playList.getPlayListTitle(), playList.getPlayListThumb());
            }
        }
    }

    private void setValueInView(String playListTitle, String thumbnail) {
        collapsingToolbarLayout.setTitle(playListTitle);
        Picasso.get().load(thumbnail).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                collapsingToolbarLayout.setBackground(new BitmapDrawable(ListSongInPlaylist.this.getResources(), bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
            }
        });
        Picasso.get().load(thumbnail).into(imgListSong);
    }

    private void setUI() {
        // Set chuc nang toolbar thay cho action bar
        setSupportActionBar(toolbar);
        // Tao nut home, tro ve trang truoc
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Set icon
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
    }

    private void initUI() {
        coordinatorLayout = findViewById(R.id.coordinatorLayout);
        collapsingToolbarLayout = findViewById(R.id.collapsingToolbar);
        toolbar = findViewById(R.id.toolbarListSong);
        rcvListSongInPlayList = findViewById(R.id.rcvlistSongInPlayList);
        imgListSong = findViewById(R.id.imgListSong);
        floatingActionPlay = findViewById(R.id.floatingActionPlay);
    }
}