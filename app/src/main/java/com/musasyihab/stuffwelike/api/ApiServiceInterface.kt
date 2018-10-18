package com.musasyihab.stuffwelike.api

import com.musasyihab.stuffwelike.BuildConfig
import com.musasyihab.stuffwelike.model.GetArticleListModel
import com.musasyihab.stuffwelike.util.Constants
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiServiceInterface {

    @GET("articles?appDomain=1&locale=de_DE&limit=10")
    fun getArticles(): Observable<GetArticleListModel>

    companion object Factory {
        fun create(): ApiServiceInterface {
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                builder.addInterceptor(interceptor)
            }
            val client = builder.build()
            val retrofit = retrofit2.Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Constants.BASE_URL)
                    .client(client)
                    .build()

            return retrofit.create(ApiServiceInterface::class.java)
        }
    }
}