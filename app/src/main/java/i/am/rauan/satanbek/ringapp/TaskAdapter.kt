package i.am.rauan.satanbek.ringapp

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.R.attr.start
import android.view.animation.DecelerateInterpolator
import android.animation.ValueAnimator
import android.widget.Toast
import i.am.rauan.satanbek.ringapp.network.NetworkService
import i.am.rauan.satanbek.ringapp.network.Ping
import retrofit2.Call
import retrofit2.Response


class TaskAdapter(val items : ArrayList<TaskModel>, val context: Context) : RecyclerView.Adapter<TaskHolder>() {
    var holders: ArrayList<TaskHolder> = ArrayList()
    val service = NetworkService.Factory.create()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TaskHolder {
        return TaskHolder(LayoutInflater.from(context).inflate(R.layout.all_tasks_list_item, p0, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(p0: TaskHolder, p1: Int) {
        if (p0 in holders) {

        } else {
            holders.add(p0)
        }



        // attach click listener to folding cell
        p0.itemView.setOnClickListener{
            if (!p0.clicked) {
                hideAll()
                p0.clicked = true

                expand(p0.more, 500, 250)
            } else {
                p0.clicked = false
                collapse(p0.more, 500, 0)

                var call = service.ping()

                call.enqueue(object : retrofit2.Callback<Ping> {
                    override fun onResponse(call: Call<Ping>, response: Response<Ping>) {
                        if (response.code() == 200) {
                            var ping = response.body()!!
                            Toast.makeText(context, "Time: ${ping.time}", Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<Ping>, t: Throwable) {
                        Toast.makeText(context, "Подключите интернет", Toast.LENGTH_LONG).show()
                    }
                })
            }
        }

        p0.setData(items[p1])
    }

    private fun hideAll() {
        for( i in holders) {
            if (i.clicked) {
                collapse(i.more, 500, 0)
                i.clicked = false
            }
        }
    }

    fun expand(v: View, duration: Int, targetHeight: Int) {

        val prevHeight = v.height

        v.visibility = View.VISIBLE
        val valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight)
        valueAnimator.addUpdateListener { animation ->
            v.layoutParams.height = animation.animatedValue as Int
            v.requestLayout()
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration.toLong()
        valueAnimator.start()
    }

    fun collapse(v: View, duration: Int, targetHeight: Int) {
        val prevHeight = v.height
        val valueAnimator = ValueAnimator.ofInt(prevHeight, targetHeight)
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.addUpdateListener { animation ->
            v.layoutParams.height = animation.animatedValue as Int
            v.requestLayout()
        }
        valueAnimator.interpolator = DecelerateInterpolator()
        valueAnimator.duration = duration.toLong()
        valueAnimator.start()
    }
}