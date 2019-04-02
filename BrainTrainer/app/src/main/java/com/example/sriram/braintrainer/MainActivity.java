package com.example.sriram.braintrainer;

import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

import static java.lang.Math.toIntExact;

public class MainActivity extends AppCompatActivity {

    Button playAgain;

    TextView finalScore;

    boolean isPressed = true;

    android.support.v7.widget.GridLayout gridLayout;

    boolean endLoop;

    int correct = 0;

    int no_of_problems = 0;

    int option;

    TextView time;

    TextView problem;

    TextView correct_wrong;

    Button go;

    Button option1;

    Button option2;

    Button option3;

    Button option4;

    int sec;

    Random random;

    int prob1;

    int prob2;

    int correctAnswer;

    Random random1;

    Random random2;

    public void go(View view) {

            finalScore = findViewById(R.id.FinalScore);

            problem = findViewById(R.id.problem);

            correct_wrong = findViewById(R.id.correct_wrong);

            option1 = findViewById(R.id.option1);

            option2 = findViewById(R.id.option2);

            option3 = findViewById(R.id.option3);

            option4 = findViewById(R.id.option4);

            gridLayout = (android.support.v7.widget.GridLayout) findViewById(R.id.gridLayout);

            time = findViewById(R.id.time);

            go = findViewById(R.id.go);

            option1.setClickable(true);

            option2.setClickable(true);

            option3.setClickable(true);

            option4.setClickable(true);

            finalScore.setVisibility(View.INVISIBLE);

            playAgain.setVisibility(View.INVISIBLE);

            go.setVisibility(View.INVISIBLE);

            problem.setVisibility(View.VISIBLE);

            correct_wrong.setVisibility(View.VISIBLE);

            gridLayout.setVisibility(View.VISIBLE);

            time.setVisibility(View.VISIBLE);

            if (isPressed) {

                new CountDownTimer(30000, 1000) {
                    @RequiresApi(api = Build.VERSION_CODES.N)
                    @Override
                    public void onTick(long l) {

                        sec = toIntExact(l) / 1000;

                        time.setText(Integer.toString(sec) + "s");

                    }

                    @Override
                    public void onFinish() {

                        option1.setClickable(false);

                        option2.setClickable(false);

                        option3.setClickable(false);

                        option4.setClickable(false);

                        playAgain.setVisibility(View.VISIBLE);

                        finalScore.setVisibility(View.VISIBLE);

                        finalScore.setText("Final Score: " + correct_wrong.getText().toString());

                    }
                }.start();

            }

            isPressed = false;

            random = new Random();

            prob1 = random.nextInt(101);

            prob2 = random.nextInt(101);

            problem.setText(Integer.toString(prob1) + " + " + Integer.toString(prob2));

            correctAnswer = prob1 + prob2;

            random1 = new Random();

            int correctOption = random1.nextInt(4) + 1;

            switch (correctOption) {

                case 1:
                    option1.setText(Integer.toString(correctAnswer));
                    break;

                case 2:
                    option2.setText(Integer.toString(correctAnswer));
                    break;

                case 3:
                    option3.setText(Integer.toString(correctAnswer));
                    break;

                case 4:
                    option4.setText(Integer.toString(correctAnswer));
                    break;

            }

            random2 = new Random();

            if (correctOption != 1) {

                option = random2.nextInt(101);

                while (option == correctAnswer){

                    option = random2.nextInt(101);

                }

                option1.setText(Integer.toString(option));

            }

            if (correctOption != 2) {

                option = random2.nextInt(101);

                while (option == correctAnswer){

                    option = random2.nextInt(101);

                }

                option2.setText(Integer.toString(option));

            }

            if (correctOption != 3) {

                option = random2.nextInt(101);

                while (option == correctAnswer){

                    option = random2.nextInt(101);

                }

                option3.setText(Integer.toString(option));

            }

            if (correctOption != 4) {

                option = random2.nextInt(101);

                while (option == correctAnswer){

                    option = random2.nextInt(101);

                }

                option4.setText(Integer.toString(option));

            }
    }

    public void optionClick(View view){

        if (option1.isPressed()){

            if (option1.getText().toString().equals(Integer.toString(correctAnswer))){

                correct++;

                go.performClick();

            } else {

                go.performClick();

            }

        }

        if (option2.isPressed()){

            if (option2.getText().toString().equals(Integer.toString(correctAnswer))){

                correct++;

                go.performClick();

            }else {

                go.performClick();

            }

        }

        if (option3.isPressed()){

            if (option3.getText().toString().equals(Integer.toString(correctAnswer))){

                correct++;

                go.performClick();

            } else {

                go.performClick();

            }

        }

        if (option4.isPressed()){

            if (option4.getText().toString().equals(Integer.toString(correctAnswer))){

                correct++;

                go.performClick();

            } else {

                go.performClick();

            }

        }

        no_of_problems++;

        correct_wrong.setText(Integer.toString(correct) + "/" + no_of_problems);

    }

    public void playAgainMethod(View view){

        isPressed = true;

        correct = 0;

        no_of_problems = 0;

        correct_wrong.setText("0/0");

        go.performClick();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgain = findViewById(R.id.playAgain);

        endLoop = false;

    }
}
