package com.kashbug.kashbugbackend.domain.user.value

import com.fasterxml.jackson.annotation.JsonProperty

enum class SignUpType(
    val value: String
) {

    @JsonProperty("D")
    DIRECT("D")
    ;

    companion object {
        fun of(value: String?) = values().find { it.value == value }
    }

}