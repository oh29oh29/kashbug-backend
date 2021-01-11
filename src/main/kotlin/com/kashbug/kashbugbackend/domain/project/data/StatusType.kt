package com.kashbug.kashbugbackend.domain.project.data

import com.fasterxml.jackson.annotation.JsonProperty

enum class StatusType(
    val value: String
) {

    @JsonProperty("T")
    TEST("T"),

    @JsonProperty("L")
    LIVE("L")
    ;

    companion object {
        fun of(value: String?) = values().find { it.value == value }
    }

}