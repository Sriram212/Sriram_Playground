package com.example.sriram.notesapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import java.security.Key;

public class NotesActivity extends AppCompatActivity {

    TextView textView;

    TextView textView2;

    static SharedPreferences sharedPreferences;

    String i;

    int x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        Intent intent = getIntent();

        x = intent.getIntExtra("Note Number 2", -1);

        i = intent.getStringExtra("Note Number");

        textView = findViewById(R.id.plainText);

        textView2 = findViewById(R.id.plainText2);

        textView.setText(sharedPreferences.getString("notes" + i, ""));

        textView2.setText(sharedPreferences.getString("Title" + i, ""));

        Log.i("Value of i: ", i);

        Log.i("sharedPreferences", sharedPreferences.getAll().toString());

        textView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int j, KeyEvent keyEvent) {
                if (keyEvent.getAction()!=KeyEvent.ACTION_DOWN)
                    return false;
                if(j == KeyEvent.KEYCODE_ENTER ){
                    //Save the text
                    Log.i("Text: ", textView.getText().toString());
                    sharedPreferences.edit().putString("notes" + textView2.getText().toString(), textView.getText().toString()).apply();
                    return true;
                }
                return false;
            }
        });

        textView2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int j, KeyEvent keyEvent) {
                if (keyEvent.getAction()!=KeyEvent.ACTION_DOWN)
                    return false;
                if(j == KeyEvent.KEYCODE_ENTER ){
                    //Save the text
                    Log.i("Text: ", textView2.getText().toString());
                    boolean b = true;
                    for (int i = 0; i < MainActivity.arrayList.size(); i++){
                        if (textView2.getText().toString().equals(MainActivity.arrayList.get(i))){
                            new AlertDialog.Builder(NotesActivity.this).setTitle("Same Title")
                                                                                .setIcon(android.R.drawable.ic_dialog_alert)
                                                                                .setMessage("Can't have the same title as another note!")
                                                                                .setPositiveButton("Okay", null)
                                                                                .show();
                            textView2.setText("");
                            textView2.setCursorVisible(true);
                            b = false;
                        }
                    }
                    if (b) {
                        MainActivity.arrayList.set(x, textView2.getText().toString());
                        MainActivity.arrayAdapter.notifyDataSetChanged();
                        i = textView2.getText().toString();
                        sharedPreferences.edit().putString("Title" + i, textView2.getText().toString()).apply();

                        return true;
                    }
                }
                return false;
            }
        });
    }
}
