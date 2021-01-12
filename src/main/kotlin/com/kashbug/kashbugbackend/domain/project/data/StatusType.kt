package com.kashbug.kashbugbackend.domain.project.data

enum class StatusType {

    TEST,

    LIVE
    ;

    companion object {
        fun of(name: String?) = values().find { it.name == name }
    }

}