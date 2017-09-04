package com.kotlin.kunlun.vmovier_in_kotlin.player.presenter;

import com.kotlin.kunlun.vmovier_in_kotlin.IPresenter;
import com.kotlin.kunlun.vmovier_in_kotlin.player.view.IPlayerView;

/**
 * Created by 0000- on 2016/6/12.
 */
public interface IPlayerPresenter  extends IPresenter<IPlayerView>{
    public void getPostDetail(String postId);
    public void getComments(int p, String postId);
}
