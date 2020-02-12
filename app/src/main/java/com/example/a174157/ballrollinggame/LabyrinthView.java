//中井スタート
package com.example.a174157.ballrollinggame;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Handler;
import android.view.View;

public class LabyrinthView extends View implements SensorEventListener {
    public final static int NULL_STATE = -1;
    public final static int GAME_INIT = 0;
    public final static int GAME_RUNNING = 1;
    public final static int GAME_OVER = 2;
    public final static int GAME_COMPLETE = 3;

    private int mGameState = NULL_STATE;
    //表示用テキスト
    private final String TXT_GAME_INIT;
    private final String TXT_GAME_OVER;
    private final String TXT_GAME_COMPLETE;
    private final String TXT_TOTAL_TIME;
    private final String TXT_RESTART;
    //テキスト用Paint
    private Paint mBgPaint;
    private Paint mTxtPaintOverlay;
    private Paint mTxtPaintResult;
    //時間
    private long mStartTime = 0;
    private long mTotalTime = 0;
    //壁
    private static final int WALL = Labyrinth.WALL_TILE;
    //反発係数
    private static final float REBOUND = 0.0f;
    //ローパスフィルタ係数
    private static final float FILTER_FACTOR = 0.2f;

    //端末の加速度
    private float mAccelX = 0.0f;
    private float mAccelY = 0.0f;
    //ボールの加速度
    private float mVectorX = 0.0f;
    private float mVectorY = 0.0f;

    //迷路とボール
    private Labyrinth mLabyrinth;
    private Ball mBall;
    //画面サイズ
    private int mWidth;
    //ゲームループ用
    private Handler mHandler;
    private Runnable mDrawThread = new Runnable(){
        @Override
        public void run() {
            gameLoop();
        }
    };
    private int mHeight;

    public LabyrinthView(Context context) {
        super(context);
        mLabyrinth = new Labyrinth();
        mBall = new Ball();
        mHandler = new Handler();
        mBgPaint = new Paint();
        mBgPaint.setColor(Color.WHITE);
        mTxtPaintOverlay = new Paint();
        mTxtPaintOverlay.setAntiAlias(true);
        mTxtPaintOverlay.setColor(Color.WHITE);
        mTxtPaintOverlay.setTextAlign(Paint.Align.CENTER);
        mTxtPaintOverlay.setTextSize(40);
        mTxtPaintResult = new Paint();
        mTxtPaintResult.setAntiAlias(true);
        mTxtPaintResult.setColor(Color.BLACK);
        mTxtPaintResult.setTextAlign(Paint.Align.CENTER);
        mTxtPaintResult.setTextSize(20);
        Resources r = context.getResources();
        String[] array = r.getStringArray(R.array.game_strings);
        TXT_GAME_INIT = array[0];
        TXT_GAME_OVER = array[1];
        TXT_GAME_COMPLETE = array[2];
        TXT_TOTAL_TIME = array[3];
        TXT_RESTART = array[4];
    }

