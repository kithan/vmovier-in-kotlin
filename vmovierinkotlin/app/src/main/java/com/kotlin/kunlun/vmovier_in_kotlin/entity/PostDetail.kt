package com.kotlin.kunlun.vmovier_in_kotlin.entity

import android.os.Parcel
import java.io.Serializable

/**
 * Created by hpb on 16/6/8.
 */


class PostDetail : Serializable {
    /**
     * postid : 49216
     * title : 暖心重温成人经典动画《小王子》
     * app_fu_title :
     * intro : 所有大人最初都是孩子，然而，所有的孩子终将长大，时间用最残酷的魔法，将我们变成了当初最不愿成为的那个人。
     *
     *
     * 本片是由2015年上映的法国大电影《小王子》中截取的片段剪辑而成，去除了小女孩与飞行老爷爷那段现代社会故事线，只留取了与原著本身相关的内容。
     *
     *
     * 原著《小王子》是法国作家安托万·德·圣·埃克苏佩里，于1942年写成的儿童文学作品，伴随了一代又一代人的成长。作家安托万具有双重身份，他既是一名飞行员，也是一位作家，在他短短44年的人生中，创造了两项别人很难企及的记录，一是创作了这部伟大作品《小王子》，据说迄今为止在全球卖掉了2亿册，二是驾驶着飞机消失在蓝天白云深处，再也没有现身，化作不朽传奇。
     * count_comment : 25
     * is_album : 0
     * is_collect : 0
     * content : {"video":[{"image":"http://cs.vmoiver.com/Uploads/cover/2016-06-03/5750f8150f6e0_cut.jpeg","title":"暖心重温成人经典动画《小王子》","duration":"1004","filesize":"311087171","source_link":"http://v.youku.com/v_show/id_XMTU5MzczNzI3Ng==.html","qiniu_url":"http://bsy.qiniu.vmovier.vmoiver.com/5750fdbae4936.mp4"}]}
     * image : http://cs.vmoiver.com/Uploads/cover/2016-06-03/5750f8150f6e0_cut.jpeg
     * rating : 9.0
     * publish_time : 1464926331
     * count_like : 1231
     * count_share : 2405
     * cate : ["动画"]
     * share_link : {"sweibo":"http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_sweibo","weixin":"http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_weixin","qzone":"http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_qzone","qq":"http://www.vmovier.com/49216?debug=1&_vfrom=VmovierApp_qq"}
     * tags :
     */

    var postid: String? = null

    var title: String? = null

    var app_fu_title: String? = null

    var intro: String? = null
    var duration:String?=null

    var count_comment: String? = null

    var is_album: String? = null

    var is_collect: String? = null

    var content: ContentBean? = null

    var image: String? = null

    var rating: String? = null

    var publish_time: String? = null

    var count_like: String? = null

    var count_share: String? = null

    /**
     * sweibo : http://h5.vmovier.com/index.html?id=49216&_vfrom=VmovierApp_sweibo
     * weixin : http://h5.vmovier.com/index.html?id=49216&_vfrom=VmovierApp_weixin
     * qzone : http://h5.vmovier.com/index.html?id=49216&_vfrom=VmovierApp_qzone
     * qq : http://h5.vmovier.com/index.html?id=49216&_vfrom=VmovierApp_qq
     */

    var share_link: ShareLinkBean? = null

    var tags: String? = null

    var cates: List<Cate>? = null

    class ContentBean : Serializable {
        /**
         * image : http://cs.vmoiver.com/Uploads/cover/2016-06-03/5750f8150f6e0_cut.jpeg
         * title : 暖心重温成人经典动画《小王子》
         * duration : 1004
         * filesize : 311087171
         * source_link : http://v.youku.com/v_show/id_XMTU5MzczNzI3Ng==.html
         * qiniu_url : http://bsy.qiniu.vmovier.vmoiver.com/5750fdbae4936.mp4
         */

        var video: List<VideoInfo>? = null


        protected constructor(`in`: Parcel) {
            this.video = `in`.createTypedArrayList(VideoInfo.CREATOR)
        }
    }

    class ShareLinkBean : Serializable {

        var sweibo: String? = null
        var weixin: String? = null
        var qzone: String? = null
        var qq: String? = null

    }

}
