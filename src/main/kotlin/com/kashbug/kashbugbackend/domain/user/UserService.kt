package com.kashbug.kashbugbackend.domain.user

import com.kashbug.kashbugbackend.domain.user.data.GenderType
import com.kashbug.kashbugbackend.domain.user.data.SignUpType
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    fun save(
        id: String,
        name: String,
        password: String,
        gender: GenderType,
        email: String,
        contact: String,
        profileImageUrl: String?,
        birthYear: String,
        signUpType: SignUpType
    ) {
        userRepository.save(
            User(
                id,
                name,
                password,
                gender,
                email,
                contact,
                profileImageUrl,
                birthYear,
                signUpType
            )
        )
    }

    fun isDuplicatedUserId(id: String): Boolean {
        val duplicatedUser = userRepository.findById(id)
        if (duplicatedUser.isEmpty) return false
        return true
    }

    fun get(id: String): User {
        val user = userRepository.findById(id)

        if (user.isEmpty) throw KashbugException(ResponseCode.NOT_FOUND_USER)
        return user.get()
    }
}