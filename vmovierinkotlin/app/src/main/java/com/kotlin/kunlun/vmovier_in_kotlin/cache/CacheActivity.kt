package com.kotlin.kunlun.vmovier_in_kotlin.cache

import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.kotlin.kunlun.vmovier_in_kotlin.BaseActivity
import com.kotlin.kunlun.vmovier_in_kotlin.R
import com.kotlin.kunlun.vmovier_in_kotlin.entity.VideoInfo
import kotlinx.android.synthetic.main.activity_cache.*
import java.util.*


/**
 * Created by 0000- on 2016/6/22.
 */
class CacheActivity : BaseActivity<CachePresenter>(), ICacheView {
    override fun initPresenter(): CachePresenter {
        return CachePresenter()
    }

    override val contentView: Int
        get() = R.layout.activity_cache


    lateinit var cacheAdapter: CacheAdapter
    lateinit  var datas: List<VideoInfo>
    lateinit  var headView: View
    lateinit var downloadingText: TextView

    override fun initTitle(): String {
        return "缓存"
    }

    override fun initViews() {
        datas = ArrayList()
        cacheAdapter = CacheAdapter(this, datas)
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = cacheAdapter

        headView = LayoutInflater.from(this).inflate(R.layout.view_caching, null)
        downloadingText = headView.findViewById(R.id.tv_title) as TextView
        cacheAdapter.addHeaderView(headView)

        presenter?.initCacheList()

    }

    override fun updateCachedList(videoInfoList: List<VideoInfo>, cachingCount: Int, title: String) {
        downloadingText.text = "正在缓存\n$title($cachingCount)"
        cacheAdapter.addData(videoInfoList)
    }
}
