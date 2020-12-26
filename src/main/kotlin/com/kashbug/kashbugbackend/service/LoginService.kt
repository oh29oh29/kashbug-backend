package com.kashbug.kashbugbackend.service

import com.kashbug.kashbugbackend.domain.member.MemberService
import com.kashbug.kashbugbackend.domain.member.data.AccountType
import com.kashbug.kashbugbackend.domain.member.data.SignUpType
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import com.kashbug.kashbugbackend.service.data.LoginRequest
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginService(
    private val passwordEncoder: PasswordEncoder,
    private val memberService: MemberService
) {

    private val log = LoggerFactory.getLogger(LoginService::class.java)

    fun join(request: LoginRequest.Join) {
        when (request.accountType) {
            AccountType.INDIVIDUAL -> joinIndividual(request)
            AccountType.ENTERPRISE -> joinEnterprise(request)
        }
    }

    private fun joinIndividual(request: LoginRequest.Join) {
        request.gender ?: run {
            log.error("성별이 올바르지 않습니다.")
            throw KashbugException(ResponseCode.STATUS_BAD_REQUEST)
        }

        if (request.birthYear.isNullOrBlank()) {
            log.error("출생 연도가 올바르지 않습니다.")
            throw KashbugException(ResponseCode.STATUS_BAD_REQUEST)
        }

        if (memberService.isDuplicatedMemberId(request.id)) {
            log.debug("아이디가 이미 존재합니다. id: ${request.id}")
            throw KashbugException(ResponseCode.DUPLICATED_ID)
        }

        memberService.save(
            request.id,
            request.name,
            toEncryptedPassword(request.password),
            request.gender,
            request.email,
            request.contact,
            request.profileImageUrl,
            request.birthYear,
            SignUpType.DIRECT
        )

    }

    private fun joinEnterprise(request: LoginRequest.Join) {

    }

    private fun toEncryptedPassword(password: String): String {
        return passwordEncoder.encode(password)
    }

}
