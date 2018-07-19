package com.example.abmoiz.musicplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mp;
    SeekBar sb;
    int Seek_VALUE=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mp=new MediaPlayer();
        sb=(SeekBar) findViewById(R.id.SEEK_BAR);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                Seek_VALUE=i;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mp.seekTo(Seek_VALUE);
            }
        });



        SeekTHread SKT= new SeekTHread();
        SKT.start();
    }

    public void PlayMedia(View view) {

        try{
            mp=new MediaPlayer();
            mp.setDataSource("http://s6.faz-dl.top/user1/top/april2018/Rock%20Classics%20The%20Collection%20-%20MP3%20320/212.%20Cats%20In%20The%20Cradle%20-%20Harry%20Chapin.mp3");
            mp.prepare();
            mp.start();
            sb.setMax(mp.getDuration());

        }
        catch(Exception e){
            e.printStackTrace();
        }



    }

    public void PauseMedia(View view) {
       if(mp.isPlaying()){

           mp.pause();
       }


    }

    public void StopMedia(View view) {
       if(mp.isPlaying()){
           mp.release();
           mp=null;

       }
    }

    class SeekTHread extends Thread{
      public void run(){
          while (true){
              try {
                  Thread.sleep(1000);

              }catch (Exception ex){
                ex.printStackTrace();
              }
              runOnUiThread(new Runnable() {
                  @Override
                  public void run() {
                      if(mp!=null){
                          sb.setProgress(mp.getCurrentPosition());
                      }
                  }
              });
          }

      }


    }



}
