package com.kashbug.kashbugbackend.domain.user.data

enum class GenderType(
    val value: String
) {
    MALE("M"),
    FEMALE("F")
    ;

    companion object {
        fun of(value: String?) = values().find { it.value == value }
    }
}