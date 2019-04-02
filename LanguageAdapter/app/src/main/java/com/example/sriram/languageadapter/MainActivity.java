package com.example.sriram.languageadapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    SharedPreferences sharedPreferences;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.english:
                textView.setText("Hello!");
                sharedPreferences.edit().putString("Language", "English").apply();
                Log.i("info", sharedPreferences.getString("Language", "nothing"));
                return true;
            case R.id.french:
                textView.setText("Bonjour!");
                sharedPreferences.edit().putString("Language", "French").apply();
                Log.i("info", sharedPreferences.getString("Language", "nothing"));
                return true;
            default:
                return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.Text);

        sharedPreferences = this.getSharedPreferences("com.example.sriram.languageadapter", Context.MODE_PRIVATE);

        if (sharedPreferences.getString("Language", "null").equals("null")) {

            new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Please choose a language")
                    .setPositiveButton("Englsih", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            textView.setText("Hello!");
                            sharedPreferences.edit().putString("Language", "English").apply();
                        }
                    })
                    .setNegativeButton("Francais", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            textView.setText("Bonjour!");
                            sharedPreferences.edit().putString("Language", "French").apply();
                        }
                    }).show();
        }else{
            switch (sharedPreferences.getString("Language", null)){
                case "English":
                    textView.setText("Hello!");
                    break;
                case "French":
                    textView.setText("Bonjour!");
                    break;
            }
        }
    }
}
