package com.musasyihab.stuffwelike.ui.review

import com.musasyihab.stuffwelike.model.GetArticleListModel
import com.musasyihab.stuffwelike.ui.base.BaseContract

class ReviewContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage()
        fun loadDataSuccess(response: GetArticleListModel)
    }

    interface Presenter: BaseContract.Presenter<ReviewContract.View> {
        fun getArticleList()
    }
}