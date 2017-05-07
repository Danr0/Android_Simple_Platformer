package com.example.danro.mygame;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Paint;
        import android.graphics.RectF;
        import android.util.Log;
        import android.view.SurfaceHolder;
        import android.widget.Button;

        import java.util.ArrayList;

        import static com.example.danro.mygame.MainHero.heroDirection;
        import static com.example.danro.mygame.MainHero.hp;
        import static com.example.danro.mygame.MainHero.attack;
        import static com.example.danro.mygame.Surface.height;
        import static com.example.danro.mygame.Surface.width;


public class Map extends Thread {

    int time=0;
    static int jumppart;
    static int maxy;
    int jumpsize=120;
    private SurfaceHolder surfaceHolder;
    private volatile boolean running = true;//флаг для остановки потока

    public Map(Context context, SurfaceHolder surfaceHolder) {
            this.surfaceHolder = surfaceHolder;
        init(context);
//        Log.d("DanichkruT","Constructured");
    }
    void init(Context context ) {
       Log.d("DanichkruT","Init");
                nature = BitmapFactory.decodeResource(context.getResources(), R.drawable.nature);
                mobs = new ArrayList<Mob>();
                walls = new ArrayList<Wall>();
                walls.add(0, new Wall(0, height, 30, 0, context));
                walls.add(1, new Wall(width-30, height, width, 0, context));
                //walls.add(2, new Wall(100, 100, 130, 70, context));
                //walls.add(3, new Wall(0,600,1080,500,context));
                ymap = 0;

                while (ymap < height) {
                    Log.d("DanichkruT","while");
                    int x = (int) (Math.random() * 0.88*width);
                    if (!spawn) {
                        mainHero = new MainHero(x + 10, ymap, size, context);
                        spawn = true;
                    }

                    walls.add(flat, new Wall(0, ymap + 10, x, ymap, context));
                    walls.add(flat + 1, new Wall(x + 200, ymap + 10, width, ymap, context));
                    // boolean goodSpawn=false;
//            while (!goodSpawn){
//                if (
//                        (xmobI >= (x + 50) && xmobI <= (x + 150))
//                        ||
//                        (xmobI <= 30)
//                        || (xmobI >= 1890)
//                        ) {
//                    xmobF=(float)Math.random()*1890;
//                    xmobI=(int)xmobF;
//
//                }
//                else {
//                    goodSpawn=true;
//                }
//
//            }

                    ymap += 100;
                    flat += 2;
                }
                for (int i = 0; i != walls.size() / 2 - 1; i++) {
                    Log.d("DanichkruT","cycle started");
                    boolean goodSpawn = false;
                    float xmobF = (float) Math.random() * (width-30);
                    int xmobI = (int) xmobF;

                    while (!goodSpawn) {
                        if (xmobI >= walls.get(2 * i).right + 50 && xmobI <= walls.get(2 * i + 1).left - 50) {
                            xmobF = (float) Math.random() * (width-30);
                            xmobI = (int) xmobF;
                        } else {
                            goodSpawn = true;
                        }
                    }
                    mobs.add(i, new Mob(xmobI, walls.get(2 * i).top - 80, 50, 100, 0, context));
                    Log.d("DanichkruT","added");
                    mobs.get(i).floorNumber = i;
                }


                //(float)mainHero.pic.getHeight()/mainHero.pic.getWidth();
//        Log.d("DanichkruT","Inited");

    }

    void update(){
        while (ymap < height) {
            int x = (int) (Math.random() * 0.88*width);
            if (!spawn) {
                //mainHero = new MainHero(x + 10, ymap, size, context);
                mainHero.pos.x=x+10;
                mainHero.pos.y=ymap;
                spawn = true;
            }

           // walls.add(flat, new Wall(0, ymap + 10, x, ymap, context));
            walls.get(flat).left=0;
            walls.get(flat).top=ymap+10;
            walls.get(flat).right=x;
            walls.get(flat).bottom=ymap;
           // walls.add(flat + 1, new Wall(x + 200, ymap + 10, 1920, ymap, context));
            walls.get(flat+1).left=x+200;
            walls.get(flat=1).top=ymap+10;
            walls.get(flat+1).right=width;
            walls.get(flat+1).bottom=ymap;
            // boolean goodSpawn=false;
//            while (!goodSpawn){
//                if (
//                        (xmobI >= (x + 50) && xmobI <= (x + 150))
//                        ||
//                        (xmobI <= 30)
//                        || (xmobI >= 1890)
//                        ) {
//                    xmobF=(float)Math.random()*1890;
//                    xmobI=(int)xmobF;
//
//                }
//                else {
//                    goodSpawn=true;
//                }
//
//            }

            ymap += 100;
            flat += 2;
        }
        for (int i = 0; i != walls.size() / 2 - 1; i++) {
            boolean goodSpawn = false;
            float xmobF = (float) Math.random() * (width-30);
            int xmobI = (int) xmobF;

            while (!goodSpawn) {
                if (xmobI >= walls.get(2 * i).right + 50 && xmobI <= walls.get(2 * i + 1).left - 50) {
                    xmobF = (float) Math.random() * (width-30);
                    xmobI = (int) xmobF;
                } else {
                    goodSpawn = true;
                }
            }

               // mobs.add(i, new Mob(xmobI, walls.get(2 * i).top - 80, 50, 100, 0, context));
                mobs.get(i).pos.x=xmobI;
            mobs.get(i).pos.y=walls.get(2 * i).top-80;


                mobs.get(i).floorNumber = i;

        }


        //(float)mainHero.pic.getHeight()/mainHero.pic.getWidth();
//        Log.d("DanichkruT","Inited");


    }
    int size=50;
    float k ;
    int ymap;
    int flat;
    float hpK=(float)size/100;
    boolean spawn;
    Bitmap nature;
    RectF rectNature= new RectF(0,0,width,height);
    ArrayList<Mob> mobs;

