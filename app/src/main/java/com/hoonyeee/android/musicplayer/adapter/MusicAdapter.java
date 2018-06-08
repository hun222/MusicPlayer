package com.hoonyeee.android.musicplayer.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.hoonyeee.android.musicplayer.R;
import com.hoonyeee.android.musicplayer.domain.Music;
import com.hoonyeee.android.musicplayer.domain.Player;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.Holder> {
    List<Music> musicList;
    Uri musicUri = null;
    public MusicAdapter(List<Music> musicList){
        this.musicList = musicList;
    }
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Music music = musicList.get(position);
        holder.setMusic(music);
    }

    @Override
    public int getItemCount() {
        return musicList.size();
    }

    //1. holder 생성
    public class Holder extends RecyclerView.ViewHolder{
        private ImageView albumart;
        private TextView title, artist, durtation;
        private ImageButton play;
        private Music music;
        private Boolean playFlag;

        public Holder(View itemView) {
            super(itemView);
            //화면이랑 연결
            albumart = itemView.findViewById(R.id.albumart);
            title = itemView.findViewById(R.id.textTitle);
            artist = itemView.findViewById(R.id.textArtist);
            durtation = itemView.findViewById(R.id.duration);
            play = itemView.findViewById(R.id.playButton);
            playFlag = false;
            play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!playFlag) {
                        play.setImageResource(android.R.drawable.ic_media_pause);
                        play(v.getContext());
                    }else {
                        play.setImageResource(android.R.drawable.ic_media_play);
                        pause();
                    }
                }
            });
        }
        public void setMusic(Music music){
            this.music = music;
            setAlbumart();
            setTitle();
            setArtist();
            setDurtation();
        }
        private void setAlbumart(){
            albumart.setImageURI(music.albumart_uri);
        }
        private void setTitle(){
            title.setText(music.title);
        }
        private void setArtist(){
            artist.setText(music.artist);
        }
        private void setDurtation(){
            String m = "";
            String s = "";
            durtation.setText(music.duration+"");
        }
        private void play(Context context){
            playFlag = true;
            if(musicUri != music.music_uri) {
                musicUri = music.music_uri;
                Player.set(context, music.music_uri);
            }
            Player.play();
        }
        private void pause(){
            playFlag = false;
            Player.pause();
        }
    }
}
