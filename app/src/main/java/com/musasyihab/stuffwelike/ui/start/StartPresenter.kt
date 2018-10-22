package com.musasyihab.stuffwelike.ui.start

import io.reactivex.disposables.CompositeDisposable

class StartPresenter: StartContract.Presenter {
    private val subscriptions = CompositeDisposable()
    private lateinit var view: StartContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: StartContract.View) {
        this.view = view
    }

}