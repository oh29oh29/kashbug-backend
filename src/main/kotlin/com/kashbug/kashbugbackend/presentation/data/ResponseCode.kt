package com.kashbug.kashbugbackend.presentation.data

enum class ResponseCode(val code: String, val message: String) {

    STATUS_SUCCESS("0000", "성공하였습니다."),
    DUPLICATED_ID("0010", "아이디가 이미 존재합니다."),

    STATUS_BAD_REQUEST("4000", "잘못된 요청입니다."),
    EXPIRED_JWT_TOKEN("4001", "JWT Token이 만료되었습니다."),
    INVALID_JWT_TOKEN("4002", "JWT Token이 유효하지 않습니다."),
    INVALID_JWT_SIGNATURE("4003", "JWT Signature가 유효하지 않습니다."),
    NOT_NULL_JWT_CLAIM("4004", "JWT Claim이 비어져 있습니다."),
    NOT_EXIST_USER("4005", "존재하지 않는 사용자 계정입니다."),
    NOT_MATCHED_USER_PASSWORD("4006", "사용자 비밀번호가 일치하지 않습니다.")
    ;
}