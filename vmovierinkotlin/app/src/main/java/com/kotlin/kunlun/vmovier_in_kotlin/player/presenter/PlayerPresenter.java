package com.kotlin.kunlun.vmovier_in_kotlin.player.presenter;

import com.kotlin.kunlun.vmovier_in_kotlin.BasePresenter;
import com.kotlin.kunlun.vmovier_in_kotlin.SimpleDisposableObserver;
import com.kotlin.kunlun.vmovier_in_kotlin.db.VideoInfoDao;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Comment;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostDetail;
import com.kotlin.kunlun.vmovier_in_kotlin.entity.VideoInfo;
import com.kotlin.kunlun.vmovier_in_kotlin.player.view.IPlayerView;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloadSampleListener;
import com.liulishuo.filedownloader.FileDownloader;
import com.liulishuo.filedownloader.util.FileDownloadUtils;

import java.util.List;


/**
 * Created by 0000- on 2016/6/12.
 */
public class PlayerPresenter extends BasePresenter<IPlayerView> implements IPlayerPresenter {


    @Override
    public void getPostDetail(String postId) {
        getVmovierApi()
                .getPostDetail(Integer.parseInt(postId))
                .compose(this.<PostDetail>transformResult())
                .subscribe(new SimpleDisposableObserver<PostDetail>() {
                    @Override
                    public void onNext(PostDetail postDetail) {
                        getMvpView().onGetPostDetail(postDetail);
                    }
                });
    }

    @Override
    public void getComments(int p, String postId) {
        getVmovierApi()
                .getComments(p, Integer.parseInt(postId), 0)
                .compose(this.<List<Comment>>transformResult())
                .subscribe(new SimpleDisposableObserver<List<Comment>>() {
                    @Override
                    public void onNext(List<Comment> comments) {
                        getMvpView().onGetComments(comments);

                    }
                });
    }


    public void addDownload(final VideoInfo videoInfo) {
        FileDownloader.getImpl().create(videoInfo.getSource_link())
                .setPath(FileDownloadUtils.getDefaultSaveFilePath(videoInfo.getSource_link()))
                .setCallbackProgressTimes(300)
                .setMinIntervalUpdateSpeed(400)
                .setListener(downloadListener);

        videoInfo.setDownloadId(FileDownloadUtils.generateId(videoInfo.getQiniu_url(), FileDownloadUtils.getDefaultSaveFilePath(videoInfo.getQiniu_url())));
        videoInfo.setDownloadStatus(1);
        getVideoInfoDao().insertOrReplaceInTx(videoInfo);
    }

    private FileDownloadListener downloadListener = new FileDownloadSampleListener() {
        @Override
        protected void completed(final BaseDownloadTask task) {
            VideoInfo info = getVideoInfoDao().queryBuilder().where(VideoInfoDao.Properties.DownloadId.eq(task.getId()))
                    .limit(1).list().get(0);
            info.setDownloadStatus(2);
            getVideoInfoDao().update(info);
        }
    };
}
