package com.example.a174157.ballrollinggame

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_makeaccount.*

class MakeaccountActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_makeaccount)
        realm = Realm.getDefaultInstance()
        kakutei.setOnClickListener { onbuttonTapped(it) }
    }
    fun onbuttonTapped(view: View?) {
        //    val  ID = (findViewById(R.id.ID) as IDtext).text.toString()


       realm.executeTransaction {
            val maxId = realm.where<Schedule>().max("id")
            val nextId = (maxId?.toLong() ?: 0L) + 1
            val schedule = realm.createObject<Schedule>(nextId)

            schedule.Id = C_ID.text.toString()
            schedule.Pw = C_PW.text.toString()

            //realm.commitTransaction()
        }

       //if (PW == PW2 && C_PW.getText().toString().equals("") == false && C_PW2.getText().toString().equals("") == false) {
        val intent = Intent(this, MainActivity::class.java) //ログイン完了後遷移先
        startActivity(intent)
        //}
    }
    override fun onDestroy() {
        super.onDestroy()
        realm.close()
    }
}
        //井原スタート
   //     kakutei.setOnClickListener { onkakuteiButtonTapped(it) }
    //}
    /*fun onkakuteiButtonTapped(view: View?){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }*/
        //井原終了


