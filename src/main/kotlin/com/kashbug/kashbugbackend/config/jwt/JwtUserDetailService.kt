package com.kashbug.kashbugbackend.config.jwt

import com.kashbug.kashbugbackend.domain.user.UserService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class JwtUserDetailService(
    private val userService: UserService
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val user = userService.get(id)
        return User(user.id, user.password, hashSetOf())
    }
}