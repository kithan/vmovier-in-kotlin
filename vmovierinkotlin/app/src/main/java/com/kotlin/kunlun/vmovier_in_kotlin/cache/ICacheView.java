package com.kotlin.kunlun.vmovier_in_kotlin.cache;


import com.kotlin.kunlun.vmovier_in_kotlin.IView;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.VideoInfo;

import java.util.List;

/**
 * Created by 0000- on 2016/6/22.
 */
public interface ICacheView  extends IView{
    public void updateCachedList(List<VideoInfo> videoInfoList, int cachingCount, String title);
}
