package com.example.taskmanagerpractice

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.Array

class TaskManager_Activity : AppCompatActivity() {

    private lateinit var edtTitle : EditText
    private lateinit var edtPrice : EditText
    private lateinit var edtDate : EditText
    private lateinit var btnDatePicker : ImageButton
    private lateinit var btnSave : Button
    private lateinit var imgview : ImageView
//    private val selectedimageIds = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.taskmanager_activity)

        initViews()
        DatePickerDialog()

    }

    private  fun  DatePickerDialog(){
        btnDatePicker.setOnClickListener {
            val datePickerDialog = DatePickerDialog(this@TaskManager_Activity,MyonDateClick(),2024,10,22)
            datePickerDialog.show()
        }
    }

    private  inner class MyonDateClick : DatePickerDialog.OnDateSetListener{
        override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
            edtDate.setText("$p1/${p2 + 1}/$p3")
        }
    }

    private fun initViews(){
        edtTitle = findViewById(R.id.edtTitle)
        edtPrice = findViewById(R.id.edtPrice)
        edtDate = findViewById(R.id.edtDate)
        btnSave = findViewById(R.id.btnSave)
        btnDatePicker = findViewById(R.id.btnDatePicker)
        imgview = findViewById(R.id.imgview)

        btnSave.setOnClickListener{
            val title = findViewById<EditText>(R.id.edtTitle).text.toString()
            val price = findViewById<EditText>(R.id.edtPrice).text.toString()
            val date = findViewById<EditText>(R.id.edtDate).text.toString()
            val imageId = imgview.tag as? Int ?: 0

            val intent = Intent()
            intent.putExtra("selectedImageIds", imageId)
            intent.putExtra("title", title)
            intent.putExtra("price", price)
            intent.putExtra("Date", date)
            setResult(RESULT_OK, intent)
            finish()
        }

        imgview.setOnClickListener {
            val imgIds = intArrayOf(R.drawable.laptops, R.drawable.car, R.drawable.stock)

            val imagePickerDialog = ImagePickerDialog(this, imgIds, "Select Image")
            imagePickerDialog.setOnImageSelectedListener(object : ImagePickerDialog.OnImageSelectedListener {
                override fun onImageSelected(imagePickerDialog: ImagePickerDialog, imageId: Int) {

                    imgview.setImageResource(imageId)
                    imgview.tag = imageId
                    imagePickerDialog.dismiss()
                }
            }
            )
            imagePickerDialog.show()
        }
    }
}