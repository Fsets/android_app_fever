package com.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.nft_ticket_andrey.R;
import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 20/04/2022.
 */
public class QRActivity extends AppCompatActivity  {
    Button btnScan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        btnScan = findViewById(R.id.btnScanQR);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scan(view);
            }
        });
    }

    // Register the launcher and result handler
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Intent originalIntent = result.getOriginalIntent();
                    if (originalIntent == null) {
                        Log.d("QRActivity", "Cancelled scan");
                        Toast.makeText(QRActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                    } else if(originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                        Toast.makeText(QRActivity.this, "Cancelled due to missing camera permission", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(QRActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                }
            });

    public void scan(View view) {
        ScanOptions options = new ScanOptions();
        options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
        options.setPrompt("Scan something");
        options.setOrientationLocked(false);
        options.setBeepEnabled(false);
        barcodeLauncher.launch(options);
    }
}
