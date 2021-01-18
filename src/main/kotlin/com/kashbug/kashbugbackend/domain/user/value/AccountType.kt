package com.kashbug.kashbugbackend.domain.user.value

import com.fasterxml.jackson.annotation.JsonProperty

enum class AccountType(
    val value: String
) {

    @JsonProperty("I")
    INDIVIDUAL("I"),

    @JsonProperty("E")
    ENTERPRISE("E")
    ;

    companion object {
        fun of(value: String?) = values().find { it.value == value }
    }

}