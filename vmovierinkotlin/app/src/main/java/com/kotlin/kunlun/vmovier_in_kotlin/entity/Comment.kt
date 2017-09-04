package com.kotlin.kunlun.vmovier_in_kotlin.entity

/**
 * Created by 0000- on 2016/6/12.
 */
data class Comment(var commentid: String, var isrecommend: String, var count_approve: String, var has_approve: String,
                   var content: String, var addtime: String, var userinfo: User, var reply_username: String,
                   var reply_user_isadmin: String, var reply_userinfo: List<User>, var subcomment: List<Comment>)


/**
 * commentid : 269813
 * isrecommend : 0
 * count_approve : 1
 * has_approve : 0
 * content : 小编影评写的不错啊，赞一个[鼓掌]
 * addtime : 1465698176
 * userinfo : {"userid":"319760","username":"　　　　_3","avatar":"http://cs.vmovier.com/Uploads/avatar/2014/05/29/5386d49832266_80.jpeg","isadmin":"0","is_xpc_author":"0"}
 * reply_username :
 * reply_user_isadmin :
 * reply_userinfo : []
 * subcomment : []
 */



