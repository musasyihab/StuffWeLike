package com.musasyihab.stuffwelike.ui.start

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.musasyihab.stuffwelike.R
import com.musasyihab.stuffwelike.di.component.DaggerActivityComponent
import com.musasyihab.stuffwelike.di.module.ActivityModule
import com.musasyihab.stuffwelike.ui.selection.SelectionActivity
import com.musasyihab.stuffwelike.ui.view.SelectionStartPage
import com.musasyihab.stuffwelike.util.Helper
import javax.inject.Inject

class StartActivity : AppCompatActivity(), StartContract.View {


    @Inject
    lateinit var presenter: StartContract.Presenter

    private lateinit var startView: SelectionStartPage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)
        injectDependency()
        presenter.attach(this)
        presenter.subscribe()

        initView()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if(hasFocus) showPage()
    }

    private fun initView() {
        startView = findViewById(R.id.start_view)
        startView.visibility = View.GONE


        startView.setListener(object: SelectionStartPage.OnStartClick{
            override fun clickStart() {
                val selectionIntent = Intent(this@StartActivity, SelectionActivity::class.java)
                startActivity(selectionIntent)
            }
        })

    }


    private fun injectDependency() {
        val activityComponent = DaggerActivityComponent.builder()
                .activityModule(ActivityModule(this))
                .build()

        activityComponent.inject(this)
    }

    override fun showPage() {
        Helper.fadeIn(startView)
    }

}