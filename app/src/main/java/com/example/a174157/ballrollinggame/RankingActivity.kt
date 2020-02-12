package com.example.a174157.ballrollinggame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_gametop.*


class RankingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ranking)
        //井原スタート
        Exitbutton.setOnClickListener {onExitbuttonTapped(it)}
        Rankingbutton.setOnClickListener {onRankingbuttonTapped(it)}
    }
    fun onExitbuttonTapped(view: View?){
        moveTaskToBack(true)
    }
    fun onRankingbuttonTapped(view: View?){
        val intent = Intent(this,GametopActivity::class.java)
        startActivity(intent)
    }
    //井原終了
}
