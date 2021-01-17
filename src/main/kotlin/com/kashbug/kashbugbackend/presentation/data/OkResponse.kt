package com.kashbug.kashbugbackend.presentation.data

data class OkResponse<T> (

    val result: T? = null,
    val code: String = ResponseCode.SUCCESS.code,
    val name: String = ResponseCode.SUCCESS.name,
    val message: String = ResponseCode.SUCCESS.message

)