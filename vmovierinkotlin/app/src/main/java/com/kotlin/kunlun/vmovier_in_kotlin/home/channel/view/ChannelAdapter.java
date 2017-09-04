package com.kotlin.kunlun.vmovier_in_kotlin.home.channel.view;

import android.content.Context;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kotlin.kunlun.vmovier_in_kotlin.R;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Cate;
import com.vise.xsnow.loader.ILoader;

import java.util.List;

/**
 * Created by 0000- on 2016/6/9.
 */
public class ChannelAdapter extends BaseQuickAdapter<Cate, BaseViewHolder> {
    public ChannelAdapter(Context context, List<Cate> data) {
        super(R.layout.item_channel, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, Cate cate) {
        baseViewHolder.setText(R.id.tv_title, "#" + cate.getCatename() + "#");
        com.vise.xsnow.loader.LoaderManager.getLoader().loadNet((ImageView) baseViewHolder.getView(R.id.iv_channel)
                , cate.getIcon(), new ILoader.Options(R.mipmap.ic_launcher, R.mipmap.ic_launcher));
    }
}
