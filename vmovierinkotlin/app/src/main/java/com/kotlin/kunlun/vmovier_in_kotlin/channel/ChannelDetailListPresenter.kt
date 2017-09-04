package com.kotlin.kunlun.vmovier_in_kotlin.channel


import com.kotlin.kunlun.vmovier_in_kotlin.BasePresenter
import com.kotlin.kunlun.vmovier_in_kotlin.SimpleDisposableObserver
import com.kotlin.kunlun.vmovier_in_kotlin.data.HttpResult
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostDetail
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostTab
import com.vise.log.ViseLog
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers

import io.reactivex.schedulers.Schedulers
import java.util.*

class ChannelDetailListPresenter : BasePresenter<ChannelDetailListContract.View>(), ChannelDetailListContract.Presenter {

    override fun initialize(cateId: Int, cateName: String) {
        Observable.just(cateId).flatMap({ cateId ->
            when (cateId) {
                0 ->
                    when (cateName) {
                        "热门" -> vmovierApi.getChannelList(1, 10, "hot")
                                .compose(this.transformResult())
                                .map({ pts: List<PostTab> -> AdapterEntity.getListFromPostTab(pts) })
                        "专辑" -> vmovierApi.getChannelList(1, 10, "album")
                                .compose(this.transformResult())
                                .map({ pts: List<PostTab> -> AdapterEntity.getListFromPostTab(pts) })
                        else -> Observable.just(Collections.emptyList())
                    }
                else -> vmovierApi.getPostInCate(1, cateId)
                        .compose(this.transformResult())
                        .map { t: List<PostDetail> ->
                            (AdapterEntity.getListFromPostDetail(t))
                        }

            }
        }).subscribe({ value: List<AdapterEntity> ->
            mvpView!!.onLoadData(value)
        }, { error: Throwable ->
            ViseLog.e("error")
        })

    }

    private fun getAlbumOrHotList(page: Int, tab: String) {
        vmovierApi.getChannelList(page, 10, tab)
                .compose(this.transformResult())
                .subscribe { postTabs -> mvpView!!.onLoadAlbumOrHot(postTabs, tab == "album") }
    }

    override fun getAlbumList(page: Int) {
        getAlbumOrHotList(page, "album")
    }

    override fun getHotList(page: Int) {
        getAlbumOrHotList(page, "hot")
    }

    override fun getSeries(page: Int) {
        vmovierApi.getSeries(page)
    }

    override fun getBackstageCate(page: Int) {
        vmovierApi.backstageCate
    }

    override fun getPostByCate(page: Int, cateId: Int) {
        vmovierApi.getPostInCate(page, cateId)
    }
}