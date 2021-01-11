package com.kashbug.kashbugbackend.application

import com.kashbug.kashbugbackend.application.data.LoginRequest
import com.kashbug.kashbugbackend.application.data.LoginResponse
import com.kashbug.kashbugbackend.config.jwt.JwtTokenProvider
import com.kashbug.kashbugbackend.domain.enterprise.EnterpriseService
import com.kashbug.kashbugbackend.domain.member.MemberService
import com.kashbug.kashbugbackend.domain.member.data.AccountType
import com.kashbug.kashbugbackend.domain.member.data.SignUpType
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class LoginApplicationService(
    private val passwordEncoder: PasswordEncoder,
    private val memberService: MemberService,
    private val enterpriseService: EnterpriseService,
    private val jwtTokenProvider: JwtTokenProvider
) {

    private val log = LoggerFactory.getLogger(LoginApplicationService::class.java)

    fun join(request: LoginRequest.Join) {
        when (request.accountType) {
            AccountType.INDIVIDUAL -> joinIndividual(request)
            AccountType.ENTERPRISE -> joinEnterprise(request)
        }
    }

    private fun joinIndividual(request: LoginRequest.Join) {
        request.gender ?: run {
            log.debug("성별이 비어져 있습니다.")
            throw KashbugException(ResponseCode.STATUS_BAD_REQUEST)
        }

        if (request.birthYear.isNullOrBlank()) {
            log.error("출생 연도가 비어져 있습니다.")
            throw KashbugException(ResponseCode.STATUS_BAD_REQUEST)
        }

        if (memberService.isDuplicatedMemberId(request.id)) {
            log.debug("개인 아이디가 이미 존재합니다. id: ${request.id}")
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
        request.introduce ?: run {
            log.debug("소개가 비어져 있습니다.")
            throw KashbugException(ResponseCode.STATUS_BAD_REQUEST)
        }

        if (enterpriseService.isDuplicatedEnterpriseId(request.id)) {
            log.debug("기업 아이디가 이미 존재합니다. id: ${request.id}")
            throw KashbugException(ResponseCode.DUPLICATED_ID)
        }

        enterpriseService.save(
            request.id,
            request.name,
            toEncryptedPassword(request.password),
            request.email,
            request.serial,
            request.contact,
            request.profileImageUrl,
            request.homepageUrl,
            request.introduce
        )
    }

    private fun toEncryptedPassword(password: String): String {
        return passwordEncoder.encode(password)
    }

    fun login(request: LoginRequest.Login): LoginResponse.Login {
        val user =
            memberService.get(request.id)
                ?: enterpriseService.get(request.id)
                ?: throw KashbugException(ResponseCode.NOT_EXIST_USER)

        if (!passwordEncoder.matches(request.password, user.password)) throw KashbugException(ResponseCode.NOT_MATCHED_USER_PASSWORD)

        return LoginResponse.Login(jwtTokenProvider.issue(user.id))
    }

}
