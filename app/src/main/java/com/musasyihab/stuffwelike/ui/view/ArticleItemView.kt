package com.musasyihab.stuffwelike.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.musasyihab.stuffwelike.R
import com.musasyihab.stuffwelike.util.Constants

class ArticleItemView(mContext: Context, mAttributes: AttributeSet?): LinearLayout(mContext, mAttributes) {

    private var image: ImageView
    private var title: TextView
    private var id: TextView
    private var counter: TextView
    private var glideContext: RequestManager? = null

    init {

        LayoutInflater.from(mContext).inflate(R.layout.article_item_view, this, true)

        image = findViewById(R.id.article_item_image)
        title = findViewById(R.id.article_item_title)
        id = findViewById(R.id.article_item_id)
        counter = findViewById(R.id.article_item_counter)

        glideContext = Glide.with(mContext)

        val attributes = mContext.obtainStyledAttributes(mAttributes,
                R.styleable.ArticleItemView)
        val imageSrc = attributes.getString(R.styleable.ArticleItemView_imageSrc)
        setImagePic(imageSrc)
        val titleText = attributes.getString(R.styleable.ArticleItemView_title)
        setTitleText(titleText)
        val idText = attributes.getString(R.styleable.ArticleItemView_itemId)
        setIdText(idText)
        val counterNumber = attributes.getInt(R.styleable.ArticleItemView_counter, -1)
        setCounterText(counterNumber)

    }

    fun setCounterText(counterNumber: Int) {
        this.counter.text = if(counterNumber >= 0) "Item $counterNumber/${Constants.ARTICLE_SIZE}" else ""
    }

    fun setTitleText(titleText: String?) {
        this.title.text = if(titleText?.isNotEmpty() == true) titleText else ""
    }

    fun setIdText(idText: String?) {
        this.id.text = if(idText?.isNotEmpty() == true) idText else ""
    }

    fun setImagePic(imageSrc: String?) {
        glideContext!!.load(imageSrc)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .error(R.drawable.ic_image)
                .into(image);
    }


}