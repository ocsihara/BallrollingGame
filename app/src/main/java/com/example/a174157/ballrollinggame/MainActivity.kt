package com.example.a174157.ballrollinggame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //井原スタート
        Guestbutton.setOnClickListener { onguestButtonTapped(it) }


        NewaccountButton.setOnClickListener { onmakeaccountButtonTapped(it) }
        //井原終了
        //鏡スタート
      //  Loginbutton.setOnClickListener { onLoginButtonTapped(it) }//(鏡作業）
        //鏡終了
        //realm = Realm.getDefaultInstance()



    }
    //井原スタート
    fun onguestButtonTapped(view: View?){
        val intent = Intent(this,GametopActivity::class.java)
        startActivity(intent)
    }
    fun onmakeaccountButtonTapped(view: View?){
        val intent = Intent(this,MakeaccountActivity::class.java)
        startActivity(intent)
    }
    //井原終了






//鏡スタート
//(鏡作業：ログイン動作確認DBなし）
    /*fun onLoginButtonTapped(view: View?) {
        //    val  ID = (findViewById(R.id.ID) as IDtext).text.toString()
       val ID = Idtext.text.toString()
        val RID =realm.where<Schedule>().equalTo("Id",ID).toString() //IDtext.getText().toInt()
        if (ID == RID) {
            val PW = Pwtext.text.toString()
            val APW = "12"
          //  if (PW == APW) {
                val intent = Intent(this, GametopActivity::class.java) //ログイン完了後遷移先
                startActivity(intent)
            //}
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }*/
    //鏡終了
}
