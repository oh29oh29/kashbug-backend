package com.kashbug.kashbugbackend.config.jwt

import com.kashbug.kashbugbackend.domain.user.service.EnterpriseService
import com.kashbug.kashbugbackend.domain.user.service.MemberService
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class JwtUserDetailService(
    private val memberService: MemberService,
    private val enterpriseService: EnterpriseService
) : UserDetailsService {

    override fun loadUserByUsername(id: String): UserDetails {
        val user = memberService.get(id) ?: enterpriseService.get(id)
        ?: throw KashbugException(ResponseCode.NOT_EXIST_USER)

        return User(user.id, user.password, hashSetOf())
    }
}