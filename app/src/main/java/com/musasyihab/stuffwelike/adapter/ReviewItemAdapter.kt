package com.musasyihab.stuffwelike.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.musasyihab.stuffwelike.R
import com.musasyihab.stuffwelike.model.ArticleSimpleModel

class ReviewItemAdapter(private val mList: ArrayList<ArticleSimpleModel>,
                        context: Context, private val isList: Boolean) : RecyclerView.Adapter<ReviewItemAdapter.ItemHolder>() {

    private val mInflater: LayoutInflater

    init {
        mInflater = LayoutInflater.from(context)
    }

    override fun getItemCount() = mList.size

    override fun onBindViewHolder(holder: ReviewItemAdapter.ItemHolder, position: Int) {
        val item = mList[position]
        holder.bindItem(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewItemAdapter.ItemHolder {
        val gridInflated = mInflater.inflate(R.layout.review_item_grid, parent,false)
        val listInflated = mInflater.inflate(R.layout.review_item_list, parent,false)
        val inflatedView = if(isList) listInflated else gridInflated
        return ItemHolder(inflatedView, isList)
    }

    class ItemHolder(v: View, isList: Boolean) : RecyclerView.ViewHolder(v) {
        private var view: View = v
        private var item: ArticleSimpleModel? = null
        private var isList: Boolean = isList

        var image: ImageView
        var liked: ImageView
        var title: TextView? = null
        var id: TextView
        var glideContext: RequestManager? = null

        init {
            if(this.isList) {
                image = view.findViewById(R.id.review_item_list_image) as ImageView
                liked = view.findViewById(R.id.review_item_list_liked) as ImageView
                id = view.findViewById(R.id.review_item_list_id) as TextView
                title = view.findViewById(R.id.review_item_list_title) as TextView
            } else {
                image = view.findViewById(R.id.review_item_grid_image) as ImageView
                liked = view.findViewById(R.id.review_item_grid_liked) as ImageView
                id = view.findViewById(R.id.review_item_grid_id) as TextView
            }

            glideContext = Glide.with(view.context)
        }

        fun bindItem(item: ArticleSimpleModel) {
            this.item = item
            id.text = item.id
            if(isList) title!!.text = item.title

            glideContext!!.load(item.imageUrl)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .error(R.drawable.ic_image)
                    .into(image)
            liked.visibility = if(item.isLiked) View.VISIBLE else View.GONE
        }

    }

}