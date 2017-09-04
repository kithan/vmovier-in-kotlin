package com.kotlin.kunlun.vmovier_in_kotlin;

import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_web.*

/**
 * Created by hpb on 2017/8/12.
 */

class WebActivity : BaseActivity<IPresenter<IView>>() {
    override fun initPresenter(): BasePresenter<IView> {
        return null!!
    }

    override val contentView: Int
        get() = R.layout.activity_web


    override fun initTitle(): String = null!!

    var shareItem: MenuItem? = null

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_web_share, menu)
        shareItem = menu.findItem(R.id.action_share)
        shareItem?.isVisible = true
        shareItem?.isEnabled = true
        return true
    }

    inner class MyWebChromeClient : WebChromeClient() {
        override fun onReceivedTitle(view: WebView?, title: String?) {
            super.onReceivedTitle(view, title)
            initToolBar(title)
        }
    }

    inner class MyWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            if (!url!!.contains("redirect")) {
                view?.loadUrl(url)
            }
            return true
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            shareItem?.isVisible = true
            shareItem?.isEnabled = true
        }
    }

    override fun initViews() {
        webView.settings.javaScriptEnabled = true
        webView.settings.loadWithOverviewMode = true
        webView.setWebChromeClient(MyWebChromeClient())
        webView.setWebViewClient(MyWebViewClient())
        webView.loadUrl(intent.getStringExtra("url"))
    }

    override fun onResume() {
        super.onResume()
        webView.onResume()
    }

    override fun onPause() {
        super.onPause()
        webView.onPause()
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.action == KeyEvent.ACTION_UP) {
            if (webView.canGoBack()) {
                webView.goBack()
                return true
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    override fun onBackPressed() {
        super.onBackPressed();
        if (webView.canGoBack()) {
            webView.goBack();
        }
    }
}
