package com.kotlin.kunlun.vmovier_in_kotlin.player.view;


import com.kotlin.kunlun.vmovier_in_kotlin.IView;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Comment;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostDetail;

import java.util.List;

/**
 * Created by 0000- on 2016/6/12.
 */
public interface IPlayerView  extends IView{
    public void onGetPostDetail(PostDetail detail);

    public void onGetComments(List<Comment> comments);


}
