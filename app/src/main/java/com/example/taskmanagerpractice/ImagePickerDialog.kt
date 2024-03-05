package com.example.taskmanagerpractice

import android.app.Dialog
import android.content.Context
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

class ImagePickerDialog(context: Context, private val imageIds: IntArray, title: String) : Dialog(context) {

    private val imagesContainer: LinearLayout

    interface OnImageSelectedListener {
        fun onImageSelected(imagePickerDialog: ImagePickerDialog, imageId: Int)
    }

    private var onImageSelectedListener: OnImageSelectedListener? = null

    fun setOnImageSelectedListener(onImageSelectedListener: OnImageSelectedListener) {
        this.onImageSelectedListener = onImageSelectedListener
    }

    init {
        setContentView(R.layout.image_picker_dialog)
        imagesContainer = findViewById(R.id.imagesContainer)

        for (imageId in imageIds) {
            val img = ImageView(context)
            img.setImageResource(imageId)
//            img.scrollBarStyle
            img.layoutParams = ViewGroup.LayoutParams(300, 300)
            img.setOnClickListener {
                onImageSelectedListener?.onImageSelected(this@ImagePickerDialog, imageId)
            }
            imagesContainer.addView(img)
        }
    }
}