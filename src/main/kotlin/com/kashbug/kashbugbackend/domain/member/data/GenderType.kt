package com.kashbug.kashbugbackend.domain.member.data

import com.fasterxml.jackson.annotation.JsonProperty

enum class GenderType(
    val value: String
) {

    @JsonProperty("M")
    MALE("M"),

    @JsonProperty("F")
    FEMALE("F")
    ;

    companion object {
        fun of(value: String?) = values().find { it.value == value }
    }
}