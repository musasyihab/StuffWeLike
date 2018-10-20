package com.musasyihab.stuffwelike.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.animation.AnimationUtils
import android.widget.ViewFlipper
import com.musasyihab.stuffwelike.R
import com.musasyihab.stuffwelike.model.ArticleModel

class ArticleFllipperView(private val mContext: Context, mAttributes: AttributeSet): ViewFlipper(mContext, mAttributes) {

    private var container: ViewFlipper
    private var currentIndex: Int = 0

    init {

        LayoutInflater.from(mContext).inflate(R.layout.article_flipper_view, this, true)

        container = findViewById(R.id.article_flipper_container)
    }

    fun addChild(item: ArticleModel, index: Int) {
        try {
            var view = ArticleItemView(mContext, null)
            view.setTitleText(item.title)
            view.setIdText(item.sku)
            view.setCounterText(index)

            var src = if (item.media.isNotEmpty()) item.media[0].uri else ""
            view.setImagePic(src)

            container.addView(view)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

    fun setArticles(articles: List<ArticleModel>) {
        articles.forEachIndexed({ index, item -> addChild(item, index+1) })
    }

    fun nextArticle() {
        if(container.childCount > currentIndex+1){
            currentIndex++
            container.outAnimation = AnimationUtils.loadAnimation(mContext, R.anim.slide_out_left)
            container.inAnimation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_right)
            container.displayedChild = currentIndex
        }
    }

    fun prevArticle() {
        if(container.childCount>0 && currentIndex>0){
            currentIndex--
            container.outAnimation = AnimationUtils.loadAnimation(mContext, R.anim.slide_out_right)
            container.inAnimation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_left)
            container.displayedChild = currentIndex
        }
    }

}