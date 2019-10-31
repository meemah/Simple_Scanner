package com.example.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ItemViewModel itemViewModel;
    private FloatingActionButton addFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFab = findViewById(R.id.fab);
        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent scannerIntent = new Intent(MainActivity.this,ScanActivity.class);
               // startActivityForResult(scannerIntent,1);
                startActivity(  scannerIntent);
            }
        });
        itemViewModel = new ViewModelProvider(this).get(ItemViewModel.class);
        itemViewModel.getAllItems().observe(this, new Observer<List<Item>>() {
            @Override
            public void onChanged(List<Item> items) {
                Toast.makeText(MainActivity.this, "Observed",Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK){
            String Id = data.getStringExtra("Id");
            String name = data.getStringExtra("name");
            String desc = data.getStringExtra("description");

            int itemId = Integer.parseInt(Id);
            Item item = new Item(itemId,name,desc);
            itemViewModel.insert(item);
            Toast.makeText(this,"Success in adding",Toast.LENGTH_LONG).show();

        }
    }
}
