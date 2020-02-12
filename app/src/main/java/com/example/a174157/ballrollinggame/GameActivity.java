//中井スタート
package com.example.a174157.ballrollinggame;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

import static com.example.a174157.ballrollinggame.GametopActivity.*;

public class GameActivity extends AppCompatActivity {
    //View
    private LabyrinthView mLabyrinthView;
    //センサー
    private SensorManager mSensorManager;
//    private GametopActivity mGametopActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        mLabyrinthView = new LabyrinthView(this);
        setContentView(mLabyrinthView);
        //センサーの初期化
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

    }
    //画面が表示された時の処理
    @Override
    protected void onResume(){
        super.onResume();
        //センサーリスナーの登録
        final Sensor accele = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorManager.registerListener(mLabyrinthView, accele, SensorManager.SENSOR_DELAY_GAME);
        //ゲームレベルをセット
        mLabyrinthView.setLabyrinthData(LoadLabyrinth(1));
    }

    //画面が非表示になった時の処理
    @Override
    protected void onPause() {
        super.onPause();
        //センサーリスナーの解除
        mSensorManager.unregisterListener(mLabyrinthView);
        //ゲームを初期化
        mLabyrinthView.initGame();
    }
    //画面がタップされた時の処理
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            switch(mLabyrinthView.getGameState()){
                case LabyrinthView.GAME_INIT:
                    mLabyrinthView.startGame();
                    break;
                case LabyrinthView.GAME_OVER:
                    mLabyrinthView.startGame();
                    break;
                case LabyrinthView.GAME_COMPLETE:
                    Intent intent = new Intent(getApplication(), GametopActivity.class);
                    startActivity(intent);
                    break;
            }
        }
        return true;
    }
    //CSV形式の迷路データを読み込む
    private int[][] LoadLabyrinth(int level){
        final String fileName = "stage" + level + ".txt";
        final int MAZE_ROWS = Labyrinth.MAZE_ROWS;
        final int MAZE_COLS = Labyrinth.MAZE_COLS;

        int[][] data = new int[MAZE_ROWS][MAZE_COLS];
        InputStream is = null;
        try {
            is = getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null && i < MAZE_ROWS) {
                StringTokenizer st = new StringTokenizer(line, ",");
                int j = 0;
                while (st.hasMoreTokens() && j < MAZE_COLS) {
                    //空白があれば除去
                    String s = st.nextToken();
                    data[i][j] = Integer.parseInt(s.trim());
                    j++;
                }
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                }
            }
        }
        return data;
    }
}
//中井終わり

