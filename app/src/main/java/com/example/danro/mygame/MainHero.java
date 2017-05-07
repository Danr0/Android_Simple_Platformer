package com.example.danro.mygame;

import android.content.Context;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;


public class MainHero extends Hero {
    int num;
    int spritetime=15;
    int spritepart=5;
    int spritewidth;
    int spritehight;
    static boolean heroDirection;
    //true-право, false- лево;
    static  float vx;
    float k = (float)1.4;
    static float vy;
    static boolean jump;
    static boolean attack = false;
    boolean heroAttackNow=false;
    static float hp=100;
    MainHero(int x, int y, int size, Context context) {
        super(x, y, size, context, R.drawable.walk_elaine);

    }

    public int jump(int jumppart){if(jumppart==0 && jump)jumppart=1;
        if(jumppart==1)vy=-2;
        if(jumppart==2)vy=2;
return jumppart;
    }

    public void move(boolean leftstop,boolean rightstop,boolean topstop,boolean bottomstop)
    {
        if(vy==0)vy=2;
        if (leftstop && vx<0) vx=0;
        if (rightstop && vx>0) vx=0;
        if (topstop && vy<0) vy=0;
        if (bottomstop && vy>0) vy=0;
        pos.x+=vx;
        pos.y+=vy;
    }

    void appear(Canvas canvas, int time) {
        num=(time/spritetime)%spritepart;
        float k = (float)1.4;
        RectF rect = new RectF(pos.x, pos.y, pos.x + size, pos.y + size * k );
        spritehight=pic.getHeight();
        spritewidth=pic.getWidth()/spritepart;
        Rect sprite=new Rect(num*spritewidth,0,(num+1)*spritewidth,spritehight);
        canvas.drawBitmap(pic, sprite, rect, null);
    }
    boolean attack(MainHero hero,Mob mob, float range){
        if(
                ((hero.pos.y<=mob.pos.y && hero.pos.y>=mob.pos.y+mob.size*k)||
                        (hero.pos.y+hero.size*k<=mob.pos.y && hero.pos.y+hero.size*k>=mob.pos.y+mob.size*k)||
                        (hero.pos.y>=mob.pos.y && hero.pos.y<=mob.pos.y+mob.size*k)||
                        (hero.pos.y+hero.size*k>=mob.pos.y && hero.pos.y+hero.size<=mob.pos.y+mob.size*k))
                        &&
                        ((hero.pos.x<=mob.pos.x+range && hero.pos.x>=mob.pos.x+mob.size-range)||
                                (hero.pos.x+hero.size<=mob.pos.x+range && hero.pos.x+hero.size>=mob.pos.x+mob.size-range)||
                                (hero.pos.x>=mob.pos.x-range && hero.pos.x<=mob.pos.x+mob.size+range)||
                                (hero.pos.x+hero.size>=mob.pos.x-range && hero.pos.x+hero.size<=mob.pos.x+mob.size+range))
                        &&
                        (!heroAttackNow)

                ) {
            heroAttackNow = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000);
                        heroAttackNow = false;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            return true;
        }
        else
            return false;
    }
}
