package com.kotlin.kunlun.vmovier_in_kotlin.home.channel.presenter;

import com.kotlin.kunlun.vmovier_in_kotlin.BasePresenter;
import com.kotlin.kunlun.vmovier_in_kotlin.home.channel.view.IChannelView;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Cate;

import java.util.List;

import io.reactivex.functions.Consumer;


/**
 * Created by 0000- on 2016/6/9.
 */
public class ChannelPresenter extends BasePresenter<IChannelView> {


    public void getAllChannel() {
        getVmovierApi()
                .getCateList().compose(this.<List<Cate>>transformResult())
        .subscribe(new Consumer<List<Cate>>() {
            @Override
            public void accept(List<Cate> cates) throws Exception {
                getMvpView().onLoadChannels(cates);
            }
        });
    }

}
