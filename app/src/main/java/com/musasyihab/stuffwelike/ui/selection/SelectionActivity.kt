package com.musasyihab.stuffwelike.ui.selection

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.musasyihab.stuffwelike.R
import com.musasyihab.stuffwelike.di.component.DaggerActivityComponent
import com.musasyihab.stuffwelike.di.module.ActivityModule
import com.musasyihab.stuffwelike.model.GetArticleListModel
import javax.inject.Inject

class SelectionActivity : AppCompatActivity(), SelectionContract.View {

    @Inject
    lateinit var presenter: SelectionContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        presenter.getArticleList()
    }

    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }

    override fun showProgress(show: Boolean) {
        // do something later
    }

    override fun showErrorMessage(error: String) {
        // do something later
    }

    override fun loadDataSuccess(response: GetArticleListModel) {
        // do something later
    }

}

