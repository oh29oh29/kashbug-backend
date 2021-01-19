package com.kashbug.kashbugbackend.domain.project.value

enum class BugType {

    TYPING_ERROR,

    ETC
    ;

    companion object {
        fun of(name: String?) = values().find { it.name == name }
    }

}