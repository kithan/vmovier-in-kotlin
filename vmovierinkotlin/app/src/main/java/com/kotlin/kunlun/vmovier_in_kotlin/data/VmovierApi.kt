package com.kotlin.kunlun.vmovier_in_kotlin.data


import com.kotlin.kunlun.vmovier_in_kotlin.entity.Banner
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Cate
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Comment
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostDetail
import com.kotlin.kunlun.vmovier_in_kotlin.entity.PostTab
import com.kotlin.kunlun.vmovier_in_kotlin.entity.SearchData
import com.kotlin.kunlun.vmovier_in_kotlin.entity.Series

import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Observable

/**
 * Created by hpb on 16/6/8.
 */
interface VmovierApi {
    /**
     * 频道列表
     * @return
     */
    @get:GET("/apiv3/cate/getList")
    val cateList: Observable<HttpResult<List<Cate>>>

    /**
     * 首页banner
     * @return
     */
    @get:GET("/apiv3/index/getBanner")
    val banner: Observable<HttpResult<List<Banner>>>

    /**
     * 发现 or 最新
     * @param p
     * *
     * @return
     */
    @GET("/apiv3/post/getPostByTab")
    fun getTabPost(@Query("p") p: Int): Observable<HttpResult<List<PostTab>>>

    /**
     * 获取专辑／热门频道信息列表
     * @param p
     * *
     * @param size
     * *
     * @param tab album,hot,
     * *
     * @return
     */
    @GET("/apiv3/post/getPostByTab")
    fun getChannelList(@Query("p") p: Int, @Query("size") size: Int, @Query("tab") tab: String): Observable<HttpResult<List<PostTab>>>

    /**
     * 获取系列频道信息列表
     * @param p
     * *
     * @return
     */
    @GET("/apiv3/series/getList")
    fun getSeries(@Query("p") p: Int): Observable<HttpResult<List<Series>>>

    @GET("/apiv3/post/view")
    fun getPostDetail(@Query("postid") postId: Int): Observable<HttpResult<PostDetail>>

    @GET("/apiv3/comment/getLists")
    fun getComments(@Query("p") page: Int, @Query("postid") postId: Int, @Query("type") type: Int): Observable<HttpResult<List<Comment>>>

    /**
     * 获取幕后频道信息列表
     * @return
     */
    @get:GET("/apiv3/backstage/getCate")
    val backstageCate: Observable<HttpResult<PostDetail>>//幕后列表

    /**
     * 获取创意(cateid=6)/旅行(cateid=11)/爱情(cateid=12)/动画(cateid=16)/
     * 励志cateid=7，音乐18，科幻23，预告43，记录24，混剪44，实验45，生活78
     * 频道信息列表
     * @param page
     * *
     * @param cateid
     * *
     * @return
     */
    @GET("/apiv3/post/getPostInCate")
    fun getPostInCate(@Query("p") page: Int, @Query("cateid") cateid: Int): Observable<HttpResult<List<PostDetail>>>

    @GET("/apiv3/search/index")
    fun search(@Query("p") page: Int, @Query("size") size: Int, @Query("kw") kw: String): Observable<HttpResult<SearchData>>


}
