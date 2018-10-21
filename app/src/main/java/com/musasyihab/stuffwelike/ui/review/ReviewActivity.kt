package com.musasyihab.stuffwelike.ui.review

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.musasyihab.stuffwelike.R
import com.musasyihab.stuffwelike.adapter.ReviewItemAdapter
import com.musasyihab.stuffwelike.di.component.DaggerActivityComponent
import com.musasyihab.stuffwelike.di.module.ActivityModule
import com.musasyihab.stuffwelike.model.ArticleSimpleModel
import com.musasyihab.stuffwelike.model.GetArticleListModel
import com.musasyihab.stuffwelike.ui.view.*
import com.musasyihab.stuffwelike.util.Helper
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class ReviewActivity : AppCompatActivity(), ReviewContract.View {

    object EXTRA {
        const val LIKED_IDS: String = "LIKED_IDS"
    }

    @Inject
    lateinit var presenter: ReviewContract.Presenter

    private lateinit var loading: ProgressBar
    private lateinit var errorPage: ErrorPageView
    private lateinit var list: RecyclerView

    private var likedIds: ArrayList<String> = ArrayList(Collections.emptyList())
    private var reviewItems: ArrayList<ArticleSimpleModel> = ArrayList(Collections.emptyList())
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: ReviewItemAdapter
    private var isList: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        if(intent.hasExtra(EXTRA.LIKED_IDS)) {
            likedIds = intent.getStringArrayListExtra(EXTRA.LIKED_IDS)
        }

        initView()
        presenter.getArticleList()
    }

    private fun initView() {
        loading = findViewById(R.id.review_loading)
        errorPage = findViewById(R.id.review_error)
        list = findViewById(R.id.review_list)

        showProgress(false)
        errorPage.visibility = View.GONE
        list.visibility = View.GONE

        gridLayoutManager = GridLayoutManager(this, 2)
        linearLayoutManager = LinearLayoutManager(this)
        if(isList)
            list.layoutManager = linearLayoutManager
        else
            list.layoutManager = gridLayoutManager

        adapter = ReviewItemAdapter(ArrayList(Collections.emptyList()), this, isList)
        list.adapter = adapter

        errorPage.setListener(object: ErrorPageView.OnReloadClick{
            override fun clickReload() {
                Helper.fadeOut(errorPage)
                presenter.getArticleList()
            }
        })

    }

    private fun loadToView() {
        if (!reviewItems.isEmpty()) {
            if(isList)
                list.layoutManager = linearLayoutManager
            else
                list.layoutManager = gridLayoutManager

            errorPage.visibility = View.GONE
            list.visibility = View.VISIBLE
            adapter = ReviewItemAdapter(reviewItems, this, isList)
            list.adapter = adapter
        } else {
            errorPage.visibility = View.VISIBLE
            list.visibility = View.GONE
        }
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }

    override fun showProgress(show: Boolean) {
        if(show) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }

    override fun showErrorMessage() {
        Helper.fadeIn(errorPage)
    }

    override fun loadDataSuccess(response: GetArticleListModel) {
        reviewItems = ArrayList(Collections.emptyList())
        for (item in response._embedded.articles) {
            var src = if (item.media.isNotEmpty()) item.media[0].uri else ""
            var isLiked = likedIds.contains(item.sku)
            var simple = ArticleSimpleModel(item.sku, item.title, src, isLiked)
            reviewItems.add(simple)
        }
        loadToView()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.review_activity_menu, menu)
        val switchMenu = menu!!.getItem(0)
        switchMenuIcon(switchMenu)
        return true
    }

    private fun switchMenuIcon(switchMenu: MenuItem) {
        if (isList) {
            switchMenu.icon = ContextCompat.getDrawable(this, R.drawable.ic_list_white)
        } else {
            switchMenu.icon = ContextCompat.getDrawable(this, R.drawable.ic_grid_white)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.getItemId()

        when (id) {
            R.id.action_review_switch -> {
                isList = !isList
                switchMenuIcon(item)
                loadToView()
            }
        }

        return super.onOptionsItemSelected(item)
    }

}