package com.musasyihab.stuffwelike.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.musasyihab.stuffwelike.R

class ErrorPageView(private val mContext: Context, mAttributes: AttributeSet): LinearLayout(mContext, mAttributes) {

    private var reloadButton: Button
    private var errorText: TextView
    private var onReloadClick: OnReloadClick? =null

    init {

        LayoutInflater.from(mContext).inflate(R.layout.error_page_view, this, true)

        reloadButton = findViewById(R.id.btn_reload)
        errorText = findViewById(R.id.text_error)

        reloadButton.setOnClickListener {
            onReloadClick!!.clickReload()
        }

    }

    fun setListener(listener: OnReloadClick) {
        onReloadClick = listener
    }

    interface OnReloadClick {
        fun clickReload()
    }

}