    //ゲーム実行中のループ
    private void gameLoop(){
        mVectorX = mVectorX - mAccelX;
        mVectorY = mVectorY + mAccelY;

        //ボールの位置判定
        int nextX = mBall.getX() + (int)mVectorX;
        int nextY = mBall.getY() + (int)mVectorY;
        int radius = mBall.getRadus();
        if( (nextX - radius) < 0) mVectorX *= -REBOUND;
        if( (nextX + radius) > mWidth) mVectorX *= -REBOUND;
        if( (nextY - radius) < 0) mVectorY *= -REBOUND;
        if( (nextY + radius) > mHeight) mVectorY *= -REBOUND;
        if( (nextY + radius) > mHeight) mVectorY *= -REBOUND;
        if(radius < nextX && nextX < mWidth-radius && radius < nextY && nextY < mHeight - radius){
            //壁の当たり判定
            float ul = mLabyrinth.getCellType(nextX-radius, nextY-radius);
            float ur = mLabyrinth.getCellType(nextX+radius, nextY-radius);
            float dl = mLabyrinth.getCellType(nextX-radius, nextY+radius);
            float dr = mLabyrinth.getCellType(nextX+radius, nextY+radius);//-0.0000001f;

            if( ul != WALL && ur != WALL && dl != WALL && dr != WALL){
            }else if( ul != WALL && ur == WALL && dl != WALL && dr == WALL){
                mVectorX *= -REBOUND;
            }else if( ul == WALL && ur != WALL && dl == WALL && dr != WALL){
                mVectorX *= -REBOUND;
            }else if( ul == WALL && ur == WALL && dl != WALL && dr != WALL){
                mVectorY *= -REBOUND;
            }else if( ul != WALL && ur != WALL && dl == WALL && dr == WALL){
                mVectorY *= -REBOUND;
            }else if( ul == WALL && ur != WALL && dl != WALL && dr != WALL){
                if(mVectorX < 0.0f && mVectorY > 0.0f){
                    mVectorX *= -REBOUND;
                }else if(mVectorX > 0.0f && mVectorY < 0.0f){
                    mVectorY *= -REBOUND;
                }else {
                    mVectorX *= -REBOUND;mVectorY *= -REBOUND;
                }
            }else if( ul != WALL && ur == WALL && dl != WALL && dr != WALL){
                if(mVectorX > 0.0f && mVectorY > 0.0f){
                    mVectorX *= -REBOUND;
                } else if(mVectorX < 0.0f && mVectorY < 0.0f){
                    mVectorY *= -REBOUND;
                } else {
                    mVectorX *= -REBOUND;
                    mVectorY *= -REBOUND;
                }
            }else if( ul != WALL && ur != WALL && dl == WALL && dr != WALL){
                if(mVectorX > 0.0f && mVectorY > 0.0f){
                    mVectorY *= -REBOUND;
                } else if(mVectorX < 0.0f && mVectorY < 0.0f){
                    mVectorX *= -REBOUND;
                } else {
                    mVectorX *= -REBOUND;
                    mVectorY *= -REBOUND;
                }
            }else if( ul != WALL && ur != WALL && dl != WALL && dr == WALL){
                if(mVectorX < 0.0f && mVectorY > 0.0f) {
                    mVectorY *= -REBOUND;
                }else if(mVectorX > 0.0f && mVectorY < 0.0f){
                    mVectorX *= -REBOUND;
                }else {
                    mVectorX *= -REBOUND;
                    mVectorY *= -REBOUND;
                }
            }else{
                mVectorX *= -REBOUND;
                mVectorY *= -REBOUND;
            }
            //穴の判定
            if(mLabyrinth.getCellType(nextX, nextY) == Labyrinth.VOID_TILE){
                stopGame();
            }
            //ゴール判定
            if(mLabyrinth.getCellType(nextX, nextY) == Labyrinth.EXIT_TILE){
                completeGame();
            }
        }
        //ボールを移動
        mBall.move((int)mVectorX, (int)mVectorY);

        //再描画
        invalidate();
        if(mGameState == GAME_RUNNING) {
            mHandler.removeCallbacks(mDrawThread);
            mHandler.postDelayed(mDrawThread, 30);
        }
    }
    //迷路データのセット
    public void setLabyrinthData(int[][] data){mLabyrinth.setData(data);}
    //ゲームの状態を取得
    public int getGameState(){
        return mGameState;
    }
    //タイムを取得
    public long getScore(){
        return mTotalTime;
    }
    //ゲームの初期化
    public void initGame(){
        mGameState = GAME_INIT;
        mHandler.removeCallbacks(mDrawThread);
        mTotalTime = 0;
        mBall.setPosition(mBall.getRadus() *4, mBall.getRadus() * 4);
        invalidate();

    }
    //ゲームを開始
    public void startGame(){
        mGameState = GAME_RUNNING;
        mHandler.post(mDrawThread);
        mBall.setPosition(mBall.getRadus() *4, mBall.getRadus() * 4);
        mStartTime = System.currentTimeMillis();
    }
    //ゲームの中断
    public void stopGame(){
        mGameState = GAME_OVER;
        mHandler.removeCallbacks(mDrawThread);
        mTotalTime += System.currentTimeMillis() - mStartTime;
    }
    //ゲームの完遂
    public void completeGame(){
        mGameState = GAME_COMPLETE;
        mHandler.removeCallbacks(mDrawThread);
        mTotalTime += System.currentTimeMillis() - mStartTime;
    }





    //ゲーム実行中のループ

    //画面のサイズが変化した時(初回表示時)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h , oldw, oldh);
        mWidth = w;
        mHeight = h;
        mLabyrinth.setSize(w, h);
        mBall.setRadus( w / (2 * Labyrinth.MAZE_COLS));
        initGame();
    }

    @Override
    public void onDraw(Canvas canvas) {
        //ゲーム中
        if(mGameState == GAME_RUNNING) {
            mLabyrinth.draw(canvas);
            mBall.draw(canvas);
            return;
        }
        //ゲーム中以外の時
        switch(mGameState){
            case GAME_INIT:
                mLabyrinth.draw(canvas);
                mBall.draw(canvas);
                canvas.drawText(TXT_GAME_INIT, mWidth/2, mHeight/2, mTxtPaintOverlay);
                break;
            case GAME_OVER:
                mLabyrinth.draw(canvas);
                canvas.drawText(TXT_GAME_OVER, mWidth/2, mHeight/2, mTxtPaintOverlay);
                break;
            case GAME_COMPLETE:
                canvas.drawRect(0, 0, mWidth, mHeight, mBgPaint);
                canvas.drawText(TXT_GAME_COMPLETE, mWidth/2, mHeight/2, mTxtPaintResult);
                canvas.drawText(TXT_TOTAL_TIME+": "+getScore()+" ms",mWidth/2, mHeight/2 + mTxtPaintResult.getFontSpacing(), mTxtPaintResult);
                canvas.drawText(TXT_RESTART, mWidth/2, mHeight - (mTxtPaintResult.getFontSpacing() * 3), mTxtPaintResult);
                break;
        }
    }

    //センサーの精度が変化した時
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        //何もしない
    }
    //センサーの値が変化した時
    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized(this){
            float[] values = event.values.clone();
            //ローパスフィルタ
            mAccelX = (mAccelX * FILTER_FACTOR) + (values[0] * (1.0f - FILTER_FACTOR));
            mAccelY = (mAccelY * FILTER_FACTOR) + (values[1] * (1.0f - FILTER_FACTOR));
        }
    }
    //センサーの値が変化した時
}
//中井終わり

