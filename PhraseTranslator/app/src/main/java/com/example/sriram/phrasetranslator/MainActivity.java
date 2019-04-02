package com.example.sriram.phrasetranslator;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public void onClick(View view){

        MediaPlayer mediaPlayer;

        Button doYouSpeakEnglish = (Button) findViewById(R.id.doYouSpeakEnglish);
        Button goodEvening = (Button) findViewById(R.id.goodEvening);
        Button hello = (Button) findViewById(R.id.Hello);
        Button howAreYou = (Button) findViewById(R.id.howAreYou);
        Button iLiveIn = (Button) findViewById(R.id.iLiveIn___);
        Button myNameIs = (Button) findViewById(R.id.myNameIs___);
        Button please = (Button) findViewById(R.id.please);
        Button welcome = (Button) findViewById(R.id.welcome);

        Log.i("Info", "Button Pressed");

        if (doYouSpeakEnglish.isPressed()){

            mediaPlayer = MediaPlayer.create(this, R.raw.doyouspeakenglish);

            mediaPlayer.pause();

            mediaPlayer.start();

        }

        if (goodEvening.isPressed()){

            mediaPlayer = MediaPlayer.create(this, R.raw.goodevening);

            mediaPlayer.pause();

            mediaPlayer.start();

        }

        if (hello.isPressed()){

            mediaPlayer = MediaPlayer.create(this, R.raw.hello);

            mediaPlayer.pause();

            mediaPlayer.start();

        }

        if (howAreYou.isPressed()){

            mediaPlayer = MediaPlayer.create(this, R.raw.howareyou);

            mediaPlayer.pause();

            mediaPlayer.start();

        }


        if (iLiveIn.isPressed()){

            mediaPlayer = MediaPlayer.create(this, R.raw.ilivein);

            mediaPlayer.pause();

            mediaPlayer.start();

        }


        if (myNameIs.isPressed()){

            mediaPlayer = MediaPlayer.create(this, R.raw.mynameis);

            mediaPlayer.pause();

            mediaPlayer.start();

        }

        if (please.isPressed()){

            mediaPlayer = MediaPlayer.create(this, R.raw.please);

            mediaPlayer.pause();

            mediaPlayer.start();

        }

        if (welcome.isPressed()){

            mediaPlayer = MediaPlayer.create(this, R.raw.welcome);

            mediaPlayer.pause();

            mediaPlayer.start();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
