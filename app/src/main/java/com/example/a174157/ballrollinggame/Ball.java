//中井スタート
        package com.example.a174157.ballrollinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Ball {
    private int mX = 0;
    private int mY = 0;
    private Paint mBallPaint;
    private int mRadius = 0;
    public Ball(){
        mBallPaint = new Paint();
        mBallPaint.setColor(Color.rgb(125, 125, 125));
        mBallPaint.setAntiAlias(true);
    }
    public void setPosition(int x, int y){
        mX = x;
        mY = y;
    }
    public int getX(){
        return mX;
    }
    public int getY(){
        return mY;
    }
    public void setRadus(int r){
        mRadius = r;
    }
    public int getRadus(){
        return mRadius;
    }
    //描画
    public void draw(Canvas canvas){
        canvas.drawCircle(mX, mY, mRadius, mBallPaint);
    }
    //移動
    public void move(int dx, int dy){
        mX += dx;
        mY += dy;
    }
}
//中井終わり
