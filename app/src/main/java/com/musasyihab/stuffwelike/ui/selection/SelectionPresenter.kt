package com.musasyihab.stuffwelike.ui.selection

import com.musasyihab.stuffwelike.api.ApiServiceInterface
import com.musasyihab.stuffwelike.model.GetArticleListModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SelectionPresenter: SelectionContract.Presenter {
    private val subscriptions = CompositeDisposable()
    var api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: SelectionContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: SelectionContract.View) {
        this.view = view
    }

    override fun getArticleList() {
        view.showProgress(true)
        val obs = api.getArticles()
        var subscribe = obs.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: GetArticleListModel? ->
                    view.showSelectionPage(true)
                    view.loadDataSuccess(response!!)
                    view.showProgress(false)
                }, { error ->
                    view.showErrorMessage(error.localizedMessage)
                    view.showProgress(false)
                    view.showSelectionPage(false)
                })

        subscriptions.add(subscribe)
    }
}