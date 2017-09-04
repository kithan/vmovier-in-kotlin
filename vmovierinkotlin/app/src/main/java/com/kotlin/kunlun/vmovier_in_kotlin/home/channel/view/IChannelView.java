package com.kotlin.kunlun.vmovier_in_kotlin.home.channel.view;


import com.kotlin.kunlun.vmovier_in_kotlin.IView;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Cate;

import java.util.List;

/**
 * Created by 0000- on 2016/6/9.
 */
public interface IChannelView extends IView{
  public void onLoadChannels(List<Cate> cates);
}
