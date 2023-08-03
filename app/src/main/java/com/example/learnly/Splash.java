package com.example.learnly;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Splash extends AppCompatActivity {

    ImageView slogo;
    TextView sname,sfrom, s;
    View bbk;
    Animation top_animation,bottom_animation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        slogo = findViewById(R.id.slogo);
        sname = findViewById(R.id.sname);
        sfrom = findViewById(R.id.sfrom);
        s = findViewById(R.id.s);
        bbk = findViewById(R.id.bbk);

        top_animation = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottom_animation = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        slogo.setAnimation(bottom_animation);
        sname.setAnimation(top_animation);
        sfrom.setAnimation(bottom_animation);
        s.setAnimation(bottom_animation);
        bbk.setAnimation(top_animation);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }
}