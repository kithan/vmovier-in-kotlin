package com.kotlin.kunlun.vmovier_in_kotlin.db

import android.content.Context

import com.kotlin.kunlun.vmovier_in_kotlin.entity.VideoInfo
import com.vise.xsnow.database.DBManager

import org.greenrobot.greendao.AbstractDao

/**
 * @Description: 数据库操作类，由于greenDao的特殊性，不能在框架中搭建，
 * * 所有数据库操作都可以参考该类实现自己的数据库操作管理类，不同的Dao实现
 * * 对应的getAbstractDao方法就行。
 * *
 * @date: 17/1/18 23:18.
 */
class DbHelper private constructor() {
    private var mHelper: DaoMaster.DevOpenHelper? = null
    var daoMaster: DaoMaster? = null
        private set
    var daoSession: DaoSession? = null
        private set

    fun init(context: Context) {
        mHelper = DaoMaster.DevOpenHelper(context, DB_NAME, null)
        daoMaster = DaoMaster(mHelper!!.writableDatabase)
        daoSession = daoMaster!!.newSession()
    }

    fun init(context: Context, dbName: String) {
        mHelper = DaoMaster.DevOpenHelper(context, dbName, null)
        daoMaster = DaoMaster(mHelper!!.writableDatabase)
        daoSession = daoMaster!!.newSession()
    }


    fun clear() {
        if (daoSession != null) {
            daoSession!!.clear()
            daoSession = null
        }
    }

    fun close() {
        clear()
        if (mHelper != null) {
            mHelper!!.close()
            mHelper = null
        }
    }

    private val DB_NAME = "vmovier_kotlin.db"

    private object Holder {
        val INSTANCE = DbHelper()
    }

    companion object {
        val instance: DbHelper by lazy { Holder.INSTANCE }
    }
}
