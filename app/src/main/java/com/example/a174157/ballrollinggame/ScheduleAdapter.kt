//井原・岩脇スタート
package com.example.a174157.ballrollinggame

import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.realm.OrderedRealmCollection
import io.realm.RealmBaseAdapter

/**
 * Created by user on 2018/03/19.
 */
class ScheduleAdapter(data: OrderedRealmCollection<Schedule>?) : RealmBaseAdapter<Schedule>(data) {

    inner class ViewHolder(cell: View) {
        val date = cell.findViewById<TextView>(android.R.id.text1)
        val title= cell.findViewById<TextView>(android.R.id.text2)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val viewHolder: ViewHolder

        when (convertView) {
            null -> {
            val inﬂater = LayoutInflater.from(parent?.context)
            view = inﬂater.inflate(android.R.layout.simple_list_item_2,
                    parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }
            else -> {
                view = convertView
                viewHolder = view.tag as ViewHolder
            }
        }

        /*adapterData?.run {
            val schedule = get(position)
            viewHolder.date.text =
                    DateFormat.format("yyyy/MM/dd", schedule.date)
            viewHolder.title.text = schedule.title
        }*/
        return view
    }
}
//井原・岩脇終了