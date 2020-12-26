package com.kashbug.kashbugbackend.presentation.data

data class OkResponse<T> (

    val result: T? = null,
    val code: String = ResponseCode.STATUS_SUCCESS.code,
    val name: String = ResponseCode.STATUS_SUCCESS.name,
    val message: String = ResponseCode.STATUS_SUCCESS.message

)