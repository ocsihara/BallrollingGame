//井原・岩脇スタート
package com.example.a174157.ballrollinggame

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Schedule: RealmObject() {
    @PrimaryKey
    var id: Long = 0
    var date:Date=Date()
    var Id: String = ""
    var Pw: String = ""
}
//井原・岩脇終了