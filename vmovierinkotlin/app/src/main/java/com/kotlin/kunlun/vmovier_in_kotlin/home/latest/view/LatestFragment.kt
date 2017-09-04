package com.kotlin.kunlun.vmovier_in_kotlin.home.latest.view

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jakewharton.rxbinding2.view.RxView
import com.kotlin.kunlun.vmovier_in_kotlin.BaseFragment
import com.kotlin.kunlun.vmovier_in_kotlin.R
import com.kotlin.kunlun.vmovier_in_kotlin.View.AutoScrollViewPager
import com.kotlin.kunlun.vmovier_in_kotlin.View.LastestPagerNavigator
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Banner
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostSection
import com.kotlin.kunlun.vmovier_in_kotlin.home.latest.adapter.BannerAdapter
import com.kotlin.kunlun.vmovier_in_kotlin.home.latest.adapter.LatestAdapter
import com.kotlin.kunlun.vmovier_in_kotlin.home.latest.presenter.LatestPresenter
import net.lucode.hackware.magicindicator.MagicIndicator
import net.lucode.hackware.magicindicator.ViewPagerHelper
import java.util.*
import kotlinx.android.synthetic.main.fragment_latest.*

/**
 * Created by hpb on 16/6/8.
 */
class LatestFragment : BaseFragment<LatestPresenter>(), ILatestView, BaseQuickAdapter.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {


    lateinit var viewPager: AutoScrollViewPager
    lateinit var latestAdapter: LatestAdapter
    internal var sections: List<PostSection> = ArrayList()


    override val contentView: Int
        get() = R.layout.fragment_latest

    lateinit var headerView: View
    lateinit var indicator: MagicIndicator

    override fun initViews() {


        headerView = LayoutInflater.from(activity).inflate(R.layout.latest_header, null)
        initAdapter(sections)
        viewPager = headerView.findViewById(R.id.vp) as AutoScrollViewPager
        indicator = headerView.findViewById(R.id.indicator) as MagicIndicator

        viewPager.interval = 3000
        viewPager.setAutoScrollDurationFactor(10.0)

        presenter.loadTabPost(1)
        presenter.loadBanner()
    }

    private fun initAdapter(sections: List<PostSection>) {
        this.sections = sections
        latestAdapter = LatestAdapter(context, sections)
        latestAdapter.onItemClickListener=this
        latestAdapter.addHeaderView(headerView)
        val linearLayoutManager = LinearLayoutManager(context)
        list_post.layoutManager=linearLayoutManager
        list_post.adapter=latestAdapter
        list_post.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                presenter.onListScroll(recyclerView, latestAdapter, newState)
            }
        })
        layout_refresh.setOnRefreshListener(this)
        latestAdapter.setOnLoadMoreListener(this, list_post)
    }

    override fun onItemClick(adapter: BaseQuickAdapter<*, *>, view: View, position: Int) {
        val section = latestAdapter.getItem(position)
        if (!section!!.isHeader) {
            //            Intent intent = new Intent(getActivity(), PlayerActivity.class);
            //            intent.putExtra("postId", section.t.getPostid());
            //            intent.putExtra("url", section.t.getRequest_url());
            //            intent.putExtra("fromCache", false);
            //            startActivityWithTransition(intent, view, R.string.share_element_player);
        }
    }


    override fun initPresenter(): LatestPresenter {
        return LatestPresenter()
    }


    override fun onReloadList(sections: ArrayList<PostSection>) {
        latestAdapter.setNewData(sections)
        layout_refresh.setRefreshing(false)
    }

    override fun onLoadList(sections: ArrayList<PostSection>) {
        latestAdapter.addData(sections)
        latestAdapter.loadMoreComplete()
    }

    override fun onGetBanner(banners: List<Banner>) {
        val views = ArrayList<View>()
        for (banner in banners) {
            val view = LayoutInflater.from(activity).inflate(R.layout.item_banner, null)
            RxView.clicks(view).subscribe {
                //                    if (banner.getExtra_data().getApp_banner_param().startsWith("http")) {
                //                        Intent intent = new Intent(getContext(), WebActivity.class);
                //                        intent.putExtra("url", banner.getExtra_data().getApp_banner_param());
                //                        startActivityWithTransition(intent, view, R.string.share_element_web);
                //                    } else if (banner.getExtra_data().getIs_album().equals("1")) {
                //                        //专题页面
                //                        Intent intent = new Intent(getContext(), PlayerActivity.class);
                //                        intent.putExtra("postId", banner.getExtra_data().getApp_banner_param());
                //                        startActivityWithTransition(intent, view, R.string.share_element_web);
                //                    } else {
                //                        Intent intent = new Intent(getActivity(), PlayerActivity.class);
                //                        intent.putExtra("postId", banner.getExtra_data().getApp_banner_param());
                //                        intent.putExtra("fromCache", false);
                //                        startActivityWithTransition(intent, view, R.string.share_element_player);
                //                    }
            }
            view.tag = banner
            views.add(view)
        }
        viewPager.adapter = BannerAdapter(views)
        val circleNavigator = LastestPagerNavigator(activity)
        circleNavigator.setFollowTouch(false)
        circleNavigator.setRectCount(banners.size)
        indicator.navigator = circleNavigator
        ViewPagerHelper.bind(indicator, viewPager)
        if (!isScroll) {
            viewPager.startAutoScroll()
            isScroll = true
        }
    }

    internal var isScroll: Boolean = false

    override fun onResume() {
        super.onResume()
        if (!isScroll) {
            viewPager.startAutoScroll()
            isScroll = true
        }
    }

    override fun onPause() {
        super.onPause()
        if (isScroll) {
            viewPager.stopAutoScroll()
            isScroll = false
        }

    }

    override fun onRefresh() {
        presenter.onRefresh()
    }

    override fun onLoadMoreRequested() {
        presenter.onLoadMore()
    }


    override fun onDestroy() {
        super.onDestroy()
        viewPager.stopAutoScroll()
    }

    companion object {

        fun newInstance(): LatestFragment {
            return LatestFragment()
        }
    }
}
