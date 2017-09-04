package com.kotlin.kunlun.vmovier_in_kotlin;

import com.vise.log.ViseLog;
import com.vise.xsnow.event.BusManager;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by hpb on 2017/8/22.
 */

open class SimpleDisposableObserver<T> : DisposableObserver<T>() {
    override fun onNext(t: T) {
    }

    override fun onError(e: Throwable) {
        e.printStackTrace()
        ViseLog.w(e.message)
    }

    override fun onComplete() {

    }
}
