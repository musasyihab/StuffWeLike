package com.musasyihab.stuffwelike

import com.musasyihab.stuffwelike.api.ApiServiceInterface
import com.musasyihab.stuffwelike.model.*
import com.musasyihab.stuffwelike.ui.selection.SelectionContract
import com.musasyihab.stuffwelike.ui.selection.SelectionPresenter
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*

class SelectionPresenterTest {
    @Mock
    lateinit var view: SelectionContract.View

    lateinit var presenter: SelectionPresenter
    var api: ApiServiceInterface = ApiServiceInterface.create()

    private val DUMMY_HREF = HrefModel("")
    private val DUMMY_LINK = LinksModel(DUMMY_HREF, Collections.emptyList(), DUMMY_HREF)
    private val DUMMY_EMBEDDED = EmbeddedModel(Collections.emptyList(), Collections.emptyList())
    private val DUMMY_RESPONSE = GetArticleListModel("", 0, DUMMY_LINK, DUMMY_EMBEDDED, "", "", "")


    @Before
    fun setupPresenter() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        MockitoAnnotations.initMocks(this)
        api = Mockito.mock(ApiServiceInterface::class.java)
        presenter = SelectionPresenter()
        presenter.api = api
        presenter.attach(view)
    }

    @Test
    fun testGetArticleList_success() {

        Mockito.`when`(api.getArticles()).thenReturn(Observable.just(DUMMY_RESPONSE))

        val testSubscriber = api.getArticles().test()
        api.getArticles().subscribe(testSubscriber)

        presenter.getArticleList()
        verify(view).showProgress(true)
        verify(view).showSelectionPage(true)
        verify(view).loadDataSuccess(DUMMY_RESPONSE)
        verify(view).showProgress(false)

    }

    @Test
    fun testGetArticleList_error() {
        val e = Exception("Network Error")
        Mockito.`when`(api.getArticles()).thenReturn(Observable.error(e))

        presenter.getArticleList()
        verify(view)!!.showProgress(true)
        verify(view).showSelectionPage(false)
        verify(view)!!.showErrorMessage("Network Error")
        verify(view)!!.showProgress(false)
    }
}