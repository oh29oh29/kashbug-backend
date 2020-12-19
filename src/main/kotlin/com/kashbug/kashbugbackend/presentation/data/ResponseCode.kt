package com.kashbug.kashbugbackend.presentation.data

enum class ResponseCode(val code: String, val message: String) {

    STATUS_SUCCESS("0000", "성공하였습니다."),
    DUPLICATED_ID("0010", "아이디가 이미 존재합니다."),

    STATUS_BAD_REQUEST("4000", "잘못된 요청입니다.")
    ;
}