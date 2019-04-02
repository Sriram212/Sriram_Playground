package com.example.sriram.notesapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<String> arrayList;

    static ArrayAdapter<String> arrayAdapter;

    ListView listView;

    SharedPreferences sharedPreferences;

    private int noteNumber = 2;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void Save(View view){
        try {
            sharedPreferences.edit().putString("ArrayList", ObjectSerializer.serialize(arrayList)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        switch (item.getItemId()){
            case R.id.newNote:
                arrayList.add("New Note " + noteNumber);
                arrayAdapter.notifyDataSetChanged();
                noteNumber++;
                return true;
            default: return false;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = this.getSharedPreferences("com.example.sriram.notesapp", Context.MODE_PRIVATE);

        NotesActivity.sharedPreferences = this.getSharedPreferences("com.example.sriram.notesapp", Context.MODE_PRIVATE);

        listView = findViewById(R.id.listView);

        arrayList = new ArrayList<>();

        if (sharedPreferences.getString("ArrayList", "null") != "null"){
            try {
                arrayList = (ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("ArrayList", ObjectSerializer.serialize(new ArrayList<String>())));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            arrayList.add("New Note");
        }

        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        listView.setAdapter(arrayAdapter);

        try {
            sharedPreferences.edit().putString("ArrayList", ObjectSerializer.serialize(arrayList));
        } catch (IOException e) {
            e.printStackTrace();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), NotesActivity.class);
                intent.putExtra("Note Number", arrayList.get(i).toString());
                Log.i("Note Name", arrayList.get(i).toString());
                intent.putExtra("Note Number 2", i);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final int position = i;
                new AlertDialog.Builder(MainActivity.this).setIcon(android.R.drawable.ic_dialog_alert)
                                                                .setTitle("Are You Sure?")
                                                                .setMessage("A deleted note can't be recovered")
                                                                .setPositiveButton("No", null)
                                                                .setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialogInterface, int j) {
                                                                        arrayList.remove(position);
                                                                        arrayAdapter.notifyDataSetChanged();
                                                                        NotesActivity.sharedPreferences.edit().remove("notes" + position).apply();
                                                                        NotesActivity.sharedPreferences.edit().remove("Title" + position).apply();
                                                                    }
                                                                }).show();
                return true;
            }
        });
    }
}
