package com.example.farras23.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.io.IOException;
public class MainActivity extends AppCompatActivity {
    Button btn_play, btn_pause, btn_stop;
    MediaPlayer mPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_play = (Button)findViewById(R.id.button);
        btn_pause = (Button)findViewById(R.id.button2);
        btn_stop = (Button)findViewById(R.id.button3);
        stateAwal();
        btn_play.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                play();
                btn_play.setEnabled(false);
                btn_pause.setEnabled(true);
                btn_stop.setEnabled(true);
            }
        });
        btn_pause.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                pause();
            }
        });
        btn_stop.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                stop();
            }
        });
    }
    public void stateAwal(){
        btn_play.setEnabled(true);
        btn_pause.setEnabled(false);
        btn_stop.setEnabled(false);
    }
    private void play(){
        mPlayer = MediaPlayer.create(this,R.raw.stayalive);
        try {
            mPlayer.prepare();
        }catch (IllegalStateException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
        mPlayer.start();
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion (MediaPlayer mediaPlayer){
                stateAwal();
            }
        });
    }
    public void pause(){
        if (mPlayer.isPlaying())
            if (mPlayer!=null){
                mPlayer.pause();
            }else{
                if (mPlayer!=null){
                    mPlayer.start();
                }
            }
    }
    public void stop(){
        mPlayer.stop();
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
        }catch (Throwable t){
            t.printStackTrace();
        }
        stateAwal();
    }
}