package com.example.zingmp3.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zingmp3.R;
import com.example.zingmp3.api.ApiService;
import com.example.zingmp3.model.Song;
import com.example.zingmp3.model.ZingChart;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlaySong extends AppCompatActivity implements View.OnClickListener {
    private ImageView btnBackToHome, btnPlaySong, btnPause, btnNext, btnPrevious, btnShuffle, btnRepeat;
    private TextView tvSongName, tvArtistOfSong, tvTimeSong, tvTotalTimeSong;
    private ViewPager viewPagerPlaySong;
    private SeekBar seekBarPlaySong;
    private ZingChart zingChart;
    private MediaPlayer mediaPlayer;
    private ArrayList<ZingChart> listSongFromZingChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        initUI();
        getDataFromIntent();



        tvSongName.setText(zingChart.getZcTitle());
        tvArtistOfSong.setText(zingChart.getZcArtist());
//        Picasso.get().load(zingChart.getZcThumb()).into(ivSongThumb);

          btnPlaySong.setOnClickListener(this::onClick);
//        btnPause.setOnClickListener(this::onClick);
//        btnResume.setOnClickListener(this::onClick);
          btnBackToHome.setOnClickListener(this::onClick);
    }

    private void getDataFromIntent() {
        Intent intent = getIntent();
        if(intent == null){
            Toast.makeText(this, "Get intent data failed", Toast.LENGTH_SHORT).show();
        }
        if(intent.hasExtra("Song")) {
            zingChart = intent.getParcelableExtra("Song");
            Toast.makeText(this, "" + zingChart.getZcTitle(), Toast.LENGTH_SHORT).show();
        }
        if(intent.hasExtra("ListSong")) {
            listSongFromZingChart = intent.getParcelableArrayListExtra("ListSong");
            for (ZingChart z : listSongFromZingChart){
                Log.i("test", z.getZcTitle());
            }
        }
    }

    private void initUI() {
        btnBackToHome = findViewById(R.id.btnBackToHome);
        btnPlaySong = findViewById(R.id.btnPlay);
        btnShuffle = findViewById(R.id.btnShuffle);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnPlaySong = findViewById(R.id.btnPlay);
        btnNext = findViewById(R.id.btnNext);
        btnRepeat = findViewById(R.id.btnRepeat);
        tvSongName = findViewById(R.id.tvSongName);
        tvArtistOfSong = findViewById(R.id.tvArtistOfSong);
        tvTimeSong = findViewById(R.id.tvTimeSong);
        tvTotalTimeSong = findViewById(R.id.tvTotalTimeSong);
        seekBarPlaySong = findViewById(R.id.seekBarSong);
        viewPagerPlaySong = findViewById(R.id.viewPagerPlaySong);
    }

    public void getSong(){
        Call<Song> songCall = ApiService.apiService.getSong(zingChart.getZcID());
        songCall.enqueue(new Callback<Song>() {
            @Override
            public void onResponse(Call<Song> call, Response<Song> response) {
                Song song = response.body();
                if(response.isSuccessful() && song != null){
                    playAudio(song.getData().get128());
                }
            }
            @Override
            public void onFailure(Call<Song> call, Throwable t) {
                Toast.makeText(PlaySong.this, "Load failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void playAudio(String url){
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(PlaySong.this, "Audio started playing..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnPlay:
                getSong();

                break;
//            case R.id.btnPause:
//                if(mediaPlayer.isPlaying()){
//                    mediaPlayer.pause();
//                }
//                break;
//            case R.id.btnResume:
//                if(!mediaPlayer.isPlaying()){
//                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition());
//                    mediaPlayer.start();
//                }
//                break;
            case R.id.btnBackToHome:
                finish();
                break;
        }
    }
}