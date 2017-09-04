package com.kotlin.kunlun.vmovier_in_kotlin.entity

import com.chad.library.adapter.base.entity.SectionEntity

/**
 * Created by 0000- on 2016/6/9.
 */
class PostSection : SectionEntity<PostTab> {
    constructor(isHeader: Boolean, header: String) : super(isHeader, header)

    constructor(postTab: PostTab) : super(postTab)
}
