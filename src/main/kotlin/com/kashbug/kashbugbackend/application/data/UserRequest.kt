package com.kashbug.kashbugbackend.application.data

import com.kashbug.kashbugbackend.domain.user.value.AccountType
import com.kashbug.kashbugbackend.domain.user.value.GenderType

class UserRequest {

    data class Get(
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

}