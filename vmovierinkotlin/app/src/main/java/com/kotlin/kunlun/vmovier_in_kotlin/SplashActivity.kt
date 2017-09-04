package com.kotlin.kunlun.vmovier_in_kotlin

import android.app.Activity
import android.content.Intent
import android.preference.PreferenceManager
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation


import com.kotlin.kunlun.vmovier_in_kotlin.home.view.MainActivity
import kotlinx.android.synthetic.main.activity_splash.*
/**
 * Created by 0000- on 2016/6/21.
 */
class SplashActivity : BaseActivity<IPresenter<IView>>() {

    override fun initPresenter(): BasePresenter<IView>? {
        return null
    }

    override val contentView: Int
        get() = R.layout.activity_splash

    override fun initTitle(): String {
        return ""
    }

    override fun initViews() {

        val scaleAnimation = ScaleAnimation(1f, 1.3f, 1f, 1.3f)
        scaleAnimation.fillAfter = true
        scaleAnimation.duration = 5000
        scaleAnimation.interpolator = LinearInterpolator()

        scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {

            }

            override fun onAnimationEnd(animation: Animation) {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }

            override fun onAnimationRepeat(animation: Animation) {

            }
        })
        splash.startAnimation(scaleAnimation)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_INTRO) {
            if (resultCode == Activity.RESULT_CANCELED) {
                PreferenceManager.getDefaultSharedPreferences(this).edit()
                        .putBoolean(PREF_KEY_FIRST_START, true)
                        .apply()
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        }
    }

    companion object {

        val PREF_KEY_FIRST_START = "com.example.hpb.PREF_KEY_START"
        val REQUEST_CODE_INTRO = 1
    }


}
