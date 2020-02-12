//井原・岩脇スタート
package com.example.a174157.ballrollinggame

import android.app.Application
import io.realm.Realm

/**
 * Created by user on 2018/03/19.
 */
class MySchedulerApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
    }
}
//井原・岩脇終了