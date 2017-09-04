package com.kotlin.kunlun.vmovier_in_kotlin.entity


/**
 * Created by hpb on 16/6/8.
 */
data class PostTab(var postid: String, var title: String, var pid: String, var app_fu_title: String,
                   var is_xpc: String, var is_promote: String, var is_xpc_zp: String, var is_xpc_cp: String,
                   var is_xpc_fx: String, var is_album: String, var tags: String, var recent_hot: String,
                   var discussion: String, var image: String, var rating: String, var duration: String,
                   var publish_time: Long = 0, var like_num: String, var share_num: String, var request_url: String,
                   var cates: List<Cate>)


/**
 * postid : 49239
 * title : 绝美4k延时摄影风光片《转变》
 * pid : 1
 * app_fu_title :
 * is_xpc : 0
 * is_promote : 0
 * is_xpc_zp : 0
 * is_xpc_cp : 0
 * is_xpc_fx : 0
 * is_album : 0
 * tags :
 * recent_hot : 0
 * discussion : 0
 * image : http://cs.vmoiver.com/Uploads/cover/2016-06-06/575561fa69ef6_cut.jpeg
 * rating : 7.0
 * duration : 152
 * publish_time : 1465344240
 * like_num : 89
 * share_num : 182
 * cates : [{"cateid":"11","catename":"旅行"}]
 * request_url : http://app.vmoiver.com/49239?qingapp=app_new
 */

    



