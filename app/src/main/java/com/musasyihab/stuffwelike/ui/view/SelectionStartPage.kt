package com.musasyihab.stuffwelike.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import com.musasyihab.stuffwelike.R

class SelectionStartPage(mContext: Context, mAttributes: AttributeSet): LinearLayout(mContext, mAttributes) {

    private var startButton: Button
    private var onStartClick: OnStartClick? =null

    init {

        LayoutInflater.from(mContext).inflate(R.layout.selection_start_page, this, true)

        startButton = findViewById(R.id.btn_start)

        startButton.setOnClickListener {
            onStartClick!!.clickStart()
        }

    }

    fun setListener(listener: OnStartClick) {
        onStartClick = listener
    }

    interface OnStartClick {
        fun clickStart()
    }

}