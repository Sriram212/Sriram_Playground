package com.example.sriram.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void convert(View view){

        Log.i("Info", "Button Pressed");

        TextView euros = findViewById((R.id.euros));

        double dollar = Double.parseDouble(euros.getText().toString()) * 1.23;

        Toast.makeText(this,euros.getText().toString() + "\u20ac" + " is " + String.format("%.2f", dollar) + "$", Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}