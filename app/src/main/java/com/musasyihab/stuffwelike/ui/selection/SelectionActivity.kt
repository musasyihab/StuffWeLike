package com.musasyihab.stuffwelike.ui.selection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import com.musasyihab.stuffwelike.R
import com.musasyihab.stuffwelike.di.component.DaggerActivityComponent
import com.musasyihab.stuffwelike.di.module.ActivityModule
import com.musasyihab.stuffwelike.model.GetArticleListModel
import com.musasyihab.stuffwelike.ui.view.*
import com.musasyihab.stuffwelike.util.Constants
import com.musasyihab.stuffwelike.util.Helper
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
    private lateinit var selectionStart: SelectionStartPage
    private var currentLikes: Int = 0
    private var currentIndex: Int = 0

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
        selectionStart = findViewById(R.id.selection_start)

        showProgress(false)
        showStartPage(true)
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
                // do something later
            }
        })

        selectionStart.setListener(object: SelectionStartPage.OnStartClick{
            override fun clickStart() {
                presenter.getArticleList()
            }
        })
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }

    private fun nextItem(addLike: Boolean) {
        if(!isLastItem()) {
            currentIndex++
            if (addLike) addLikes()
            flipper.nextArticle()
        } else {
            if (addLike) addLikes()
            selectionDone.setReviewText(currentLikes)
            showSelectionPage(false)
            Helper.slideUp(selectionDone)
        }
    }

    private fun addLikes() {
        currentLikes++
        likeCounter.setLikes(currentLikes)
    }

    private fun isLastItem(): Boolean { return currentIndex == Constants.ARTICLE_SIZE - 1}

    override fun showProgress(show: Boolean) {
        if(show) {
            loading.visibility = View.VISIBLE
            showStartPage(false)
            showSelectionPage(false)
        } else {
            loading.visibility = View.GONE
        }
    }

    override fun showStartPage(show: Boolean) {
        if(show){
            selectionStart.visibility = View.VISIBLE
        } else {
            Helper.fadeOut(selectionStart)
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
        flipper.setArticles(response._embedded.articles)
    }

}

