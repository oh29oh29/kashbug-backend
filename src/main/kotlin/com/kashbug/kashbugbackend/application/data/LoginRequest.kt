package com.kashbug.kashbugbackend.application.data

import com.kashbug.kashbugbackend.domain.member.data.AccountType
import com.kashbug.kashbugbackend.domain.member.data.GenderType

class LoginRequest {

    data class Join(
        val id: String,
        val name: String,
        val password: String,
        val email: String,
        val contact: String,
        val profileImageUrl: String?,
        val accountType: AccountType,
        val gender: GenderType?,
        val birthYear: String?,
        val serial: String?,
        val homepageUrl: String?,
        val introduce: String?
    )

    data class Login (
        val id: String,
        val password: String
    )

}