package com.kashbug.kashbugbackend.domain.project.value

enum class BugStatusType {

    UNCONFIRMED,

    DEPOSIT_WAITING,

    DEPOSIT_COMPLETED,

    DEPOSIT_OVERDUE,

    REJECTED
    ;

    companion object {
        fun of(name: String?) = values().find { it.name == name }
    }

}