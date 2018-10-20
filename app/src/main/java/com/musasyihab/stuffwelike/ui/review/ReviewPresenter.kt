package com.musasyihab.stuffwelike.ui.review

import com.musasyihab.stuffwelike.api.ApiServiceInterface
import com.musasyihab.stuffwelike.model.GetArticleListModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class ReviewPresenter: ReviewContract.Presenter {
    private val subscriptions = CompositeDisposable()
    var api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: ReviewContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ReviewContract.View) {
        this.view = view
    }

    override fun getArticleList() {
        view.showProgress(true)
        val obs = api.getArticles()
        var subscribe = obs.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: GetArticleListModel? ->
                    view.loadDataSuccess(response!!)
                    view.showProgress(false)
                }, { error ->
                    error.printStackTrace()
                    view.showErrorMessage()
                    view.showProgress(false)
                })

        subscriptions.add(subscribe)
    }
}