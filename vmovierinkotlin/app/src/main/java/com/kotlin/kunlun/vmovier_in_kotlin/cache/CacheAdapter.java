package com.kotlin.kunlun.vmovier_in_kotlin.cache;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kotlin.kunlun.vmovier_in_kotlin.R;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.VideoInfo;
import com.jakewharton.rxbinding2.view.RxView;
import com.vise.xsnow.loader.LoaderManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.functions.Consumer;


/**
 * Created by 0000- on 2016/6/22.
 */
public class CacheAdapter extends BaseQuickAdapter<VideoInfo, BaseViewHolder> {


    Context mContext;

    public CacheAdapter(Context context, List<VideoInfo> data) {
        super(R.layout.item_cache, data);
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, final VideoInfo videoInfo) {

        baseViewHolder.setText(R.id.tv_duration, videoInfo.getDuration())
                .setText(R.id.tv_title, videoInfo.getTitle())
                .setText(R.id.tv_size, videoInfo.getFilesize());
        LoaderManager.getLoader().loadNet((ImageView) baseViewHolder.getView(R.id.iv_pic), videoInfo.getImage(), null);
        RxView.clicks(baseViewHolder.getView(R.id.tv_detail)).throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
//                        Intent intent = new Intent(mContext, PlayerActivity.class);
//                        intent.putExtra("fromCache", true);
//                        intent.putExtra("url", videoInfo.getSource_link());
//                        intent.putExtra("postId", videoInfo.getPostId());
//                        mContext.startActivity(intent);
                    }
                });

    }
}
