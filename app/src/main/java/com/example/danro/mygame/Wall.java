package com.example.danro.mygame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Wall {
    int left, top, right, bottom;
    Paint paint=new Paint();
    Wall(int left, int top,int right,int bottom, Context context) {
        this.left=left;
        this.top=top;
        this.right=right;
        this.bottom=bottom;
    }
    void appear(Canvas canvas) {
        paint.setColor(Color.BLACK);
        canvas.drawRect(left,top,right,bottom,paint);
    }

}