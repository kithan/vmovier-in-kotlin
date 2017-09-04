package com.kotlin.kunlun.vmovier_in_kotlin.entity

import android.os.Parcel
import android.os.Parcelable

import org.greenrobot.greendao.annotation.Entity
import org.greenrobot.greendao.annotation.Generated
import org.greenrobot.greendao.annotation.Id


/**
 * Created by 0000- on 2016/6/13.
 */
@Entity
class VideoInfo : Parcelable {

    @Id(autoincrement = true)
    var id: Long = 0

    var downloadId: Int = 0
    var downloadStatus: Int = 0
    var postId: String? = null
    var image: String? = null
    var title: String? = null
    var duration: String? = null
    var filesize: String? = null
    var source_link: String? = null
    var qiniu_url: String? = null

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(this.image)
        dest.writeString(this.title)
        dest.writeString(this.duration)
        dest.writeString(this.filesize)
        dest.writeString(this.source_link)
        dest.writeString(this.qiniu_url)
    }

    constructor() {}

    protected constructor(`in`: Parcel) {
        this.image = `in`.readString()
        this.title = `in`.readString()
        this.duration = `in`.readString()
        this.filesize = `in`.readString()
        this.source_link = `in`.readString()
        this.qiniu_url = `in`.readString()
    }

    @Generated(hash = 1857116569)
    constructor(id: Long, downloadId: Int, downloadStatus: Int, postId: String,
                image: String, title: String, duration: String, filesize: String,
                source_link: String, qiniu_url: String) {
        this.id = id
        this.downloadId = downloadId
        this.downloadStatus = downloadStatus
        this.postId = postId
        this.image = image
        this.title = title
        this.duration = duration
        this.filesize = filesize
        this.source_link = source_link
        this.qiniu_url = qiniu_url
    }

    companion object {

        val CREATOR: Parcelable.Creator<VideoInfo> = object : Parcelable.Creator<VideoInfo> {
            override fun createFromParcel(source: Parcel): VideoInfo {
                return VideoInfo(source)
            }

            override fun newArray(size: Int): Array<VideoInfo?> {
                return arrayOfNulls(size)
            }
        }
    }
}
