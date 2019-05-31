package i.am.rauan.satanbek.ringapp

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.internal.BottomNavigationItemView
import android.support.design.internal.BottomNavigationMenuView
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_blocker -> {
                textMessage.setText(R.string.title_blocker)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_tasks -> {
                textMessage.setText(R.string.title_tasks)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    var tasks: ArrayList<TaskModel> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(mainToolbar)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        textMessage = findViewById(R.id.message)
        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        navView.selectedItemId = R.id.navigation_tasks // Auto select for tasks

        var bottomNavigationMenuView : BottomNavigationMenuView = navView.getChildAt(0) as BottomNavigationMenuView
        var bottomNavigationItemView : BottomNavigationItemView = bottomNavigationMenuView.getChildAt(1) as BottomNavigationItemView
        var badge : View = LayoutInflater.from(this).inflate(R.layout.notification_badge, bottomNavigationItemView, true)

        loadTasks()

        allTasksRecyclerView.layoutManager = LinearLayoutManager(this)
        allTasksRecyclerView.adapter = TaskAdapter(tasks, this)

        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.INTERNET)

        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    fun loadTasks() {
        tasks.add(TaskModel(0, "", "Сбросить звонок", "5 min", "0.33 $", 0, ""))
        tasks.add(TaskModel(1, "", "Ответить на звонок", "5 min", "0.33 $", 0, ""))
        tasks.add(TaskModel(2, "Павел", "Позвонить с ответом", "12 min", "0.33 $", R.drawable.russia_flag, "Russia  TELE2"))
        tasks.add(TaskModel(3, "Елена", "Позвонить без ответа", "20 sec", "0.33 $", R.drawable.kazakh_flag, "Kazakhstan  TELE2"))
        tasks.add(TaskModel(4, "Юра", "Отправить данные", "", "0.33 $", 0, "по звонку на номер +..."))
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(this,
            arrayOf(Manifest.permission.INTERNET), 1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }
}
