package com.kashbug.kashbugbackend.config.jwt

import com.kashbug.kashbugbackend.domain.member.MemberService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class JwtUserDetailService(
    private val memberService: MemberService
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val user = memberService.get(id)
        return User(user.id, user.password, hashSetOf())
    }
}