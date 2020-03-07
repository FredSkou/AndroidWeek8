package com.example.week8_notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class noteWriter extends AppCompatActivity {


    public void saveNote(View view){

        EditText titelTextField = findViewById(R.id.titelTextFieldWriter);
        String titelTextString = titelTextField.getText().toString().toLowerCase();

        EditText notesTextField = findViewById(R.id.noteTextField);
        String notesTextFieldString = notesTextField.getText().toString();


        SharedPreferences sharedPreferences = this.getSharedPreferences("com.example.week8_notebook", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(titelTextString,notesTextFieldString).apply();

        Toast.makeText(this,"Note Saved!",Toast.LENGTH_LONG).show();
    }
    public void returnToList(View view){
        Intent goBack = new Intent(this,MainActivity.class);
        startActivity(goBack);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_writer);

        //Set the Title
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.week8_notebook",Context.MODE_PRIVATE);
        String titel=sharedPreferences.getString("titel","");
        EditText titelTextFieldWriter = findViewById(R.id.titelTextFieldWriter);
        titelTextFieldWriter.setText(titel);

        //Set the note text
        EditText titelTextField = findViewById(R.id.titelTextFieldWriter);
        String titelTextString = titelTextField.getText().toString().toLowerCase();
        String notes = sharedPreferences.getString(titelTextString,"");
        EditText notesTextField = findViewById(R.id.noteTextField);
        notesTextField.setText(notes);

        Log.i("MinListe","Titel"+titel);




    }
}
