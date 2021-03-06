package com.kotlin.kunlun.vmovier_in_kotlin.db;

import com.kotlin.kunlun.vmovier_in_kotlin.entity.VideoInfo;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;


// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig videoInfoDaoConfig;

    private final VideoInfoDao videoInfoDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        videoInfoDaoConfig = daoConfigMap.get(VideoInfoDao.class).clone();
        videoInfoDaoConfig.initIdentityScope(type);

        videoInfoDao = new VideoInfoDao(videoInfoDaoConfig, this);

        registerDao(VideoInfo.class, videoInfoDao);
    }
    
    public void clear() {
        videoInfoDaoConfig.clearIdentityScope();
    }

    public VideoInfoDao getVideoInfoDao() {
        return videoInfoDao;
    }

}
