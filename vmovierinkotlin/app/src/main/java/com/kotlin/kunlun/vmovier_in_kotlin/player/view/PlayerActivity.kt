package com.kotlin.kunlun.vmovier_in_kotlin.player.view

import android.annotation.TargetApi
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.text.TextUtils
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import com.kotlin.kunlun.vmovier_in_kotlin.BaseActivity
import com.kotlin.kunlun.vmovier_in_kotlin.R
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Comment
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostDetail
import com.kotlin.kunlun.vmovier_in_kotlin.entity.VideoInfo
import com.kotlin.kunlun.vmovier_in_kotlin.player.presenter.PlayerPresenter
import com.vise.xsnow.loader.LoaderManager
import fm.jiecao.jcvideoplayer_lib.JCFullScreenActivity
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard
import kotlinx.android.synthetic.main.activity_player.*
import java.util.zip.GZIPOutputStream

/**
 * Created by 0000- on 2016/6/12.
 * 专题详情页面：包含播放 or无播放
 */
class PlayerActivity : BaseActivity<PlayerPresenter>(), IPlayerView {


    lateinit var url: String
    lateinit var postId: String
    var fromCache = false

    override fun initTitle(): String {
        return ""
    }


    override val contentView: Int
        get() = R.layout.activity_player

    @TargetApi(17)
    override fun initViews() {
        iv_bottom_download.setOnClickListener { _ ->
            val videoInfo = VideoInfo()
            videoInfo.title = title
            videoInfo.source_link = videoPath
            presenter?.addDownload(videoInfo)
        }
        postId = intent.getStringExtra("postId")
        url = intent.getStringExtra("url")
        fromCache = intent.getBooleanExtra("fromCache", false)
        webView!!.settings.javaScriptEnabled = true
        webView!!.settings.loadWithOverviewMode = true
        webView!!.setWebViewClient(object : WebViewClient() {

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (!url.contains("redirect")) {
                    view.loadUrl(url)
                }
                return true
            }
        })
        if (!TextUtils.isEmpty(url)) {
            webView.loadUrl(url)
        } else {
            webView.loadUrl("http://www.vmovier.com/" + postId)
            videoplayer.visibility = View.GONE
            iv_bottom_download.visibility = View.GONE
        }


        presenter?.getPostDetail(postId)
        presenter?.getComments(1, postId)

    }

    internal var title: String? = null
    internal var videoPath: String? = null

    override fun onGetPostDetail(detail: PostDetail) {
        LoaderManager.getLoader().loadNet(poster, detail.image, null)
        if (!TextUtils.isEmpty(url)) {
            title = detail.title
            videoPath = detail.content!!.video!![0].qiniu_url
            videoplayer.setUp(videoPath, title!!)
            videoplayer.startButton.performClick()

            if (fromCache) {
                requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            }
        }
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val landscape = newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE
        if (landscape) {
            layout_bottom.visibility = View.GONE
            JCFullScreenActivity.startActivity(this,
                    videoPath,
                    JCVideoPlayerStandard::class.java, title)
        } else {
            layout_bottom.setVisibility(View.VISIBLE);
        }
    }


    override fun onPause() {
        super.onPause()
        JCVideoPlayer.releaseAllVideos()
    }

    override fun onGetComments(comments: List<Comment>) {

    }


    override fun initPresenter(): PlayerPresenter {
        return PlayerPresenter()
    }

    companion object {

        val PLAY_ACTION = 0xa1
        val PAUSE_ACTION = 0xa2
        val STOP_ACTION = 0xa3
        val GET_MEDIA_INFO_ACTION = 0xa4
        val GET_POSITION_INFO_ACTION = 0xa5
        val RESUME_SEEKBAR_ACTION = 0xa6
        val GET_VOLUME_ACTION = 0xa7
        val SET_VOLUME_ACTION = 0xa8
    }


}
