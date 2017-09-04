package com.kotlin.kunlun.vmovier_in_kotlin.home.menu

import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.kotlin.kunlun.vmovier_in_kotlin.BaseFragment
import com.kotlin.kunlun.vmovier_in_kotlin.R
import kotlinx.android.synthetic.main.frag_menu.*

/**
 * Created by 0000- on 2016/7/1.
 */
class MenuFragment : BaseFragment<MenuPresenter>() ,View.OnClickListener,IMenuView{

    override fun initPresenter(): MenuPresenter {
        return MenuPresenter(activity)
    }

    override val contentView: Int
        get() = R.layout.frag_menu

    override fun initViews() {

        ll_main.setOnClickListener(this)
        ll_series.setOnClickListener(this)
        ll_behind.setOnClickListener(this)
        iv_close.setOnClickListener(this)
        tv_subscribe.setOnClickListener(this)
        tv_download.setOnClickListener(this)
        tv_like.setOnClickListener(this)
    }


    override  fun onClick(view: View) {
        when (view.id) {
            R.id.ll_main -> {
            }
            R.id.ll_series -> {
            }
            R.id.ll_behind -> {
            }
            R.id.iv_close -> presenter.hide()
            R.id.tv_subscribe -> {
            }
            R.id.tv_download -> {
            }
            R.id.tv_like -> {
            }
        }//                startActivity(new Intent(getActivity(), CacheActivity.class));
    }

}
