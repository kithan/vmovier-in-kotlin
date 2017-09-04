package com.kotlin.kunlun.vmovier_in_kotlin


//import com.example.kunlun.data.HttpResult
//import com.example.kunlun.data.VmovierApi
//import com.example.kunlun.db.DbHelper
//import com.example.kunlun.db.VideoInfoDao
import com.kotlin.kunlun.vmovier_in_kotlin.data.ApiException
import com.kotlin.kunlun.vmovier_in_kotlin.data.HttpResult
import com.kotlin.kunlun.vmovier_in_kotlin.data.VmovierApi
import com.kotlin.kunlun.vmovier_in_kotlin.db.DbHelper
import com.kotlin.kunlun.vmovier_in_kotlin.db.VideoInfoDao
import com.trello.rxlifecycle2.LifecycleProvider
import com.vise.xsnow.http.ViseHttp

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers

abstract class BasePresenter<T : IView> : IPresenter<T> {


    companion object {
        @JvmStatic
        val vmovierApi: VmovierApi = ViseHttp.RETROFIT<Any>().create(VmovierApi::class.java)
        @JvmStatic
        val videoInfoDao: VideoInfoDao = DbHelper.instance.daoSession!!.videoInfoDao

    }

    var mvpView: T?=null
    //
    var provider: LifecycleProvider<*>? = null

    inner class MyObservable<T> : ObservableTransformer<HttpResult<T>, T> {
        override fun apply(upstream: Observable<HttpResult<T>>): ObservableSource<T> {
            val tObservable = upstream!!.flatMap(Function<HttpResult<T>, ObservableSource<T>> { tHttpResult ->
                if (tHttpResult.status !== 0) {
                    return@Function Observable.error(ApiException(
                            "status:" + tHttpResult.status + "message:" + tHttpResult.msg))
                }
                Observable.create { e ->
                    e.onNext(tHttpResult.data!!)
                    e.onComplete()
                }
            }).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
            tObservable.compose(provider?.bindToLifecycle())
            return tObservable
        }
    }

    fun <T> transformResult(): ObservableTransformer<HttpResult<T>, T> {
        return MyObservable<T>()
    }


    override fun attachView(mView: IView) {
        this.mvpView = mView as T
        if (mView is LifecycleProvider<*>) {
            provider = mView
        }
    }


    override fun detachView() {
        mvpView = null
    }


}

