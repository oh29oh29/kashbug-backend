package com.kashbug.kashbugbackend.domain.user.service

import com.kashbug.kashbugbackend.defaultIfEmpty
import com.kashbug.kashbugbackend.domain.user.entity.Member
import com.kashbug.kashbugbackend.domain.user.repository.MemberRepository
import com.kashbug.kashbugbackend.domain.user.value.GenderType
import com.kashbug.kashbugbackend.domain.user.value.SignUpType
import com.kashbug.kashbugbackend.error.exception.KashbugException
import com.kashbug.kashbugbackend.presentation.data.ResponseCode
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.*

@Service
class MemberService(
    private val memberRepository: MemberRepository
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
        memberRepository.save(
            Member(
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

    // TODO: 사용자 수정 API
    fun update(
        id: String,
        password: String?
    ) {
        memberRepository.findByIdOrNull(id)?.apply {
            this.password = password.defaultIfEmpty(this.password)

            memberRepository.save(this)
        } ?: throw KashbugException(ResponseCode.BAD_REQUEST)
    }

    fun existId(id: String): Boolean {
        val existMember = memberRepository.findByIdOrNull(id)
        return !Objects.isNull(existMember)
    }

    fun get(id: String): Member? {
        return memberRepository.findByIdOrNull(id)
    }
}