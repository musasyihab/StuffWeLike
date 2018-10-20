package com.musasyihab.stuffwelike.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.musasyihab.stuffwelike.R
import com.musasyihab.stuffwelike.util.Constants

class LikeCounterView(mContext: Context, mAttributes: AttributeSet?): LinearLayout(mContext, mAttributes) {

    private var likes: TextView
    private var total: TextView

    init {

        LayoutInflater.from(mContext).inflate(R.layout.like_counter_view, this, true)

        likes = findViewById(R.id.like_count)
        total = findViewById(R.id.total_count)

        likes.text = "0"
        total.text = Constants.ARTICLE_SIZE.toString()

    }

    fun setLikes(counterNumber: Int) {
        this.likes.text = counterNumber.toString()
    }



}