package com.kotlin.kunlun.vmovier_in_kotlin.home.latest.view;


import com.kotlin.kunlun.vmovier_in_kotlin.IView;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Banner;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostSection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hpb on 16/6/8.
 */
public interface ILatestView extends IView {
    public void onReloadList(ArrayList<PostSection> sections);
    public void onLoadList(ArrayList<PostSection> sections);
    public void onGetBanner(List<Banner> banners);
}
