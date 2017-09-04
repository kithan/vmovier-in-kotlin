package com.kotlin.kunlun.vmovier_in_kotlin.data

/**
 * Created by hpb on 2017/8/23.
 */

class ApiException : Exception {

    constructor(status: Int) : super(getExceptionMessage(status)) {}

    constructor(message: String) : super(message) {}


    companion object {
        var message = "未知错误"

        private fun getExceptionMessage(status: Int): String {
            when (status) {
                500 -> message = "服务端异常"
                else -> message = "未知错误"
            }
            return message
        }
    }

}
