package com.example.danro.mygame;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Surface;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import static com.example.danro.mygame.MainHero.jump;
import static com.example.danro.mygame.MainHero.vx;
import static com.example.danro.mygame.MainHero.vy;
import static com.example.danro.mygame.MainHero.attack;
import static com.example.danro.mygame.MainHero.heroDirection;


public class MainActivity extends AppCompatActivity implements View.OnTouchListener {
    boolean right;
    boolean left;
    boolean leftstop;
    boolean rightstop;
    boolean topstop;
    boolean bottomstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        // setContentView(new com.example.danro.mygame.Surface(this));

        Button jumpBut = (Button) findViewById(R.id.JumpBut);
        Button leftBut = (Button) findViewById(R.id.leftBut);
        Button rightBut = (Button) findViewById(R.id.rightBut);
        Button downBut = (Button) findViewById(R.id.DownBut);
        Button aBut = (Button) findViewById(R.id.Abut);


        downBut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    vy = 2;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    vy = 0;
                }
                return true;
            }
        });

        rightBut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    vx = 2;
                    heroDirection=true;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    vx = 0;
                }
                return true;
            }
        });

        leftBut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    vx = -2;
                    heroDirection=false;
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    vx = 0;
                }
                return true;
            }
        });

        jumpBut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    jump=true;


                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    jump=false;
                }
                return true;
            }
        });
        aBut.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {

attack=true;

                } else if (event.getAction() == MotionEvent.ACTION_UP) {

                }
                return true;
            }
        });


    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();


            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                vy = -5;

            } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                vy = 0;
            }
        return true;
    }
}