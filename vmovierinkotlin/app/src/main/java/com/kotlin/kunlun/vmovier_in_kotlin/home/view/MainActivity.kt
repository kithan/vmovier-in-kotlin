package com.kotlin.kunlun.vmovier_in_kotlin.home.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.ViewPager
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.RelativeLayout

import com.kotlin.kunlun.vmovier_in_kotlin.BaseActivity
import com.kotlin.kunlun.vmovier_in_kotlin.BasePresenter
import com.kotlin.kunlun.vmovier_in_kotlin.R
import com.kotlin.kunlun.vmovier_in_kotlin.home.channel.view.ChannelFragment
import com.kotlin.kunlun.vmovier_in_kotlin.home.latest.presenter.LatestPresenter
import com.kotlin.kunlun.vmovier_in_kotlin.home.latest.view.LatestFragment
import com.kotlin.kunlun.vmovier_in_kotlin.home.menu.MenuFragment
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.vise.xsnow.event.IEvent
import com.vise.xsnow.event.Subscribe

import kotlinx.android.synthetic.main.activity_main.*

//import com.example.kunlun.dlna.upnp.SystemManager;
//import com.example.kunlun.dlna.upnp.SystemService;
//import com.example.kunlun.dlna.upnp.VmovierUpnpService;

class MainActivity : RxAppCompatActivity() {
    private lateinit var mSectionsPagerAdapter: SectionsPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    fun initViews() {

        menu_left.setOnClickListener { _ ->
            val transaction = supportFragmentManager.beginTransaction()
            if (menuFragment == null) {
                menuFragment = MenuFragment()
                transaction.add(R.id.menu, menuFragment)
            } else {
                transaction.show(menuFragment)
            }
            transaction.commit()
        }
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        container.adapter = mSectionsPagerAdapter
        container.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if (position == 0) {
                    tabs.check(R.id.rb_latest)
                } else {
                    tabs.check(R.id.rb_channel)
                }
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })

        tabs!!.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.rb_latest) {
                container.currentItem = 0
            } else {
                container.currentItem = 1
            }
        }


        //        Intent upnpServiceIntent = new Intent(this, VmovierUpnpService.class);
        //        bindService(upnpServiceIntent, mUpnpServiceConnection, Context.BIND_AUTO_CREATE);
        //        Intent systemServiceIntent = new Intent(this, SystemService.class);
        //        bindService(systemServiceIntent, mSystemServiceConnection, Context.BIND_AUTO_CREATE);


    }

    @Subscribe
    fun updateTitle(event: IEvent) {
        if (event is LatestPresenter.UpdateTabTitleEvent) {
            rb_latest.text = event.title
        }
    }


    override fun onResume() {
        super.onResume()
    }


    internal var menuFragment: MenuFragment? = null

    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            return if (position == 0) {
                LatestFragment.newInstance()
            } else Fragment.instantiate(applicationContext, ChannelFragment::class.java.name)
        }

        override fun getCount(): Int {
            return 2
        }

        override fun getPageTitle(position: Int): CharSequence {
            return if (position == 0) {
                "最新"
            } else "频道"
        }
    }
}
