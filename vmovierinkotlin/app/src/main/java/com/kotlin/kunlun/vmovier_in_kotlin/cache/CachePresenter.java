package com.kotlin.kunlun.vmovier_in_kotlin.cache;

import com.kotlin.kunlun.vmovier_in_kotlin.BasePresenter;
import com.kotlin.kunlun.vmovier_in_kotlin.IPresenter;
import com.kotlin.kunlun.vmovier_in_kotlin.IView;
import com.kotlin.kunlun.vmovier_in_kotlin.db.VideoInfoDao;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.VideoInfo;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.List;

//import com.liulishuo.filedownloader.FileDownloader;

/**
 * Created by 0000- on 2016/6/22.
 */
public class CachePresenter extends BasePresenter<ICacheView> implements ICacheView {


    public void initCacheList() {
        List<VideoInfo> result = getVideoInfoDao().queryBuilder().where(VideoInfoDao.Properties.DownloadStatus.eq(2)).list();
        QueryBuilder<VideoInfo> queryBuilder = getVideoInfoDao().queryBuilder().where(VideoInfoDao.Properties.DownloadStatus.eq(1));
        long count = queryBuilder.count();
        String title = "";
        if (count > 0) {
            title = queryBuilder.limit(1).list().get(0).getTitle();
        }
        getMvpView().updateCachedList(result, (int) count, title);
    }

    @Override
    public void updateCachedList(List<VideoInfo> videoInfoList, int cachingCount, String title) {

    }
}
