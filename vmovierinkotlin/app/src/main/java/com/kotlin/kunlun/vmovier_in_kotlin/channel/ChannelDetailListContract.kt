package com.kotlin.kunlun.vmovier_in_kotlin.channel


import com.kotlin.kunlun.vmovier_in_kotlin.IPresenter
import com.kotlin.kunlun.vmovier_in_kotlin.IView
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostTab

interface ChannelDetailListContract {

    interface View : IView {
        fun onLoadData(aeList:List<AdapterEntity>)
        fun onLoadAlbumOrHot(postTabList: List<PostTab>, isAlbum: Boolean)

    }

    interface Presenter : IPresenter<View> {
        fun getAlbumList(page: Int)

        fun getHotList(page: Int)

        fun initialize(cateId: Int, cateName: String)

        fun getSeries(page: Int)

        fun getBackstageCate(page: Int)

        fun getPostByCate(page: Int, cateId: Int)
    }
}