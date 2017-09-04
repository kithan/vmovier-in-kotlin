package com.kotlin.kunlun.vmovier_in_kotlin.channel;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.kotlin.kunlun.vmovier_in_kotlin.R;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostTab;
import com.vise.xsnow.loader.ILoader;
import com.vise.xsnow.loader.LoaderManager;

import java.util.List;

/**
 * Created by hpb on 2017/8/17.
 */

public class ChannelDetailAdapter extends BaseQuickAdapter<AdapterEntity, BaseViewHolder> {
    public ChannelDetailAdapter(@Nullable List<AdapterEntity> data) {
        super(R.layout.item_latest, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AdapterEntity data) {
        baseViewHolder.setText(R.id.tilte, data.getTitle());
        String cateNameAndLength = data.getSubName();
        if (!TextUtils.isEmpty(data.getDuration())) {
            int len = Integer.parseInt(data.getDuration());
            int minutes = len / 60;
            int secs = len - minutes * 60;
            cateNameAndLength += " / " + minutes + "'" + secs + "\"";
        }
        baseViewHolder.setText(R.id.cate_and_dur, cateNameAndLength);
        LoaderManager.getLoader().loadNet((ImageView) baseViewHolder.getView(R.id.iv_post)
                , data.getImageUrl(), new ILoader.Options(android.R.color.black, android.R.color.black));
    }
}
