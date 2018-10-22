package com.musasyihab.stuffwelike.ui.start

import com.musasyihab.stuffwelike.ui.base.BaseContract

class StartContract {

    interface View: BaseContract.View {
        fun showPage()
    }

    interface Presenter: BaseContract.Presenter<StartContract.View>
}