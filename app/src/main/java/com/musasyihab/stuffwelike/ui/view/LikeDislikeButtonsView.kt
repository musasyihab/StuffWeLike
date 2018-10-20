package com.musasyihab.stuffwelike.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.musasyihab.stuffwelike.R

class LikeDislikeButtonsView(mContext: Context, mAttributes: AttributeSet): LinearLayout(mContext, mAttributes) {

    private var likeBtn: LinearLayout
    private var dislikeBtn: LinearLayout
    private var onButtonClick: OnButtonClick? =null

    init {

        LayoutInflater.from(mContext).inflate(R.layout.like_dislike_buttons_view, this, true)

        likeBtn = findViewById(R.id.btn_like)
        dislikeBtn = findViewById(R.id.btn_dislike)
        likeBtn.setOnClickListener {
            onButtonClick!!.clickLike()
        }

        dislikeBtn.setOnClickListener {
            onButtonClick!!.clickDislike()
        }

    }

    fun setListener(listener: OnButtonClick) {
        onButtonClick = listener
    }

    interface OnButtonClick {
        fun clickLike()
        fun clickDislike()
    }

}