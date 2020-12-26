package com.kashbug.kashbugbackend.config.jwt

import com.kashbug.kashbugbackend.domain.member.MemberService
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class JwtUserDetailService(
    private val memberService: MemberService
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val user = memberService.get(id) ?: throw KashbugException(ResponseCode.NOT_EXIST_USER)
        return User(user.id, user.password, hashSetOf())
    }
}