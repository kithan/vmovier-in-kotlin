package com.kotlin.kunlun.vmovier_in_kotlin.data

data class HttpResult<T>(var status: Int = 0,
                         var msg: String? = null,
                         var total: Int = 0,
                         var data: T? = null)