package com.musasyihab.stuffwelike.ui.selection

import com.musasyihab.stuffwelike.model.GetArticleListModel
import com.musasyihab.stuffwelike.ui.base.BaseContract

class SelectionContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showSelectionPage(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(response: GetArticleListModel)
    }

    interface Presenter: BaseContract.Presenter<SelectionContract.View> {
        fun getArticleList()
    }
}