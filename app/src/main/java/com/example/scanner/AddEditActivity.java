package com.example.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class AddEditActivity extends AppCompatActivity {
    TextView idTextView;
    TextInputEditText nameText,descriptionText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);
        Intent intent = getIntent();
        String string = intent.getStringExtra("value");

        idTextView =  findViewById(R.id.idText);
        idTextView.setText(string);
        nameText = findViewById(R.id.nameText);
        String itemName = nameText.getText().toString();

        descriptionText = findViewById(R.id.descriptionText);
        String itemDescription = descriptionText.getText().toString();

        Intent addIntent = new Intent();
        addIntent.putExtra("Id",string);
        addIntent.putExtra("name",itemName);
        addIntent.putExtra("description",itemDescription);
        setResult(RESULT_OK,addIntent);
        finish();

    }
}
