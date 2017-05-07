package com.example.danro.mygame;

import android.content.Context;



public class Mob extends Hero {
    Mob(int x, int y, int size, int hp,int v, Context context) {
        super(x, y, size, context, R.drawable.ninja);
        this.hp=hp;
        mobv=v;
    }

    float k = (float)1.4;
    boolean attackNow= false;
    float mobv;
int hp;
int floorNumber;
    boolean agr(MainHero hero,Mob mob){
        if(
                (hero.pos.y<=mob.pos.y && hero.pos.y>=mob.pos.y+mob.size*k)||
            (hero.pos.y+hero.size*k<=mob.pos.y && hero.pos.y+hero.size*k>=mob.pos.y+mob.size*k)||
        (hero.pos.y>=mob.pos.y && hero.pos.y<=mob.pos.y+mob.size*k)||
        (hero.pos.y+hero.size*k>=mob.pos.y && hero.pos.y+hero.size<=mob.pos.y+mob.size*k)


                )return true;
else
        return false;
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
                        (!attackNow)

                ) {
            attackNow = true;
           new Thread(new Runnable() {
               @Override
               public void run() {
                   try {
                       Thread.sleep(1000);
                       attackNow = false;
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
//    void stop(boolean left,boolean right){
//        if (left && mobv<0)mobv=0;
//        if (right && mobv>0)mobv=0;
//
//    }

}
