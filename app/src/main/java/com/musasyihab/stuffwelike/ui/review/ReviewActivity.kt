package com.musasyihab.stuffwelike.ui.review

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ProgressBar
import com.musasyihab.stuffwelike.R
import com.musasyihab.stuffwelike.di.component.DaggerActivityComponent
import com.musasyihab.stuffwelike.di.module.ActivityModule
import com.musasyihab.stuffwelike.model.GetArticleListModel
import com.musasyihab.stuffwelike.ui.view.*
import com.musasyihab.stuffwelike.util.Helper
import javax.inject.Inject

class ReviewActivity : AppCompatActivity(), ReviewContract.View {

    @Inject
    lateinit var presenter: ReviewContract.Presenter

    private lateinit var loading: ProgressBar
    private lateinit var errorPage: ErrorPageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)
        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        initView()
    }

    private fun initView() {
        loading = findViewById(R.id.review_loading)
        errorPage = findViewById(R.id.review_error)

        showProgress(false)
        errorPage.visibility = View.GONE

        errorPage.setListener(object: ErrorPageView.OnReloadClick{
            override fun clickReload() {
                Helper.fadeOut(errorPage)
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

    override fun showProgress(show: Boolean) {
        if(show) {
            loading.visibility = View.VISIBLE
        } else {
            loading.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Helper.fadeIn(errorPage)
    }

    override fun loadDataSuccess(response: GetArticleListModel) {
        // do something later
    }

}