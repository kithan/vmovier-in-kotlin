package com.kotlin.kunlun.vmovier_in_kotlin.home.channel.view

import android.content.Intent
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast


import com.chad.library.adapter.base.BaseQuickAdapter
import com.kotlin.kunlun.vmovier_in_kotlin.BaseFragment
import com.kotlin.kunlun.vmovier_in_kotlin.R
import com.kotlin.kunlun.vmovier_in_kotlin.channel.ChannelDetailListActivity
import com.kotlin.kunlun.vmovier_in_kotlin.home.channel.presenter.ChannelPresenter
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Cate

import java.util.ArrayList
import kotlinx.android.synthetic.main.frament_channel.*

/**
 * Created by 0000- on 2016/6/9.
 */
class ChannelFragment : BaseFragment<ChannelPresenter>(), IChannelView {


    private lateinit var channelAdapter: ChannelAdapter
    override val contentView: Int
        get() = R.layout.frament_channel

    override fun initPresenter(): ChannelPresenter {
        return ChannelPresenter()
    }

    override fun initViews() {
        rv_channel.layoutManager = GridLayoutManager(context, 2)
        rv_channel.setFadingEdgeLength(0)
        rv_channel.offsetChildrenVertical(0)
        presenter?.getAllChannel()
    }

    private fun initAdapter(cates: List<Cate>) {
        val cateList = ArrayList<Cate>()
        val cate = Cate()
        cate.catename = "热门"
        cate.icon = "http://cs.vmoiver.com/Uploads/cover/2017-08-16/59946164d0ddb_cut.jpeg@600w_400h_1e_1c.jpg"
        cateList.add(cate)

        val subjectCate = Cate()
        subjectCate.catename = "专题"
        subjectCate.icon = "http://cs.vmoiver.com/Uploads/cover/2017-08-16/59946164d0ddb_cut.jpeg@600w_400h_1e_1c.jpg"
        cateList.add(subjectCate)
        val backCate = Cate()
        backCate.catename = "幕后"
        backCate.icon = "http://cs.vmoiver.com/Uploads/cover/2017-08-16/59946164d0ddb_cut.jpeg@600w_400h_1e_1c.jpg"
        cateList.add(backCate)
        val seriesCate = Cate()
        seriesCate.catename = "系列"
        seriesCate.icon = "http://cs.vmoiver.com/Uploads/cover/2017-08-16/59946164d0ddb_cut.jpeg@600w_400h_1e_1c.jpg"
        cateList.add(seriesCate)
        cateList.addAll(cates)
        channelAdapter = ChannelAdapter(activity, cateList)
        channelAdapter.onItemClickListener = BaseQuickAdapter.OnItemClickListener { adapter, view, position ->
            Toast.makeText(context, channelAdapter.getItem(position)!!.catename + " clicked",
                    Toast.LENGTH_SHORT).show()
            if (cateList[position].catename != "幕后"
                    || cateList[position].catename != "专题") {
                var intent = Intent(context, ChannelDetailListActivity::class.java)
                intent.putExtra("cateId", cateList[position].cateid)
                intent.putExtra("cateName", cateList[position].catename)
                startActivity(intent)
            }else {

            }
        }
        rv_channel.adapter = channelAdapter
    }

    override fun onLoadChannels(cates: List<Cate>) {
        initAdapter(cates)
    }
}
