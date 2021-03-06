package com.musasyihab.stuffwelike.ui.selection

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.musasyihab.stuffwelike.R
import com.musasyihab.stuffwelike.di.component.DaggerActivityComponent
import com.musasyihab.stuffwelike.di.module.ActivityModule
import com.musasyihab.stuffwelike.model.ArticleModel
import com.musasyihab.stuffwelike.model.GetArticleListModel
import com.musasyihab.stuffwelike.ui.review.ReviewActivity
import com.musasyihab.stuffwelike.ui.view.*
import com.musasyihab.stuffwelike.util.Constants
import com.musasyihab.stuffwelike.util.Helper
import java.util.*
import javax.inject.Inject

class SelectionActivity : AppCompatActivity(), SelectionContract.View {

    @Inject
    lateinit var presenter: SelectionContract.Presenter

    private lateinit var loading: ProgressBar
    private lateinit var errorPage: ErrorPageView
    private lateinit var flipper: ArticleFllipperView
    private lateinit var likeDislikeBtns: LikeDislikeButtonsView
    private lateinit var likeCounter: LikeCounterView
    private lateinit var selectionDone: SelectionDonePage
    private var currentLikes: Int = 0
    private var currentIndex: Int = 0
    private var likedIds: ArrayList<String> = ArrayList(Collections.emptyList())
    private var articles: List<ArticleModel> = Collections.emptyList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selection)
        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        initView()
    }

    private fun initView() {
        loading = findViewById(R.id.selection_loading)
        errorPage = findViewById(R.id.selection_error)
        flipper = findViewById(R.id.article_flipper)
        likeDislikeBtns = findViewById(R.id.like_dislike_buttons)
        likeCounter = findViewById(R.id.like_counter)
        selectionDone = findViewById(R.id.selection_done)

        showProgress(false)
        showSelectionPage(false)
        errorPage.visibility = View.GONE
        selectionDone.visibility = View.INVISIBLE

        errorPage.setListener(object: ErrorPageView.OnReloadClick{
            override fun clickReload() {
                Helper.fadeOut(errorPage)
                presenter.getArticleList()
            }
        })

        likeCounter.setLikes(currentLikes)
        likeDislikeBtns.setListener(object: LikeDislikeButtonsView.OnButtonClick{
            override fun clickLike() {
                nextItem(true)
            }

            override fun clickDislike() {
                nextItem(false)
            }
        })

        selectionDone.setListener(object: SelectionDonePage.OnReviewClick{
            override fun clickReview() {
                val reviewIntent = Intent(this@SelectionActivity, ReviewActivity::class.java)
                reviewIntent.putExtra(ReviewActivity.EXTRA.LIKED_IDS, likedIds)
                startActivity(reviewIntent)
            }
        })

        presenter.getArticleList()

    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }

    private fun nextItem(addLike: Boolean) {
        if(!isLastItem()) {
            if (addLike) addLikes()
            currentIndex++
            flipper.nextArticle()
        } else {
            if (addLike) addLikes()
            selectionDone.setReviewText(currentLikes)
            showSelectionPage(false)
            Helper.slideUp(selectionDone)
        }
    }

    private fun addLikes() {
        val currentItem = articles[currentIndex]
        likedIds.add(currentItem.sku)
        currentLikes++
        likeCounter.setLikes(currentLikes)
    }

    private fun isLastItem(): Boolean { return currentIndex == Constants.ARTICLE_SIZE - 1}

    override fun showProgress(show: Boolean) {
        if(show) {
            loading.visibility = View.VISIBLE
            showSelectionPage(false)
        } else {
            loading.visibility = View.GONE
        }
    }

    override fun showSelectionPage(show: Boolean) {
        flipper.visibility = if (show) View.VISIBLE else View.GONE
        likeCounter.visibility = if(show) View.VISIBLE else View.GONE
        likeDislikeBtns.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun showErrorMessage(error: String) {
        Helper.fadeIn(errorPage)
    }

    override fun loadDataSuccess(response: GetArticleListModel) {
        articles = response._embedded.articles
        flipper.setArticles(response._embedded.articles)
    }

}

