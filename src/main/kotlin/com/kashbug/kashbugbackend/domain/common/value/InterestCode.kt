package com.kashbug.kashbugbackend.domain.common.value

enum class InterestCode {

    GAME
    ;

    companion object {
        fun of(value: String?) = values().find { it.name == value }
    }
}