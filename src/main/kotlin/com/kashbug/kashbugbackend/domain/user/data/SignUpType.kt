package com.kashbug.kashbugbackend.domain.user.data

enum class SignUpType(
    val value: String
) {

    DIRECT("D")
    ;

    companion object {
        fun of(value: String?) = values().find { it.value == value }
    }

}