package com.example.sriram.connect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //0 = red; 1 = yellow; 2 = empty

    boolean gameActive = true;

    int player = 0;

    int[] gamestate = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int[][] winningPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int counterPosition = Integer.parseInt(counter.getTag().toString());

        if (gameActive && gamestate[counterPosition] == 2) {

            counter.setTranslationY(-1500);

            gamestate[counterPosition] = player;

            if (player == 0) {

                counter.setImageResource(R.drawable.red);

                player = 1;

            } else {

                counter.setImageResource(R.drawable.yellow);

                player = 0;

            }

            counter.animate().translationYBy(1500).setDuration(500);

            for (int[] array : winningPositions) {

                if (gamestate[array[0]] == gamestate[array[1]] && gamestate[array[1]] == gamestate[array[2]] && gamestate[array[0]] != 2) {

                    //Someone Won

                    if (player == 1) {

                        Toast.makeText(this, "Red won", Toast.LENGTH_LONG).show();

                        gameActive = false;

                    } else {

                        gameActive = false;

                        Toast.makeText(this, "Yellow won", Toast.LENGTH_LONG).show();

                    }

                    Button button = findViewById(R.id.button);

                    button.setVisibility(View.VISIBLE);

                }

            }

        }
    }

    public void restart(View view){

        Button button = findViewById(R.id.button);

        button.setVisibility(View.INVISIBLE);

        gameActive = true;

        player = 0;

        android.support.v7.widget.GridLayout gridLayout = (android.support.v7.widget.GridLayout)findViewById(R.id.gridLayout);

        for (int j = 0; j < gridLayout.getChildCount(); j++){

            ImageView counter = (ImageView) gridLayout.getChildAt(j);

            counter.setImageDrawable(null);

        }

        for (int i = 0; i < gamestate.length; i++){

            gamestate[i] = 2;

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
