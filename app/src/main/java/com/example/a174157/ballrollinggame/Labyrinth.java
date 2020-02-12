//中井スタート
package com.example.a174157.ballrollinggame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Labyrinth {
    public final static int MAZE_ROWS = 32;
    public final static int MAZE_COLS = 20;

    public final static int PATH_TILE = 0;
    public final static int WALL_TILE = 1;
    public final static int EXIT_TILE = 2;
    public final static int VOID_TILE = 3;

    private int[][] mData;

    private int mTileWidth;
    private int mTileHeight;

    private Paint mPathPaint;
    private Paint mWallPaint;
    private Paint mExitPaint;
    private Paint mVoidPaint;

    public Labyrinth(){
        mData = new int[MAZE_ROWS][MAZE_COLS];
        mPathPaint = new Paint();
        mPathPaint.setColor(Color.rgb(255, 136, 112));
        mWallPaint = new Paint();
        mWallPaint.setColor(Color.rgb(102, 54, 44));
        mExitPaint = new Paint();
        mExitPaint.setColor(Color.RED);
        mVoidPaint = new Paint();
        mVoidPaint.setColor(Color.BLACK);
    }
    //データをセットする
    public void setData(int[][] data){
        mData = data;
    }

    //サイズを決める
    public void setSize(int w, int h){
        mTileWidth = w / MAZE_COLS;
        mTileHeight = h / MAZE_ROWS;
    }

    //描画処理
    public void draw(Canvas canvas){
        for(int i=0; i < MAZE_ROWS; i++){
            for(int j=0; j < MAZE_COLS; j++){
                switch(mData[i][j]){
                    case PATH_TILE:
                        canvas.drawRect(j*mTileWidth, i*mTileHeight,
                                (j+1)*mTileWidth, (i+1)*mTileHeight, mPathPaint);
                        break;
                    case WALL_TILE:
                        canvas.drawRect(j*mTileWidth, i*mTileHeight,
                                (j+1)*mTileWidth, (i+1)*mTileHeight, mWallPaint);
                        break;
                    case EXIT_TILE:
                        canvas.drawRect(j*mTileWidth, i*mTileHeight,
                                (j+1)*mTileWidth, (i+1)*mTileHeight, mExitPaint);
                        break;
                    case VOID_TILE:
                        canvas.drawRect(j*mTileWidth, i*mTileHeight,
                                (j+1)*mTileWidth, (i+1)*mTileHeight, mVoidPaint);
                        break;
                }
            }
        }
    }

    //セルのタイプを取得
    public int getCellType(int x, int y){
        int j = x / mTileWidth;
        int i = y / mTileHeight;
        if(i < MAZE_ROWS && j < MAZE_COLS) return mData[i][j];
        return PATH_TILE;
    }
}
//中井終わり
