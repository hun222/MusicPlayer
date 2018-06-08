package com.hoonyeee.android.musicplayer;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hoonyeee.android.musicplayer.adapter.MusicAdapter;
import com.hoonyeee.android.musicplayer.domain.Music;
import com.hoonyeee.android.musicplayer.domain.MusicLoader;

import java.util.List;

public class MainActivity extends BaseActivity {
    MusicAdapter adapter;
    RecyclerView recyclerView;

    public MainActivity() {
        super(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE});
    }

    @Override
    public void init() {
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        List<Music> musicList = MusicLoader.getMusic(this);
        adapter = new MusicAdapter(musicList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
