package com.kotlin.kunlun.vmovier_in_kotlin.channel


import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostDetail
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostTab

import java.util.ArrayList

/**
 * Created by hpb on 2017/8/17.
 */

class AdapterEntity {
    var title: String? = null
    lateinit  var subName: String
    var imageUrl: String? = null
    var duration: String? = null

    companion object {

        fun getListFromPostTab(postTabs: List<PostTab>): List<AdapterEntity> {
            val entities = ArrayList<AdapterEntity>(postTabs.size)
            for (pt in postTabs) {
                val entity = AdapterEntity()
                entity.title = pt.title
                entity.subName = pt.cates[0].catename
                entity.imageUrl = pt.image
                entity.duration = pt.duration
                entities.add(entity)
            }
            return entities
        }

        fun getListFromPostDetail(postDetailList: List<PostDetail>): List<AdapterEntity> {
            val entities = ArrayList<AdapterEntity>(postDetailList.size)
            for (pt in postDetailList) {
                val entity = AdapterEntity()
                entity.title = pt.title
                entity.subName = pt.cates!![0].catename
                entity.imageUrl = pt.image
                entity.duration = pt.duration
                entities.add(entity)
            }
            return entities
        }
    }
}
