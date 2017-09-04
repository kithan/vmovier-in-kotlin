package com.kotlin.kunlun.vmovier_in_kotlin.home.latest.presenter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.kotlin.kunlun.vmovier_in_kotlin.BasePresenter;
import com.kotlin.kunlun.vmovier_in_kotlin.SimpleDisposableObserver;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Banner;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostSection;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostTab;
import com.kotlin.kunlun.vmovier_in_kotlin.home.latest.view.ILatestView;
import com.vise.xsnow.event.BusManager;
import com.vise.xsnow.event.IEvent;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.reactivex.functions.Consumer;

/**
 * Created by hpb on 16/6/8.
 */
public class LatestPresenter extends BasePresenter<ILatestView> {


    public void loadBanner() {
        getVmovierApi()
                .getBanner()
                .compose(this.<List<Banner>>transformResult())
                .subscribe(new SimpleDisposableObserver<List<Banner>>() {
                    @Override
                    public void onNext(List<Banner> banners) {
                        getMvpView().onGetBanner(banners);
                    }
                });
    }

    String day = "";
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM.dd");
    String today = dateFormat.format(new Date());

    public void loadTabPost(final int page) {
        getVmovierApi()
                .getTabPost(page)
                .compose(this.<List<PostTab>>transformResult())
                .subscribe(new SimpleDisposableObserver<List<PostTab>>() {
                    @Override
                    public void onNext(List<PostTab> postTabs) {
                        LatestPresenter.this.page = page;
                        ArrayList<PostSection> sections = new ArrayList<PostSection>();
                        if (page == 1) {
                            sections.clear();
                            day = "";
                        }
                        for (PostTab tab : postTabs) {
                            String curr = dateFormat.format(new Date(tab.getPublish_time() * 1000));
                            if (!TextUtils.isEmpty(day) && !day.equals(curr)) {
                                sections.add(new PostSection(true, "- " + curr + " -"));
                            }
                            sections.add(new PostSection(tab));
                            day = curr;
                        }
                        if (page == 1) {
                            getMvpView().onReloadList(sections);
                        } else {
                            getMvpView().onLoadList(sections);
                        }
                    }
                });
    }

    int page = 1;

    public void onRefresh() {
        page = 1;
        loadTabPost(page);
    }

    public void onLoadMore() {
        page++;
        loadTabPost(page);
    }

    String tabTitle = "";

    public void onListScroll(RecyclerView recyclerView, BaseQuickAdapter adapter, int newState) {
        if (newState == RecyclerView.SCROLL_STATE_IDLE) {
            int pos = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
            PostSection section = (PostSection) adapter.getItem(pos);
            if (!section.isHeader) {
                String curr = dateFormat.format(new Date(section.t.getPublish_time() * 1000));
                if (!tabTitle.equals(curr)) {
                    tabTitle = curr;
                    if (tabTitle.equals(today)) {
                        curr = "最新";
                    }
                    BusManager.getBus().post(new UpdateTabTitleEvent(curr));
                }
            }
        }
    }

    public class UpdateTabTitleEvent implements IEvent {
        private String title;

        public UpdateTabTitleEvent(String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }
    }

}
