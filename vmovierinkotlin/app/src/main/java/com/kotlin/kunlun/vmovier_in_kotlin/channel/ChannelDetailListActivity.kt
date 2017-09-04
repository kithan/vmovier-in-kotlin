package com.kotlin.kunlun.vmovier_in_kotlin.channel


import android.support.v7.widget.LinearLayoutManager

import com.kotlin.kunlun.vmovier_in_kotlin.BaseActivity
import com.kotlin.kunlun.vmovier_in_kotlin.R
import com.kotlin.kunlun.vmovier_in_kotlin.data.HttpResult
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostTab
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate
import  kotlinx.android.synthetic.main.activity_channel_list.*
import java.util.*


class ChannelDetailListActivity : BaseActivity<ChannelDetailListContract.Presenter>(), ChannelDetailListContract.View {

    override fun initPresenter(): ChannelDetailListContract.Presenter {
        return ChannelDetailListPresenter()
    }

    override fun initTitle(): String {
        return getString(R.string.title_channel_detail)
    }

    override val contentView: Int
        get() = R.layout.activity_channel_list

    lateinit var channelDetailAdapter: ChannelDetailAdapter

    override fun initViews() {
        list_post.layoutManager = LinearLayoutManager(this)
        var cateId = intent.getIntExtra("cateId", 0)
        var name = intent.getStringExtra("cateName")
        presenter?.initialize(cateId, name)
    }

    override fun onLoadAlbumOrHot(postTabList: List<PostTab>, isAlbum: Boolean) {
        channelDetailAdapter = ChannelDetailAdapter(AdapterEntity.getListFromPostTab(postTabList))
        if (isAlbum) {

        } else {
        }
    }

    override fun onLoadData(aeList: List<AdapterEntity>) {
        channelDetailAdapter = ChannelDetailAdapter(aeList)
        list_post.adapter = channelDetailAdapter
    }
}