package com.kotlin.kunlun.vmovier_in_kotlin.entity

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by hpb on 16/6/8.
 */
data class Cate(var cate_type: Int = 0, var orderid: Int = 0, var cateid: Int = 0,
                var catename: String, var alias: String, var icon: String) : Parcelable {
    constructor() : this(0, 0, 0, "", "", "") {}

    constructor(source: Parcel) : this(
            source.readInt(),
            source.readInt(),
            source.readInt(),
            source.readString(),
            source.readString(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeInt(cate_type)
        writeInt(orderid)
        writeInt(cateid)
        writeString(catename)
        writeString(alias)
        writeString(icon)
    }

    companion object {
        val CATE_ORIGINALITY = 6

        val CATE_TRAVEL = 11

        val CATE_LOVE = 12

        val CATE_CARTOON = 16

        val CATE_LIZHI = 7

        val CATE_MUSIC = 18

        val CATE_SCIENCE_FICTION = 23

        val CATE_PREVIEW = 43

        @JvmField
        val CREATOR: Parcelable.Creator<Cate> = object : Parcelable.Creator<Cate> {
            override fun createFromParcel(source: Parcel): Cate = Cate(source)
            override fun newArray(size: Int): Array<Cate?> = arrayOfNulls(size)
        }
    }
}
