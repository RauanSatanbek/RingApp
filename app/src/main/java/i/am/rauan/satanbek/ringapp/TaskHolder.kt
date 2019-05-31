package i.am.rauan.satanbek.ringapp

import android.support.v7.widget.RecyclerView
import android.view.View
import kotlinx.android.synthetic.main.all_tasks_list_item.view.*

class TaskHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var imageViewAva = itemView.imageViewAva
    var cancelCall = itemView.cancelCall
    var acceptCall = itemView.acceptCall
    var flag = itemView.flag

    var name = itemView.name
    var title = itemView.title
    var time = itemView.time

    var money = itemView.money
    var operatorName = itemView.operatorName
    var sendDataText = itemView.sendDataText
    var more = itemView.more

    var clicked = false

    fun setData(data: TaskModel) {
        time.text = data.time
        title.text = data.title
        money.text = data.money

        when (data.type) {
            0 -> {
                imageViewAva.visibility = View.INVISIBLE
                cancelCall.visibility = View.VISIBLE
                acceptCall.visibility = View.GONE
                flag.visibility = View.GONE
                sendDataText.visibility = View.GONE
                operatorName.visibility = View.GONE
                name.visibility = View.GONE

            }
            1 -> {
                imageViewAva.visibility = View.INVISIBLE
                cancelCall.visibility = View.GONE
                acceptCall.visibility = View.VISIBLE
                flag.visibility = View.GONE
                sendDataText.visibility = View.GONE
                operatorName.visibility = View.GONE
                name.visibility = View.GONE
            }
            2 -> {
                imageViewAva.setImageResource(R.drawable.ava_1)
                imageViewAva.visibility = View.VISIBLE
                cancelCall.visibility = View.GONE
                acceptCall.visibility = View.GONE

                flag.setImageResource(data.flag!!)
                flag.visibility = View.VISIBLE
                sendDataText.visibility = View.GONE

                operatorName.text = data.operator
                operatorName.visibility = View.VISIBLE

                name.text = data.name
                name.visibility = View.VISIBLE
            }
            3 -> {
                imageViewAva.setImageResource(R.drawable.ava_2)
                imageViewAva.visibility = View.VISIBLE
                cancelCall.visibility = View.GONE
                acceptCall.visibility = View.GONE

                flag.setImageResource(data.flag!!)
                flag.visibility = View.VISIBLE
                sendDataText.visibility = View.GONE

                operatorName.text = data.operator
                operatorName.visibility = View.VISIBLE

                name.text = data.name
                name.visibility = View.VISIBLE
            }
            4 -> {
                imageViewAva.setImageResource(R.drawable.ava_3)
                imageViewAva.visibility = View.VISIBLE
                cancelCall.visibility = View.GONE
                acceptCall.visibility = View.GONE

                flag.visibility = View.GONE
                operatorName.visibility = View.GONE

                sendDataText.text = data.operator
                sendDataText.visibility = View.VISIBLE

                name.text = data.name
                name.visibility = View.VISIBLE
            }
        }
    }

}