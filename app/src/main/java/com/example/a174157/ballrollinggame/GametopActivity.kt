package com.example.a174157.ballrollinggame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_gametop.*


class GametopActivity : AppCompatActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gametop)


        //井原スタート
        Startbutton.setOnClickListener { onStartbuttonTapped(it) }

        Rankingbutton.setOnClickListener {onRankingbuttonTapped(it)}

        Exitbutton.setOnClickListener {onExitbuttonTapped(it)}
    }
    fun onStartbuttonTapped(view: View?){
        val intent = Intent(this,GameActivity::class.java)
        startActivity(intent)
    }

    fun onRankingbuttonTapped(view: View?){
        val intent = Intent(this,RankingActivity::class.java)
        startActivity(intent)
    }

    fun onExitbuttonTapped(view: View?){
        moveTaskToBack(true)
    }
    //井原終了
}
