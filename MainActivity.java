package com.example.week8_notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> noteList = new ArrayList<String>();

    public void createNewNote(View view){
        Button createNewNoteButton = findViewById(R.id.newNoteButton);
        Intent createNote = new Intent(this,MainActivity.class);

        EditText titelTextField = findViewById(R.id.titelTextFieldWriter);
        String titelTextString = titelTextField.getText().toString();

        noteList.add(titelTextString);
        Toast.makeText(this,"New Note Created!",Toast.LENGTH_LONG).show();
        Log.i("List",noteList.get(0));
        onResume();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        SharedPreferences sharedPreferences = getSharedPreferences("com.example.week8_notebook",Context.MODE_PRIVATE);
        String titels = sharedPreferences.getString("titel","");
        




        System.out.println(sharedPreferences.getAll());


        ListView listView = findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, noteList);
        listView.setAdapter(arrayAdapter);




    }
    @Override
    protected void onResume(){
        super.onResume();

        final ListView listView = findViewById(R.id.listView);
        ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, noteList);
        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent go = new Intent(MainActivity.this,noteWriter.class);
                startActivity(go);
                String titelFromMain = noteList.get(position);
                EditText titelTextFieldWriter = findViewById(R.id.titelTextFieldWriter);
                titelTextFieldWriter.setText(titelFromMain);

                SharedPreferences sharedPreferences = getSharedPreferences("com.example.week8_notebook", Context.MODE_PRIVATE);
                sharedPreferences.edit().putString("titel",titelFromMain).apply();

                Log.i("MinListe",""+position+"id"+id);
                Log.i("MinListe",""+titelFromMain);
            }
        });

    }
}
