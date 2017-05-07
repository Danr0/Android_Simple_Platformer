package com.example.danro.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.os.Build;
import android.support.annotation.Dimension;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import static com.example.danro.mygame.MainHero.jump;
import static com.example.danro.mygame.MainHero.vx;


class Surface extends SurfaceView implements SurfaceHolder.Callback{

    static int jumppart;
    Map map;
    static int width,height;

    public Surface(Context context) {
        super(context);

        getHolder().addCallback(this);
    }

    public Surface(Context context, AttributeSet attrs) {
        super(context, attrs);

        getHolder().addCallback(this);
    }

    public Surface(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        getHolder().addCallback(this);
    }

      //Character character;
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
// Character character = new Character();
// while (true){
// Canvas canvas=getHolder().lockCanvas();
// mainHero.appear(canvas);
// mainHero.move();
// getHolder().unlockCanvasAndPost(canvas);
// }


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        //  character = new Character(getContext(),getHolder());
        //character.start();


        Canvas canvas=holder.lockCanvas();
       height= canvas.getHeight();
        width =canvas.getWidth();

        holder.unlockCanvasAndPost(canvas);
        jumppart = 0;
        map = new Map(getContext(), getHolder());
                map.start();

//            else if (youWin && map.isAlive()){
//                map.interrupt();
//                Log.d("DanichkruT","Thread is destroyed");
//                map.start();
//                Log.d("DanichkruT","Thread is started2");
//                youWin=false;
//            }

        }
//    public void MyScreenSize() {
//        Point size = new Point();
//        WindowManager w = getWindowManager();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
//            w.getDefaultDisplay().getSize(size);
//
//            width = size.x;
//            height = size.y;
//        } else {
//            Display d = w.getDefaultDisplay();
//            width = d.getWidth();
//            height = d.getHeight();
//        }
//    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if(x<400) vx=-2;
            if(x>800) vx=2;
            if(x>=400 && x<=800) jump=true;
        } if (event.getAction() == MotionEvent.ACTION_UP) {
            vx=0;
            vx=0;
            jump=false;
        }

        return true;
    }


    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
