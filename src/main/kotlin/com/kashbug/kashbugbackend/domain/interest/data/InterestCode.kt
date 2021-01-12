package com.kashbug.kashbugbackend.domain.interest.data

enum class InterestCode {

    GAME
    ;

    companion object {
        fun of(value: String?) = values().find { it.name == value }
    }
}