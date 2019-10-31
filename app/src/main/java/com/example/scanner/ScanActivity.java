package com.example.scanner;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.vision.barcode.Barcode;

import java.util.List;

import info.androidhive.barcode.BarcodeReader;

public class ScanActivity extends AppCompatActivity implements BarcodeReader.BarcodeReaderListener {
    BarcodeReader barcodeReader;
    RelativeLayout scannerLayout;
    LinearLayout addLayout;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        scannerLayout = findViewById(R.id.scannerLayout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addLayout.setVisibility(View.VISIBLE);
                scannerLayout.setVisibility(View.GONE);
            }
        });
        barcodeReader = (BarcodeReader) getSupportFragmentManager().findFragmentById(R.id.barcode_scanner);
    }

    @Override
    public void onScanned(Barcode barcode) {

       Toast.makeText(this,barcode.displayValue, Toast.LENGTH_LONG).show();
       Intent intent = new Intent(this,AddEditActivity.class);
       intent.putExtra("value",barcode.rawValue);
       startActivity(intent);


    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
        Toast.makeText(this,"Scanning Error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCameraPermissionDenied() {
        Toast.makeText(this,"Access Denied", Toast.LENGTH_LONG).show();
    }
}
