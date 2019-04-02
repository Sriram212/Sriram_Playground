package com.example.sriram.eggtimer;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import static java.lang.Math.toIntExact;

public class MainActivity extends AppCompatActivity {

    CountDownTimer countDownTimer;

    MediaPlayer mediaPlayer;

    Button button;

    SeekBar seekBar;

    TextView textView;

    int min;

    int sec;

    String time = "";

    public void startStop(View view){

        button = findViewById(R.id.button);

        mediaPlayer = MediaPlayer.create(this, R.raw.foghorn);

        if (button.getText().toString().equals("Start")) {

            countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000, 1000) {

                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onTick(long l) {

                    time = "";

                    sec = toIntExact(l) / 1000;

                    if (sec >= 60) {

                        sec = sec - (60 * min);

                    }

                    if (sec < 0){

                        min = min - 1;


                        sec = sec + 60;

                    }

                    if (sec < 10) {

                        time = "0";

                    }

                    textView.setText(min + ":" + time + sec);

                    button.setText("Stop");

                }

                public void onFinish() {

                    textView.setText("Finished");

                    mediaPlayer.start();

                }

            }.start();
        } else{

            countDownTimer.cancel();

            button.setText("Start");

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button);

        seekBar = findViewById(R.id.seekBar);

        textView = findViewById(R.id.textView);

        seekBar.setMax(600);

        seekBar.setProgress(0);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                min = i/60;

                sec = i;

                time = "";

                textView = findViewById(R.id.textView);

                if (sec >= 60){

                    sec = sec - 60 * min;

                }

                if (sec < 10){

                    time = "0";

                }

                textView.setText(min + ":" + time + sec);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

                if (button.getText().toString().equals("Stop")){

                    countDownTimer.cancel();

                    button.setText("Start");

                }

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
