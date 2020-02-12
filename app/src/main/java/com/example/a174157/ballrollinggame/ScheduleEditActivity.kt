//井原・岩脇スタート
package com.example.a174157.ballrollinggame

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import io.realm.Realm
import io.realm.kotlin.createObject
import io.realm.kotlin.where
import kotlinx.android.synthetic.main.activity_makeaccount.*

class ScheduleEditActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makeaccount)
        kakutei.setOnClickListener { onbuttonTapped(it) }
    }
    fun onbuttonTapped(view: View?) {
        //    val  ID = (findViewById(R.id.ID) as IDtext).text.toString()
        val ID = C_ID.text.toString()
        val PW = C_PW.text.toString()
        val PW2 = C_PW2.text.toString()

      //  if (PW == PW2 && C_PW.getText().toString().equals("") == false && C_PW2.getText().toString().equals("") == false) {
            val intent = Intent(this, MainActivity::class.java) //ログイン完了後遷移先
            startActivity(intent)
        //}
    }
}

//メモ帳コピー以前
/*class ScheduleEditActivity : AppCompatActivity() {
    private lateinit var realm: Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_makeaccount)

        kakutei.setOnClickListener { onkakuteiButtonTapped (it)}

       // realm = Realm.getDefaultInstance()
//確定をおしたときの処理


       

    }

    fun onkakuteiButtonTapped(view: View?){
       /* realm.executeTransaction {
            val maxId = realm.where<Schedule>().max("id")
            val nextId = (maxId?.toLong() ?: 0L) + 1
            val schedule = realm.createObject<Schedule>()

            schedule.Id = C_ID.text.toString()
            schedule.Pw = C_PW.text.toString()
        }*/

        val intent = Intent(this,GametopActivity::class.java)
        startActivity(intent)
    }



}
//井原・岩脇終了
*/
