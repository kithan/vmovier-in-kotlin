package com.kotlin.kunlun.vmovier_in_kotlin.entity

/**
 * Created by hpb on 16/6/8.
 */
data class Banner(var bannerid: String, var title: String, var image: String, var description: String,
                  var addtime: String, var extra: String, var end_time: String, var extra_data: ExtraDataBean) {

    /**
     * bannerid : 990
     * title :
     * image : http://cs.vmoiver.com/Uploads/Banner/2016/06/06/5755418ba6238.jpg
     * description :
     * addtime : 1465205132
     * extra : {"app_banner_type":"2","app_banner_param":""}
     * end_time : 0
     * extra_data : {"app_banner_type":"2","app_banner_param":"49180","is_album":"0"}
     */


    /**
     * app_banner_type : 2
     * app_banner_param : 49180
     * is_album : 0
     */
    class ExtraDataBean {
        var app_banner_type: String? = null
        var app_banner_param: String? = null
        var is_album: String? = null
    }
}