    ArrayList<Wall> walls;
//    Wall[] walls;
    MainHero mainHero;
    public static boolean leftstop;
    public static boolean rightstop;
    public static boolean topstop;
    public static boolean bottomstop;

    public void requestStop() {
        running = false;
    }
    @Override
    public void run() {
//        Log.d("DanichkruT","Runned");
        mobs.get(0).pos.y=4000;
        mobs.get(1).pos.y=4000;
        while (true) {
            Canvas canvas = surfaceHolder.lockCanvas();
            height=canvas.getHeight();
            width=canvas.getWidth();
            if (canvas != null) {
                try {
//                    Log.d("DanichkruT","Tried");

                    Paint p = new Paint();
                    Paint paint = new Paint();
                    p.setColor(Color.WHITE);
                    Paint hpColor = new Paint();
                    hpColor.setColor(Color.RED);
                    canvas.drawBitmap(nature,null,rectNature,paint);
//                    Log.d("DanichkruT","Narisovano");
                    for (int i = 0; i != walls.size(); i++) {
                        walls.get(i).appear(canvas);
                    }
                    leftstop = false;
                    rightstop = false;
                    topstop = false;
                    bottomstop = false;
                    k = (float) 1.4;

                    for (int i = 0; i < walls.size(); i+=1) {

                        if (((walls.get(i).right)/2 == mainHero.pos.x/2 || walls.get(i).left/2 == mainHero.pos.x/2)
                                && (
                                (walls.get(i).top > mainHero.pos.y && walls.get(i).bottom < mainHero.pos.y)
                                        ||
                                        (walls.get(i).top > (mainHero.pos.y + size * k) && walls.get(i).bottom < (mainHero.pos.y + size * k))
                                        ||
                                        (walls.get(i).top > (mainHero.pos.y) && walls.get(i).bottom < (mainHero.pos.y + size * k))
                                        ||
                                        (walls.get(i).bottom > mainHero.pos.y && walls.get(i).top < mainHero.pos.y)
                                        ||
                                        (walls.get(i).bottom > (mainHero.pos.y + size * k) && walls.get(i).top < (mainHero.pos.y + size * k))
                                        ||
                                        (walls.get(i).bottom > (mainHero.pos.y) && walls.get(i).top < (mainHero.pos.y + size * k))
                        )) leftstop = true;
                        if ((walls.get(i).right/2 == (mainHero.pos.x + size)/2 || walls.get(i).left/2 == (mainHero.pos.x + size)/2)
                                && (
                                (walls.get(i).top > mainHero.pos.y && walls.get(i).bottom < mainHero.pos.y)
                                        ||
                                        (walls.get(i).top > (mainHero.pos.y + size * k) && walls.get(i).bottom < (mainHero.pos.y + size * k))
                                        ||
                                        (walls.get(i).top > (mainHero.pos.y) && walls.get(i).bottom < (mainHero.pos.y + size * k))
                                        ||
                                        (walls.get(i).bottom > mainHero.pos.y && walls.get(i).top < mainHero.pos.y)
                                        ||
                                        (walls.get(i).bottom > (mainHero.pos.y + size * k) && walls.get(i).top < (mainHero.pos.y + size * k))
                                        ||
                                        (walls.get(i).bottom > (mainHero.pos.y) && walls.get(i).top < (mainHero.pos.y + size * k))
                        )) rightstop = true;
                        if ((walls.get(i).top/2 == mainHero.pos.y/2 || walls.get(i).bottom/2 == mainHero.pos.y/2) && (
                                (walls.get(i).left < mainHero.pos.x && walls.get(i).right > mainHero.pos.x)
                                        ||
                                        (walls.get(i).left < (mainHero.pos.x + size) && walls.get(i).right > (mainHero.pos.x + size))
                                        ||
                                        (walls.get(i).left > mainHero.pos.x && walls.get(i).right < (mainHero.pos.x + size)) ||
                                        (walls.get(i).right < mainHero.pos.x && walls.get(i).left > mainHero.pos.x)
                                        ||
                                        (walls.get(i).right < (mainHero.pos.x + size) && walls.get(i).left > (mainHero.pos.x + size))
                                        ||
                                        (walls.get(i).right > mainHero.pos.x && walls.get(i).left < (mainHero.pos.x + size))
                        )) topstop = true;
                        if ((walls.get(i).bottom/2 == (mainHero.pos.y + size * k)/2 || walls.get(i).top/2 == (mainHero.pos.y + size * k)/2) && (
                                (walls.get(i).left < mainHero.pos.x && walls.get(i).right > mainHero.pos.x)
                                        ||
                                        (walls.get(i).left < (mainHero.pos.x + size) && walls.get(i).right > (mainHero.pos.x + size))
                                        ||
                                        (walls.get(i).left > mainHero.pos.x && walls.get(i).right < (mainHero.pos.x + size)) ||
                                        (walls.get(i).right < mainHero.pos.x && walls.get(i).left > mainHero.pos.x)
                                        ||
                                        (walls.get(i).right < (mainHero.pos.x + size) && walls.get(i).left > (mainHero.pos.x + size))
                                        ||
                                        (walls.get(i).right > mainHero.pos.x && walls.get(i).left < (mainHero.pos.x + size))
                        )
                                ) {
                            bottomstop = true;
                            //hp-=0.5;
                        }
                    }


                    time++;
                    if(bottomstop && jumppart==2) jumppart=0;
                    if((topstop || mainHero.pos.y==maxy) && jumppart==1) jumppart=2;
                    if(jumppart==0) maxy=mainHero.pos.y-jumpsize;
                    jumppart=mainHero.jump(jumppart);
                    mainHero.move(leftstop, rightstop, topstop, bottomstop);
                    //ИЗМЕНИ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                    if (mainHero.pos.y>=height-200){
                        update();

                    }
                    mainHero.appear(canvas, time);


                    for (int i = 0; i != mobs.size()-1; i+=1) {
                    mobs.get(i).appear(canvas);
                        canvas.drawRect(mobs.get(i).pos.x, mobs.get(i).pos.y-2, mobs.get(i).pos.x + mobs.get(i).hp*hpK,mobs.get(i).pos.y-12 , hpColor);

                    if (mobs.get(i).agr(mainHero, mobs.get(i)) && mobs.get(i).pos.x >=mainHero.pos.x
                            &&
                            mobs.get(i).pos.x != walls.get(2*(mobs.get(i).floorNumber)).left
                                    &&
                                    mobs.get(i).pos.x != walls.get(2*(mobs.get(i).floorNumber)+1).left
                            )
                            {
                        mobs.get(i).mobv=-1;
                        mobs.get(i).pos.x += mobs.get(i).mobv;
                    }
                        else if (mobs.get(i).agr(mainHero, mobs.get(i)) && mobs.get(i).pos.x <=mainHero.pos.x
                            &&
                            mobs.get(i).pos.x + mobs.get(i).size != walls.get(2*(mobs.get(i).floorNumber)).right
                                    &&
                                    mobs.get(i).pos.x + mobs.get(i).size != walls.get(2*(mobs.get(i).floorNumber)+1).right)

                            {
                        mobs.get(i).mobv=1;
                        mobs.get(i).pos.x += mobs.get(i).mobv;
                    }
                    if (mobs.get(i).attack(mainHero, mobs.get(i), 100)) {
                        hp -= 20;
                    }
                        if (mobs.get(i).hp<=0){
                            mobs.get(i).hp=0;
                            mobs.get(i).pos.y=4000;

                        }



//                        if (mobs.get(i).pos.x + mobs.get(i).size == walls.get(2*i).left
//                                ||
//                                mobs.get(i).pos.x + mobs.get(i).size == walls.get(2*i+1).left
//                                )mobs.get(i).stop(false,true);
//
//                        if (mobs.get(i).pos.x == walls.get(2*i).right
//                                ||
//                                mobs.get(i).pos.x == walls.get(2*i+1).right
//                                )mobs.get(i).stop(true,false);
                }


                    if (hp<=0){
                        hp=0;
                    }
                    if (attack){
                        for (int i = 0; i != mobs.size()-1; i+=1) {
                            if ((mobs.get(i).pos.y/7==mainHero.pos.y/7&& ((heroDirection &&  mobs.get(i).pos.x>=mainHero.pos.x && mobs.get(i).pos.x<=mainHero.pos.x+100 )||(!heroDirection &&  mobs.get(i).pos.x<=mainHero.pos.x && mobs.get(i).pos.x>=mainHero.pos.x-100)))){
                                mobs.get(i).hp-=20;
                                attack=false;
                            }
                            if (i== mobs.size()-2)attack=false;
                        }
                    }


                    canvas.drawRect(mainHero.pos.x, mainHero.pos.y-2, mainHero.pos.x + hp*hpK,mainHero.pos.y-12 , hpColor);

                }finally{
                    surfaceHolder.unlockCanvasAndPost(canvas);
//                    Log.d("DanichkruT","Canvasano");
                }

            }
//            Log.d("DanichkruT","Otrisovano");
        }

    }


}