package com.example.sriram.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int random1;

    public void generateRandomNumber(){

        Random random = new Random();

        random1 = random.nextInt(20) + 1;

    }

    public void guess(View view){

        EditText guess = findViewById(R.id.editText);

        int guessInt = Integer.parseInt(guess.getText().toString());

        if (guessInt > random1){

            Toast.makeText(this, "Lower", Toast.LENGTH_LONG).show();

            guess.setText("");

        } else if (guessInt < random1){

            Toast.makeText(this, "Higher", Toast.LENGTH_LONG).show();

            guess.setText("");

        } else if (guessInt == random1){

            Toast.makeText(this, "You Got it! Now try again", Toast.LENGTH_LONG).show();

            guess.setText("");

            generateRandomNumber();

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        generateRandomNumber();
    }
}
