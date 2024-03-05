package com.example.taskmanagerpractice

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val TaskList = ArrayList<Tasks>()
    private lateinit var TaskAdapter: Tasks_Adapter
    private lateinit var btnAdd: Button
    val REQUEST_CODE = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initAdapter()

    }

    private fun initAdapter() {
        TaskAdapter = Tasks_Adapter(TaskList)
        recyclerView.adapter = TaskAdapter
    }

    private fun initViews() {
        btnAdd = findViewById(R.id.btnAdd)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.VERTICAL,
            false
        )

        btnAdd.setOnClickListener {
            val intent = Intent(this, TaskManager_Activity::class.java)
            startActivityForResult(intent, REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {

            val imageId = data?.getIntExtra("selectedImageIds", 0) ?: 0
            val title = data?.getStringExtra("title") ?: ""
            val price = data?.getStringExtra("price") ?: ""
            val date = data?.getStringExtra("Date") ?: ""

            val newTask = Tasks(imageId, title, price, date)

            TaskList.add(0, newTask)
            TaskAdapter.notifyItemInserted(0)
            recyclerView.scrollToPosition(0)
        }
    }
}