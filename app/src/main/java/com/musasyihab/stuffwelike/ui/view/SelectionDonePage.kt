package com.musasyihab.stuffwelike.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.musasyihab.stuffwelike.R
import com.musasyihab.stuffwelike.util.Constants

class SelectionDonePage(private val mContext: Context, mAttributes: AttributeSet): LinearLayout(mContext, mAttributes) {

    private var reviewButton: Button
    private var text: TextView
    private var onReviewClick: OnReviewClick? =null

    init {

        LayoutInflater.from(mContext).inflate(R.layout.selection_done_page, this, true)

        reviewButton = findViewById(R.id.btn_review)
        text = findViewById(R.id.text_review)

        val attributes = mContext.obtainStyledAttributes(mAttributes,
                R.styleable.SelectionDonePage)
        val likes = attributes.getInt(R.styleable.SelectionDonePage_likes, -1)
        setReviewText(likes)

        reviewButton.setOnClickListener {
            onReviewClick!!.clickReview()
        }

    }

    fun setReviewText(likes: Int) {
        this.text.text = if(likes >= 0) "Great! You have liked $likes/${Constants.ARTICLE_SIZE} items" else ""
    }

    fun setListener(listener: OnReviewClick) {
        onReviewClick = listener
    }

    interface OnReviewClick {
        fun clickReview()
    }

}