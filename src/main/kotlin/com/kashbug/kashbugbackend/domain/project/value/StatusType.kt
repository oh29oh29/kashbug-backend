package com.kashbug.kashbugbackend.domain.project.value

enum class StatusType {

    TEST,

    LIVE
    ;

    companion object {
        fun of(name: String?) = values().find { it.name == name }
    }

}