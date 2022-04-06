package com.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nft_ticket_andrey.R;

/**
 * Created by Cristian Mármol cristian.marmol@occamcomunicacion.com on 31/03/2022.
 */
public class AnimActivity extends AppCompatActivity {
    ImageView logoImageView;
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        //agregar animacion
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);
        logoImageView = findViewById(R.id.ImgVlogo);

        logoImageView.setAnimation(animation1);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(AnimActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 3000);
    }
}
