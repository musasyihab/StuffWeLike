package com.musasyihab.stuffwelike

import com.musasyihab.stuffwelike.api.ApiServiceInterface
import com.musasyihab.stuffwelike.model.EmbeddedModel
import com.musasyihab.stuffwelike.model.GetArticleListModel
import com.musasyihab.stuffwelike.model.HrefModel
import com.musasyihab.stuffwelike.model.LinksModel
import com.musasyihab.stuffwelike.ui.review.ReviewContract
import com.musasyihab.stuffwelike.ui.review.ReviewPresenter
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import java.util.*

class ReviewPresenterTest {
    @Mock
    lateinit var view: ReviewContract.View

    lateinit var presenter: ReviewPresenter
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
        presenter = ReviewPresenter()
        presenter.api = api
        presenter.attach(view)
    }

    @Test
    fun testGetArticleList_success() {

        Mockito.`when`(api.getArticles()).thenReturn(Observable.just(DUMMY_RESPONSE))

        val testSubscriber = api.getArticles().test()
        api.getArticles().subscribe(testSubscriber)

        presenter.getArticleList()
        Mockito.verify(view).showProgress(true)
        Mockito.verify(view).loadDataSuccess(DUMMY_RESPONSE)
        Mockito.verify(view).showProgress(false)

    }

    @Test
    fun testGetArticleList_error() {
        val e = Exception("Network Error")
        Mockito.`when`(api.getArticles()).thenReturn(Observable.error(e))

        presenter.getArticleList()
        Mockito.verify(view)!!.showProgress(true)
        Mockito.verify(view)!!.showErrorMessage()
        Mockito.verify(view)!!.showProgress(false)
    }
}